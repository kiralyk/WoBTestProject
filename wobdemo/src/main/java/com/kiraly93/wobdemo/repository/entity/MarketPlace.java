package com.kiraly93.wobdemo.repository.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "marketplace")
public class MarketPlace {

	@Id
	private int id;
	private String marketplaceName;

	public MarketPlace() {
	}

	public MarketPlace(int id, @JsonProperty("marketplace_name") String marketplaceName) {
		this.id = id;
		this.marketplaceName = marketplaceName;
	}

	public int getId() {
		return id;
	}

	@JsonGetter("marketplace_name")
	public String getMarketplaceName() {
		return marketplaceName;
	}

}
