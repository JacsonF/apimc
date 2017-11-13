package com.jacsonferreira.apimc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jacsonferreira.apimc.domain.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
