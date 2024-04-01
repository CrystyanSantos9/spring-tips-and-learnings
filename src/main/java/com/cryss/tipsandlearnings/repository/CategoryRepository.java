package com.cryss.tipsandlearnings.repository;

import com.cryss.tipsandlearnings.model.recursiveentity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
