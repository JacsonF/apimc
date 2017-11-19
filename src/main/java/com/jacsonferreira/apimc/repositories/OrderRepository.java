package com.jacsonferreira.apimc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jacsonferreira.apimc.domain.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

}
