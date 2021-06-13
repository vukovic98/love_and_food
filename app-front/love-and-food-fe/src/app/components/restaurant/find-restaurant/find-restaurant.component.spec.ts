import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FindRestaurantComponent } from './find-restaurant.component';

describe('FindRestaurantComponent', () => {
  let component: FindRestaurantComponent;
  let fixture: ComponentFixture<FindRestaurantComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FindRestaurantComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FindRestaurantComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
