package com.kiri.costappback.service;

import com.kiri.costappback.dto.AddCategoryRequest;
import com.kiri.costappback.model.Category;
import com.kiri.costappback.model.Cost;
import com.kiri.costappback.repo.CategoryRepo;
import com.kiri.costappback.repo.CostRepo;
import com.kiri.costappback.transformer.AddCategoryRequestTransformer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class CategoryService {
    private final CategoryRepo categoryRepo;
    private final CostRepo costRepo;
    private final AddCategoryRequestTransformer addCategoryRequestTransformer;

    public List<Category> findAllCategory(){
        log.info("Fetching all category");
        return categoryRepo.findAllByOrderById();
    }

    public Category addCategory(AddCategoryRequest addCategoryRequest){
        log.info("Saving new category: {}", addCategoryRequest.getName());
        return categoryRepo.save(addCategoryRequestTransformer.addCategoryResponseTransform(addCategoryRequest));
    }

    public void deleteCategory(Long id){
        log.info("Deleting category by Id: {}", id);
        Optional<Category> category = categoryRepo.findById(id);
        category.ifPresent(cat -> {
            List<Cost> costs = costRepo.findCostByCategory(cat);
            costs.forEach(cost -> costRepo.deleteById(cost.getId()));
        });
        categoryRepo.deleteById(id);
    }
}
