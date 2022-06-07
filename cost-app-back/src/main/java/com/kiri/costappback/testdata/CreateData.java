package com.kiri.costappback.testdata;

import com.kiri.costappback.model.Category;
import com.kiri.costappback.model.Cost;
import com.kiri.costappback.model.Mode;
import com.kiri.costappback.repo.CategoryRepo;
import com.kiri.costappback.repo.ModeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class CreateData {
    private final ModeRepo modeRepo;
    private final CategoryRepo categoryRepo;

    public Mode createMode (String name){
        Mode mode = new Mode();
        mode.setName(name);
        return mode;
    }

    public Category createCategory (String name, Long countCost){
        Category category = new Category();
        category.setName(name);
        category.setCountCost(countCost);
        return category;
    }

    public Cost createCost (String name, Long amount, Integer quantity, Date date, Mode mode, Category category ) {
        Cost cost = new Cost();
        cost.setName(name);
        cost.setAmount(amount);
        cost.setQuantity(quantity);
        cost.setTotalAmount(amount * quantity);
        cost.setDate(date);
        cost.setMode(modeRepo.findModeyByName(mode.getName()).orElseThrow());
        cost.setCategory(categoryRepo.findCategoryByName(category.getName()).orElseThrow());
        return cost;
    }
}
