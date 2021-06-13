import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RateRestaurantItemComponent } from './rate-restaurant-item.component';

describe('RateRestaurantItemComponent', () => {
  let component: RateRestaurantItemComponent;
  let fixture: ComponentFixture<RateRestaurantItemComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RateRestaurantItemComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RateRestaurantItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
