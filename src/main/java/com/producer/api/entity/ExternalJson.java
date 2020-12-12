package com.producer.api.entity;

public class ExternalJson {
	
	private String city;
	
	private String region_name;
	
	private String country_name;
	
	private String zip;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getRegion_name() {
		return region_name;
	}

	public void setRegion_name(String region_name) {
		this.region_name = region_name;
	}

	public String getCountry_name() {
		return country_name;
	}

	public void setCountry_name(String country_name) {
		this.country_name = country_name;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public ExternalJson(String city, String region_name, String country_name, String zip) {
		super();
		this.city = city;
		this.region_name = region_name;
		this.country_name = country_name;
		this.zip = zip;
	}

	public ExternalJson() {
		
	}

}
