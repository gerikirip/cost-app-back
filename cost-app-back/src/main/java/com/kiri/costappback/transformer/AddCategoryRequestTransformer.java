package com.kiri.costappback.transformer;

import com.kiri.costappback.dto.AddCategoryRequest;
import com.kiri.costappback.model.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddCategoryRequestTransformer {

    public Category addCategoryResponseTransform(AddCategoryRequest addCategoryRequest) {
        Category category = new Category();
        category.setName(addCategoryRequest.getName().trim().replaceAll("\\s+"," "));
        category.setCountCost(0L);
        return category;
    }
}



