import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RestaurantDataComponent } from './restaurant-data.component';

describe('RestaurantDataComponent', () => {
  let component: RestaurantDataComponent;
  let fixture: ComponentFixture<RestaurantDataComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RestaurantDataComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RestaurantDataComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
