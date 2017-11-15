package com.jacsonferreira.apimc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jacsonferreira.apimc.domain.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

}
