package com.producer.api.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="Address")
public class Address {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="Address_Type")
	private String addressType;
	
    public String getAddressType() {
		return addressType;
	}

	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}

	@NotNull
    @Size(max = 100)
    @Column(name="addressLine1")
    private String addressLine1;

    @NotNull
    @Size(max = 100)
    @Column(name="addressLine2")
    private String addressLine2;
    
    @ManyToOne(cascade= {CascadeType.ALL})
    @JoinColumn(name="user_id")
    private User user;
    
    public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	@Embedded
    private ExternalAddress externalAddress;
 
    public Address() {

    }

    public ExternalAddress getExternalAddress() {
		return externalAddress;
	}

	public void setExternalAddress(ExternalAddress externalAddress) {
		this.externalAddress = externalAddress;
	}

	public Address(String addressLine1, String addressLine2, String addressType, ExternalAddress addressCode, User user) {
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.externalAddress=addressCode;
        this.addressType=addressType;
        this.user=user;
       
    }

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	
    
}
