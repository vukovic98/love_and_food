import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RateRestaurantComponent } from './rate-restaurant.component';

describe('RateRestaurantComponent', () => {
  let component: RateRestaurantComponent;
  let fixture: ComponentFixture<RateRestaurantComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RateRestaurantComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RateRestaurantComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
