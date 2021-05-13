package com.kiraly93.wobdemo.Model;

import java.util.Objects;

public class InvalidListingField {

	private String listingId;
	private String marketPlaceName;
	private String invalidField;

	public InvalidListingField(String listingId, String marketPlaceName, String invalidField) {
		this.listingId = listingId;
		this.marketPlaceName = marketPlaceName;
		this.invalidField = invalidField;
	}

	public String getListingId() {
		return listingId;
	}

	public String getMarketPlaceName() {
		return marketPlaceName;
	}

	public String getInvalidField() {
		return invalidField;
	}

	@Override
	public int hashCode() {
		return Objects.hash(invalidField, listingId, marketPlaceName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof InvalidListingField)) {
			return false;
		}
		InvalidListingField other = (InvalidListingField) obj;
		return Objects.equals(invalidField, other.invalidField) && Objects.equals(listingId, other.listingId)
				&& Objects.equals(marketPlaceName, other.marketPlaceName);
	}

	@Override
	public String toString() {
		return "InvalidListingField [listingId=" + listingId + ", marketPlaceName=" + marketPlaceName
				+ ", invalidField=" + invalidField + "]";
	}

}
