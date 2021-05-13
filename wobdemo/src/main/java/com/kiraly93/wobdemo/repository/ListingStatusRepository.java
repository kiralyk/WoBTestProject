package com.kiraly93.wobdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kiraly93.wobdemo.repository.entity.ListingStatus;

@Repository
public interface ListingStatusRepository extends JpaRepository<ListingStatus, Integer>{

}
