import {Component, OnInit, Output, EventEmitter} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {FilterRestaurantsModel} from "../../../models/filter-restaurants.model";

@Component({
  selector: 'app-filter-restaurants',
  templateUrl: './filter-restaurants.component.html',
  styleUrls: ['./filter-restaurants.component.css']
})
export class FilterRestaurantsComponent {

  filterForm = new FormGroup({
    name: new FormControl('', ),
    price: new FormControl(''),
    location: new FormControl(''),
    cuisine: new FormControl(''),
  });

  @Output() filterRestaurantsEvent = new EventEmitter<FilterRestaurantsModel>();

  filterRestaurants() {
    const dto: FilterRestaurantsModel = {
      "name": (this.filterForm.value.name !== '' && this.filterForm.value.name !== null) ? this.filterForm.value.name : null,
      "price": (this.filterForm.value.price !== '' && this.filterForm.value.price !== 'NONE') ? this.filterForm.value.price : null,
      "location": (this.filterForm.value.location !== '' && this.filterForm.value.location !== 'NONE') ? this.filterForm.value.location : null,
      "cuisine": (this.filterForm.value.cuisine !== '' && this.filterForm.value.cuisine !== 'NONE') ? this.filterForm.value.cuisine : null
    }

    this.filterRestaurantsEvent.emit(dto);
  }
}
