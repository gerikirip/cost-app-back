package com.kiri.costappback.repo;

import com.kiri.costappback.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {
    Optional<Category> findCategoryByName(String name);
    List<Category> findAllByOrderById();
}
