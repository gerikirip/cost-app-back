package com.kiri.costappback.repo;

import com.kiri.costappback.model.Category;
import com.kiri.costappback.model.Cost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CostRepo extends JpaRepository<Cost, Long> {
    List<Cost> findCostByName(String name);
    List<Cost> findCostByCategory(Category category);
}
