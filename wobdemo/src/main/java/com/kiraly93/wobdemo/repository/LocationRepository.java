package com.kiraly93.wobdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kiraly93.wobdemo.repository.entity.Location;
@Repository
public interface LocationRepository extends JpaRepository<Location, Integer>{

}
