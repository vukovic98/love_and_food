package com.ftn.uns.ac.rs.love_and_food.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "restaurant_config")
public class RestaurantConfigDTO {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@Column(name = "ambient", nullable = false)
	private double ambientPoints;

	@Column(name = "smoking", nullable = false)
	private double smokingPoints;

	@Column(name = "alcohol", nullable = false)
	private double alcoholPoints;

	@Column(name = "date_time", nullable = false)
	private double dateTimePoints;

	@Column(name = "distance", nullable = false)
	private double distancePoints;

	@Column(name = "price_range", nullable = false)
	private double priceRangePoints;

	@Column(name = "music", nullable = false)
	private double musicPoints;

	@Column(name = "cuisine", nullable = false)
	private double cuisinePoints;

	@Column(name = "garden", nullable = false)
	private double gardenPoints;

	@Column(name = "parking", nullable = false)
	private double parkingPoints;

	@Column(name = "tv", nullable = false)
	private double tvPoints;

	@Column(name = "wifi", nullable = false)
	private double wifiPoints;

	@Column(name = "base_ambient", nullable = false)
	private double baseAmbientPoints;

	@Column(name = "base_smoking", nullable = false)
	private double baseSmokingPoints;

	@Column(name = "base_alcohol", nullable = false)
	private double baseAlcoholPoints;

	@Column(name = "base_date_time", nullable = false)
	private double baseDateTimePoints;

	@Column(name = "base_distance", nullable = false)
	private double baseDistancePoints;

	@Column(name = "base_price_range", nullable = false)
	private double basePriceRangePoints;

	@Column(name = "base_music", nullable = false)
	private double baseMusicPoints;

	@Column(name = "base_cuisine", nullable = false)
	private double baseCuisinePoints;

	@Column(name = "base_garden", nullable = false)
	private double baseGardenPoints;

	@Column(name = "base_parking", nullable = false)
	private double baseParkingPoints;

	@Column(name = "base_tv", nullable = false)
	private double baseTvPoints;

	@Column(name = "base_wifi", nullable = false)
	private double baseWifiPoints;

	@Column(name = "date", nullable = false)
	private Date date;

	public RestaurantConfigDTO() {
		super();
	}

	public RestaurantConfigDTO(double ambientPoints, double smokingPoints, double alcoholPoints, double dateTimePoints,
			double distancePoints, double priceRangePoints, double musicPoints, double cuisinePoints,
			double gardenPoints, double parkingPoints, double tvPoints, double wifiPoints, double baseAmbientPoints,
			double baseSmokingPoints, double baseAlcoholPoints, double baseDateTimePoints, double baseDistancePoints,
			double basePriceRangePoints, double baseMusicPoints, double baseCuisinePoints, double baseGardenPoints,
			double baseParkingPoints, double baseTvPoints, double baseWifiPoints) {
		super();
		this.ambientPoints = ambientPoints;
		this.smokingPoints = smokingPoints;
		this.alcoholPoints = alcoholPoints;
		this.dateTimePoints = dateTimePoints;
		this.distancePoints = distancePoints;
		this.priceRangePoints = priceRangePoints;
		this.musicPoints = musicPoints;
		this.cuisinePoints = cuisinePoints;
		this.gardenPoints = gardenPoints;
		this.parkingPoints = parkingPoints;
		this.tvPoints = tvPoints;
		this.wifiPoints = wifiPoints;
		this.baseAmbientPoints = baseAmbientPoints;
		this.baseSmokingPoints = baseSmokingPoints;
		this.baseAlcoholPoints = baseAlcoholPoints;
		this.baseDateTimePoints = baseDateTimePoints;
		this.baseDistancePoints = baseDistancePoints;
		this.basePriceRangePoints = basePriceRangePoints;
		this.baseMusicPoints = baseMusicPoints;
		this.baseCuisinePoints = baseCuisinePoints;
		this.baseGardenPoints = baseGardenPoints;
		this.baseParkingPoints = baseParkingPoints;
		this.baseTvPoints = baseTvPoints;
		this.baseWifiPoints = baseWifiPoints;
		this.date = new Date();
	}

	public double getAmbientPoints() {
		return ambientPoints;
	}

	public void setAmbientPoints(double ambientPoints) {
		this.ambientPoints = ambientPoints;
	}

	public double getSmokingPoints() {
		return smokingPoints;
	}

	public void setSmokingPoints(double smokingPoints) {
		this.smokingPoints = smokingPoints;
	}

	public double getAlcoholPoints() {
		return alcoholPoints;
	}

	public void setAlcoholPoints(double alcoholPoints) {
		this.alcoholPoints = alcoholPoints;
	}

	public double getDateTimePoints() {
		return dateTimePoints;
	}

	public void setDateTimePoints(double dateTimePoints) {
		this.dateTimePoints = dateTimePoints;
	}

	public double getDistancePoints() {
		return distancePoints;
	}

	public void setDistancePoints(double distancePoints) {
		this.distancePoints = distancePoints;
	}

	public double getPriceRangePoints() {
		return priceRangePoints;
	}

	public void setPriceRangePoints(double priceRangePoints) {
		this.priceRangePoints = priceRangePoints;
	}

	public double getMusicPoints() {
		return musicPoints;
	}

	public void setMusicPoints(double musicPoints) {
		this.musicPoints = musicPoints;
	}

	public double getCuisinePoints() {
		return cuisinePoints;
	}

	public void setCuisinePoints(double cuisinePoints) {
		this.cuisinePoints = cuisinePoints;
	}

	public double getGardenPoints() {
		return gardenPoints;
	}

	public void setGardenPoints(double gardenPoints) {
		this.gardenPoints = gardenPoints;
	}

	public double getParkingPoints() {
		return parkingPoints;
	}

	public void setParkingPoints(double parkingPoints) {
		this.parkingPoints = parkingPoints;
	}

	public double getTvPoints() {
		return tvPoints;
	}

	public void setTvPoints(double tvPoints) {
		this.tvPoints = tvPoints;
	}

	public double getWifiPoints() {
		return wifiPoints;
	}

	public void setWifiPoints(double wifiPoints) {
		this.wifiPoints = wifiPoints;
	}

	public double getBaseAmbientPoints() {
		return baseAmbientPoints;
	}

	public void setBaseAmbientPoints(double baseAmbientPoints) {
		this.baseAmbientPoints = baseAmbientPoints;
	}

	public double getBaseSmokingPoints() {
		return baseSmokingPoints;
	}

	public void setBaseSmokingPoints(double baseSmokingPoints) {
		this.baseSmokingPoints = baseSmokingPoints;
	}

	public double getBaseAlcoholPoints() {
		return baseAlcoholPoints;
	}

	public void setBaseAlcoholPoints(double baseAlcoholPoints) {
		this.baseAlcoholPoints = baseAlcoholPoints;
	}

	public double getBaseDateTimePoints() {
		return baseDateTimePoints;
	}

	public void setBaseDateTimePoints(double baseDateTimePoints) {
		this.baseDateTimePoints = baseDateTimePoints;
	}

	public double getBaseDistancePoints() {
		return baseDistancePoints;
	}

	public void setBaseDistancePoints(double baseDistancePoints) {
		this.baseDistancePoints = baseDistancePoints;
	}

	public double getBasePriceRangePoints() {
		return basePriceRangePoints;
	}

	public void setBasePriceRangePoints(double basePriceRangePoints) {
		this.basePriceRangePoints = basePriceRangePoints;
	}

	public double getBaseMusicPoints() {
		return baseMusicPoints;
	}

	public void setBaseMusicPoints(double baseMusicPoints) {
		this.baseMusicPoints = baseMusicPoints;
	}

	public double getBaseCuisinePoints() {
		return baseCuisinePoints;
	}

	public void setBaseCuisinePoints(double baseCuisinePoints) {
		this.baseCuisinePoints = baseCuisinePoints;
	}

	public double getBaseGardenPoints() {
		return baseGardenPoints;
	}

	public void setBaseGardenPoints(double baseGardenPoints) {
		this.baseGardenPoints = baseGardenPoints;
	}

	public double getBaseParkingPoints() {
		return baseParkingPoints;
	}

	public void setBaseParkingPoints(double baseParkingPoints) {
		this.baseParkingPoints = baseParkingPoints;
	}

	public double getBaseTvPoints() {
		return baseTvPoints;
	}

	public void setBaseTvPoints(double baseTvPoints) {
		this.baseTvPoints = baseTvPoints;
	}

	public double getBaseWifiPoints() {
		return baseWifiPoints;
	}

	public void setBaseWifiPoints(double baseWifiPoints) {
		this.baseWifiPoints = baseWifiPoints;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
