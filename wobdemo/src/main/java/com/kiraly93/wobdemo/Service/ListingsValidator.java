package com.kiraly93.wobdemo.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kiraly93.wobdemo.Model.InvalidListingField;
import com.kiraly93.wobdemo.Model.MarketplaceEnum;
import com.kiraly93.wobdemo.repository.entity.Listing;

@Service
public class ListingsValidator {

	@Autowired
	private Validator validator;

	public List<InvalidListingField> validateListings(List<Listing> listings) {
		List<InvalidListingField> invalidListingFields = new ArrayList<>();
		List<Listing> invalidListings = new ArrayList<>();
		for (Listing listing : listings) {
			Set<ConstraintViolation<Listing>> violations = validator.validate(listing);
			if (!violations.isEmpty()) {
				invalidListings.add(listing);
			}
			for (ConstraintViolation<Listing> violation : violations) {
				MarketplaceEnum marketplaceEnum = MarketplaceEnum.getById(listing.getMarketplace());
				InvalidListingField invalidListingField = new InvalidListingField(listing.getId(), marketplaceEnum.getName(), violation.getPropertyPath().toString());
				invalidListingFields.add(invalidListingField);
			}
		}
		listings.removeAll(invalidListings);
		return invalidListingFields;
	}
}
