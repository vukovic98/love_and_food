import {GradeModel} from "./grade.model";

export interface RestaurantModel {
  restaurant_id: number,
  name: string,
  location: string,
  startingHours: string,
  endingHours: string,
  ambient: string,
  music: string,
  cuisine: Array<string>,
  priceRange: string,
  garden: boolean,
  wifi: boolean,
  tv: boolean,
  liveMusic: boolean,
  alcohol: boolean,
  parking: boolean,
  smokingArea: boolean,
  image: string,
  grades: Array<GradeModel>
}
