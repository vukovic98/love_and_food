import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FindRestaurantFormComponent } from './find-restaurant-form.component';

describe('FindRestaurantFormComponent', () => {
  let component: FindRestaurantFormComponent;
  let fixture: ComponentFixture<FindRestaurantFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FindRestaurantFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FindRestaurantFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
