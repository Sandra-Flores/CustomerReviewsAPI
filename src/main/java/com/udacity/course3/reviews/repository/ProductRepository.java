package com.udacity.course3.reviews.repository;

import com.udacity.course3.reviews.entity.Product;

import com.udacity.course3.reviews.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ProductRepository
 */

@Repository
public interface ProductRepository extends JpaRepository <Product, Integer> {

    //Find product by name

    List<Product> findByName(String name);
}