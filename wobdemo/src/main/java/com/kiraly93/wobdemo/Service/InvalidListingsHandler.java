package com.kiraly93.wobdemo.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.kiraly93.wobdemo.Model.InvalidListingField;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

@Service
public class InvalidListingsHandler {

	public void handleInvalidListings(List<InvalidListingField> invalidListingFields)
			throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
		FileWriter writer = new FileWriter("importLog.csv");
		StatefulBeanToCsvBuilder<InvalidListingField> builder = new StatefulBeanToCsvBuilder<InvalidListingField>(
				writer);
		ColumnPositionMappingStrategy<InvalidListingField> mappingStrategy = new ColumnPositionMappingStrategy<InvalidListingField>();
		mappingStrategy.setType(InvalidListingField.class);

		String[] columns = new String[] { "ListingId", "MarketPlaceName", "InvalidField" };
		mappingStrategy.setColumnMapping(columns);
		StatefulBeanToCsv<InvalidListingField> beanWriter = builder.withMappingStrategy(mappingStrategy).build();
		beanWriter.write(invalidListingFields);
	}

}
