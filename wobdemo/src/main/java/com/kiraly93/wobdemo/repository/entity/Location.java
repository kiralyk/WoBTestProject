package com.kiraly93.wobdemo.repository.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "location")
public class Location {

	@Id
	private String id;
	private String managerName;
	private String phone;
	@Column(name = "address_primary")
	private String primaryAddress;
	@Column(name = "address_secondary")
	private String secondaryAddress;
	private String country;
	private String town;
	private String postalCode;

	public Location() {
	}

	public Location(String id, @JsonProperty("manager_name") String managerName, String phone,
			@JsonProperty("address_primary") String primaryAddress,
			@JsonProperty("address_secondary") String secondaryAddress, String country, String town,
			@JsonProperty("postal_code") String postalCode) {
		this.id = id;
		this.managerName = managerName;
		this.phone = phone;
		this.primaryAddress = primaryAddress;
		this.secondaryAddress = secondaryAddress;
		this.country = country;
		this.town = town;
		this.postalCode = postalCode;
	}

	public String getId() {
		return id;
	}

	@JsonGetter("manager_name")
	public String getManagerName() {
		return managerName;
	}

	public String getPhone() {
		return phone;
	}

	@JsonGetter("address_primary")
	public String getPrimaryAddress() {
		return primaryAddress;
	}

	@JsonGetter("address_secondary")
	public String getSecondaryAddress() {
		return secondaryAddress;
	}

	public String getCountry() {
		return country;
	}

	public String getTown() {
		return town;
	}

	@JsonGetter("postal_code")
	public String getPostalCode() {
		return postalCode;
	}

}
