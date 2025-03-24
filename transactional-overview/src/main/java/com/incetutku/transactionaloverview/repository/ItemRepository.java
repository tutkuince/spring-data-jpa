package com.incetutku.transactionaloverview.repository;

import com.incetutku.transactionaloverview.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
