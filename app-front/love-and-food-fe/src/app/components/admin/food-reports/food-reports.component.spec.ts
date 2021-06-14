import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FoodReportsComponent } from './food-reports.component';

describe('FoodReportsComponent', () => {
  let component: FoodReportsComponent;
  let fixture: ComponentFixture<FoodReportsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FoodReportsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FoodReportsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
