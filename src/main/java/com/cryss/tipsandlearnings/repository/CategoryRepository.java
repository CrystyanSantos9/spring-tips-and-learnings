package com.cryss.tipsandlearnings.repository;

import com.cryss.tipsandlearnings.model.Cart;
import com.cryss.tipsandlearnings.model.CartItemsProjection;
import com.cryss.tipsandlearnings.model.recursiveentity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
