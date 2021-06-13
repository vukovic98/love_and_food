import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RateRestaurantFormComponent } from './rate-restaurant-form.component';

describe('RateRestaurantFormComponent', () => {
  let component: RateRestaurantFormComponent;
  let fixture: ComponentFixture<RateRestaurantFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RateRestaurantFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RateRestaurantFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
