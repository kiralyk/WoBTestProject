package com.kiraly93.wobdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kiraly93.wobdemo.repository.entity.MarketPlace;

@Repository
public interface MarketplaceRepository extends JpaRepository<MarketPlace, Integer>{

}
