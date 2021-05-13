package com.kiraly93.wobdemo.repository.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "listingstatus")
public class ListingStatus {

	@Id
	private int id;
	private String statusName;

	public ListingStatus() {
	}

	public ListingStatus(int id, @JsonProperty("status_name") String statusName) {
		this.id = id;
		this.statusName = statusName;
	}

	public int getId() {
		return id;
	}

	public String getStatusName() {
		return statusName;
	}

}
