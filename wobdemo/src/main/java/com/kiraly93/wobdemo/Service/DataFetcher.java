package com.kiraly93.wobdemo.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.kiraly93.wobdemo.Model.InvalidListingField;
import com.kiraly93.wobdemo.repository.ListingRepository;
import com.kiraly93.wobdemo.repository.ListingStatusRepository;
import com.kiraly93.wobdemo.repository.LocationRepository;
import com.kiraly93.wobdemo.repository.MarketplaceRepository;
import com.kiraly93.wobdemo.repository.entity.Listing;
import com.kiraly93.wobdemo.repository.entity.ListingStatus;
import com.kiraly93.wobdemo.repository.entity.Location;
import com.kiraly93.wobdemo.repository.entity.MarketPlace;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

@Service
public class DataFetcher {

	private final static String LISTINGS_URL = "https://my.api.mockaroo.com/listing?key=63304c70";
	private final static String LISTINGS_STATUS_URL = "https://my.api.mockaroo.com/listingStatus?key=63304c70";
	private final static String LOCATION_URL = "https://my.api.mockaroo.com/location?key=63304c70";
	private final static String MARKETPACE_URL = "https://my.api.mockaroo.com/marketplace?key=63304c70";

	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private ListingsValidator validator;
	@Autowired
	private ListingRepository listingRepository;
	@Autowired
	private LocationRepository locationRepository;
	@Autowired
	private MarketplaceRepository marketplaceRepository;
	@Autowired
	private ListingStatusRepository listingStatusRepository;

	@Autowired
	private InvalidListingsHandler invalidListingHandler;

	public void initializeData() {
		initListingStatus();
		initLocation();
		initMarketplace();
		initListings();
	}

	private void initListings() {
		ResponseEntity<Listing[]> response = restTemplate.getForEntity(LISTINGS_URL, Listing[].class);
		Listing[] listings = response.getBody();
		ArrayList<Listing> listingsList = new ArrayList<>(Arrays.asList(listings));
		List<InvalidListingField> invalidFields = validator.validateListings(listingsList);
		try {
			invalidListingHandler.handleInvalidListings(invalidFields);
		} catch (CsvDataTypeMismatchException | CsvRequiredFieldEmptyException | IOException e) {
			e.printStackTrace();
		}
		listingRepository.saveAll(listingsList);
	}
	
	private void initListingStatus() {
		ResponseEntity<ListingStatus[]> response = restTemplate.getForEntity(LISTINGS_STATUS_URL, ListingStatus[].class);
		ListingStatus[] listingStatuses = response.getBody();
		listingStatusRepository.saveAll(Arrays.asList(listingStatuses));
	}
	
	private void initLocation() {
		ResponseEntity<Location[]> response = restTemplate.getForEntity(LOCATION_URL, Location[].class);
		Location[] locations = response.getBody();
		locationRepository.saveAll(Arrays.asList(locations));
	}
	
	private void initMarketplace() {
		ResponseEntity<MarketPlace[]> response = restTemplate.getForEntity(MARKETPACE_URL, MarketPlace[].class);
		MarketPlace[] marketplaces = response.getBody();
		marketplaceRepository.saveAll(Arrays.asList(marketplaces));
	}

}
