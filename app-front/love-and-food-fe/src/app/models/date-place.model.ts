import {RestaurantModel} from "./restaurant.model";

export interface DatePlaceModel {
  restaurant: RestaurantModel,
  date: string,
  initiator: string,
  soulmate: string
}
