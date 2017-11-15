package com.jacsonferreira.apimc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jacsonferreira.apimc.domain.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

}
