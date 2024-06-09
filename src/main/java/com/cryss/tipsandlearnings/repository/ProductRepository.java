package com.cryss.tipsandlearnings.repository;

import com.cryss.tipsandlearnings.model.recursiveentity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
