package com.jacsonferreira.apimc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.jacsonferreira.apimc.domain.City;

@Repository
public interface CityRepositoriy extends JpaRepository<City, Integer> {

}