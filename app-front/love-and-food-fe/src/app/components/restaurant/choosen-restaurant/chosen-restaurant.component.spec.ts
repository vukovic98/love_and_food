import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChosenRestaurantComponent } from './chosen-restaurant.component';

describe('ChoosenRestaurantComponent', () => {
  let component: ChosenRestaurantComponent;
  let fixture: ComponentFixture<ChosenRestaurantComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ChosenRestaurantComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ChosenRestaurantComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
