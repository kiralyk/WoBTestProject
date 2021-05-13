package com.kiraly93.wobdemo.repository;

import java.util.List;

import javax.persistence.Tuple;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kiraly93.wobdemo.repository.entity.Listing;

@Repository
public interface ListingRepository extends JpaRepository<Listing, String> {

	@Query(value = "SELECT marketplace, sum(listing_price), avg(listing_price), count(listing_price) FROM listings group by marketplace", nativeQuery = true)
	List<Tuple> getAllReportByMarketPlace();

	@Query(value = "SELECT marketplace, date_trunc('month', upload_time) as date, sum(listing_price), avg(listing_price), count(listing_price) AS count FROM listings group by date, marketplace order by date", nativeQuery = true)
	List<Tuple> getMonthlyReportByMarketPlace();

}
