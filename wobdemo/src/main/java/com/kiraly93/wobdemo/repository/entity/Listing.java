package com.kiraly93.wobdemo.repository.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "listings")
public class Listing {

	@Id
	@NotNull
	private String id;
	@NotNull
	private String title;
	@NotNull
	private String description;
	@NotNull
	private String inventoryItemLocationId;
	@Min(0)
	private float listingPrice;
	@Size(min = 3, max = 3)
	private String currency;
	@NotNull
	private int quantity;
	@NotNull
	@Min(1)
	private int listingStatus;
	@NotNull
	private int marketplace;
	@NotNull
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date uploadTime;
	@NotNull
	@Email
	private String ownerEmailAddress;

	public Listing(String id, String title, String description,
			@JsonProperty("location_id") String inventoryItemLocation,
			@JsonProperty("listing_price") float listingPrice, String currency, int quantity,
			@JsonProperty("listing_status") int listingStatus, int marketplace,
			@JsonProperty("upload_time") Date uploadTime,
			@JsonProperty("owner_email_address") String ownerEmailAddress) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.inventoryItemLocationId = inventoryItemLocation;
		this.listingPrice = listingPrice;
		this.currency = currency;
		this.quantity = quantity;
		this.listingStatus = listingStatus;
		this.marketplace = marketplace;
		this.uploadTime = uploadTime;
		this.ownerEmailAddress = ownerEmailAddress;
	}

	public Listing() {
	}

	public String getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	@JsonGetter("location_id")
	public String getInventoryItemLocationId() {
		return inventoryItemLocationId;
	}

	@JsonGetter("listing_price")
	public float getListingPrice() {
		return listingPrice;
	}

	public String getCurrency() {
		return currency;
	}

	public int getQuantity() {
		return quantity;
	}

	@JsonGetter("listing_status")
	public int getListingStatus() {
		return listingStatus;
	}

	@JsonGetter("marketplace")
	public int getMarketplace() {
		return marketplace;
	}

	@JsonGetter("upload_time")
	public Date getUploadTime() {
		return uploadTime;
	}

	@JsonGetter("owner_email_address")
	public String getOwnerEmailAddress() {
		return ownerEmailAddress;
	}

}
