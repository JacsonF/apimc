package com.jacsonferreira.apimc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jacsonferreira.apimc.domain.ItemOrder;

@Repository
public interface ItemOrderRepository extends JpaRepository<ItemOrder, Integer> {

}
