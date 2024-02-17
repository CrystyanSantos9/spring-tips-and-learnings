package com.cryss.tipsandlearnings.repository;

import com.cryss.tipsandlearnings.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
