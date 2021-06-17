import {HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import { Injectable } from '@angular/core';
import {Observable, of, throwError} from 'rxjs';
import {AuthService} from "../services/auth.service";
import {Router} from "@angular/router";
import Swal from 'sweetalert2';
import {catchError} from "rxjs/operators";

@Injectable()
export class AuthInterceptorService implements HttpInterceptor {

  constructor(
    private router: Router,
    private authService: AuthService
  ) {}


  private handleAuthError(err: HttpErrorResponse): Observable<any> {
    if (err.status === 401 || err.status === 403) {

      this.authService.logout();

      this.router.navigate(['/auth/login']).then(r => null);

      Swal.fire({
        icon: 'error',
        title: 'Your token has expired. Please login again.',
        showConfirmButton: false,
        timer: 3000
      }).then(r => null)

      return of(err.message);
    }
    return throwError(err);
  }

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        const jwtToken = localStorage.getItem('accessToken');
        if (!jwtToken) {
            // ukoliko jwt token ne postoji niko nije ulogovan i authorization header nije potreban
            // samo prosledimo netaknut request
            return next.handle(req);
        }
        // ukoliko je neko ulogovan zakacimo authorization header sa jwt tokenom
        // httpRequest je immutable pa moramo da ga kloniramo da bismo ga izmenili
        const modifiedReq = req.clone({
            headers: req.headers.set('Authorization', 'Bearer ' +  jwtToken)
          });
        return next.handle(modifiedReq).pipe(catchError(x=> this.handleAuthError(x)));
    }

}
