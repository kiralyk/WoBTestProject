package com.kiraly93.wobdemo.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Tuple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kiraly93.wobdemo.Model.MarketplaceEnum;
import com.kiraly93.wobdemo.repository.ListingRepository;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

@Service
public class ListingsReportMaker {

	private static DecimalFormat df = new DecimalFormat("0.00");
	private static SimpleDateFormat formatter = new SimpleDateFormat("MM.yyyy");

	@Autowired
	private ListingRepository listingRepository;
	@Autowired
	FileServiceImpl fileService;

	private JSONArray allReportJsonArray = new JSONArray();

	public void makeReport() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("Report", allReportJsonArray);
		createTotalReportArray();
		createMonthlyReportArray();
		fileService.upload(jsonObject);
	}

	private void createMonthlyReportArray() {
		List<Tuple> totalPriceByMarketAndDate = listingRepository.getMonthlyReportByMarketPlace();
		JSONArray monthlyReportArray = new JSONArray();
		Date previousDate = null;
		for (Tuple tuple : totalPriceByMarketAndDate) {
			Date date = (Date) tuple.get(1);
			if (!date.equals(previousDate) && previousDate != null) {
				addMonthlyReportToArray(previousDate, monthlyReportArray);
				monthlyReportArray = new JSONArray();
			}
			BigDecimal totalListingPrice = (BigDecimal) tuple.get(2);
			BigDecimal monthlyAvgPrice = (BigDecimal) tuple.get(3);
			BigInteger monthlyListingCount = (BigInteger) tuple.get(4);
			MarketplaceEnum marketplace = MarketplaceEnum.getById((int) tuple.get(0));
			monthlyReportArray.add(marketplace + " monthly total listing price: " + totalListingPrice);
			monthlyReportArray.add(marketplace + " monthly average price: " + df.format(monthlyAvgPrice));
			monthlyReportArray.add(marketplace + " monthly listing count: " + monthlyListingCount);
			previousDate = date;

		}
		addMonthlyReportToArray(previousDate, monthlyReportArray);
	}

	private void createTotalReportArray() {
		List<Tuple> allReportByMarketPlace = listingRepository.getAllReportByMarketPlace();
		for (Tuple tuple : allReportByMarketPlace) {
			JSONArray allReportArray = new JSONArray();
			BigDecimal totalListingPrice = (BigDecimal) tuple.get(1);
			BigDecimal totalAvgPrice = (BigDecimal) tuple.get(2);
			BigInteger totalListingCount = (BigInteger) tuple.get(3);
			allReportArray.add("total listing price: " + totalListingPrice);
			allReportArray.add("listing price average: " + df.format(totalAvgPrice));
			allReportArray.add("listing count: " + totalListingCount);
			JSONObject allReportObject = new JSONObject();
			MarketplaceEnum marketplace = MarketplaceEnum.getById((int) tuple.get(0));
			allReportObject.put(marketplace.toString(), allReportArray);
			allReportJsonArray.add(allReportObject);
		}
	}

	private void addMonthlyReportToArray(Date date, JSONArray reportToAdd) {
		JSONObject monthlyReportObject = new JSONObject();
		monthlyReportObject.put(formatter.format(date), reportToAdd);
		allReportJsonArray.add(monthlyReportObject);
	}

}
