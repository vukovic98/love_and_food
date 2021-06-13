import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FilterRestaurantsModel} from "../../../../models/filter-restaurants.model";
import {RestaurantModel} from "../../../../models/restaurant.model";
import {FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-add-restaurant-form',
  templateUrl: './add-restaurant-form.component.html',
  styleUrls: ['./add-restaurant-form.component.css']
})
export class AddRestaurantFormComponent implements OnInit {

  @Output() addRestaurantsEvent = new EventEmitter<RestaurantModel>();

  createForm = new FormGroup({
    name: new FormControl('', Validators.required),
    location: new FormControl('', Validators.required),
    startingHours: new FormControl('', Validators.required),
    endingHours: new FormControl('', Validators.required),
    grades: new FormControl({ value: null, disabled: false }, Validators.required),
    ambient: new FormControl('', Validators.required),
    music: new FormControl('', Validators.required),
    cuisine: new FormControl('', Validators.required),
    priceRange: new FormControl('', Validators.required),
    garden: new FormControl({ value: false, disabled: false }, Validators.required),
    wifi: new FormControl({ value: false, disabled: false }, Validators.required),
    tv: new FormControl({ value: false, disabled: false }, Validators.required),
    liveMusic: new FormControl({ value: false, disabled: false }, Validators.required),
    alcohol: new FormControl({ value: false, disabled: false }, Validators.required),
    parking: new FormControl({ value: false, disabled: false }, Validators.required),
    smokingArea: new FormControl({ value: false, disabled: false }, Validators.required),
    image: new FormControl(''),
  });

  constructor() { }

  ngOnInit(): void {

  }

  createRestaurant(): void {
    const dto: RestaurantModel = {
      alcohol: this.createForm.value.alcohol,
      ambient: this.createForm.value.ambient,
      cuisine: this.createForm.value.cuisine,
      endingHours: this.createForm.value.endingHours,
      garden: this.createForm.value.garden,
      grades: null,
      image: this.createForm.value.image,
      liveMusic: this.createForm.value.liveMusic,
      location: this.createForm.value.location,
      music: this.createForm.value.music,
      name: this.createForm.value.name,
      parking: this.createForm.value.parking,
      priceRange: this.createForm.value.priceRange,
      restaurant_id: 0,
      smokingArea: this.createForm.value.smokingArea,
      startingHours: this.createForm.value.startingHours,
      tv: this.createForm.value.tv,
      wifi: this.createForm.value.wifi
    };

    this.createForm.reset();

    this.addRestaurantsEvent.emit(dto);
  }
}
