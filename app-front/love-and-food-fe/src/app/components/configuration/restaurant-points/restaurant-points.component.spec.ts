import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RestaurantPointsComponent } from './restaurant-points.component';

describe('RestaurantPointsComponent', () => {
  let component: RestaurantPointsComponent;
  let fixture: ComponentFixture<RestaurantPointsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RestaurantPointsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RestaurantPointsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
