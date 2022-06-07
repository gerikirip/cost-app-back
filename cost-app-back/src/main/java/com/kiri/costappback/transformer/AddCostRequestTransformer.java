package com.kiri.costappback.transformer;

import com.kiri.costappback.dto.AddCostRequest;
import com.kiri.costappback.model.Category;
import com.kiri.costappback.model.Cost;
import com.kiri.costappback.model.Mode;
import com.kiri.costappback.repo.CategoryRepo;
import com.kiri.costappback.repo.ModeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddCostRequestTransformer {
    private final CategoryRepo categoryRepo;
    private final ModeRepo modeRepo;

    public Cost addCostResponseTransform(AddCostRequest addCost){
        Cost cost = new Cost();

        Optional<Category> category = categoryRepo.findById(addCost.getCategoryId());
        cost.setCategory(category.orElseThrow());

        Optional<Mode> mode = modeRepo.findById(addCost.getModeId());
        cost.setMode(mode.orElseThrow());

        cost.setAmount(addCost.getAmount());
        cost.setQuantity(addCost.getQuantity());
        cost.setDate(addCost.getDate());
        cost.setName(addCost.getName().trim().replaceAll("\\s+"," "));
        cost.getCategory().setCountCost(cost.getCategory().getCountCost() + cost.getQuantity());

        return cost;
    }
}
