package com.springboot;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Query("SELECT c FROM Category c JOIN c.product p WHERE p.product_name = :product_name")
    List<Category> findCategoriesByProductName(@Param("product_name") String product_name);
}
