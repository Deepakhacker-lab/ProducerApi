package com.producer.api.entity;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Embeddable
public class ExternalAddress {
	

	   @NotNull
	    @Size(max = 100)
	    private String city;

	    @NotNull
	    @Size(max = 100)
	    private String state;

	    @NotNull
	    @Size(max = 100)
	    private String country;

	    @NotNull
	    @Size(max = 6)
	    private String zipCode;


public ExternalAddress(String city, String state,String country,String zipCode) {
	 this.city = city;
     this.state = state;
     this.country = country;
     this.zipCode = zipCode;
}
	    
	public String getCity() {
	return city;
}

public void setCity(String city) {
	this.city = city;
}

public String getState() {
	return state;
}

public void setState(String state) {
	this.state = state;
}

public String getCountry() {
	return country;
}

public void setCountry(String country) {
	this.country = country;
}

public String getZipCode() {
	return zipCode;
}

public void setZipCode(String zipCode) {
	this.zipCode = zipCode;
}

	public ExternalAddress() {
		
	}

}
