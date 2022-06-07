package com.kiri.costappback.service;

import com.kiri.costappback.model.Cost;
import com.kiri.costappback.dto.AddCostRequest;
import com.kiri.costappback.transformer.AddCostRequestTransformer;
import com.kiri.costappback.repo.CostRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class CostService {
    private final CostRepo costRepo;
    private final AddCostRequestTransformer addCostRequestTransformer;

    public List<Cost> findAllCost() {
        log.info("Fetching all costs");
        return costRepo.findAll();
    }

    public Cost addCost(AddCostRequest addCost) {
        log.info("Saving new cost: cost_name: {} ", addCost.getName());
        Cost cost = addCostRequestTransformer.addCostResponseTransform(addCost);
        Cost saveIfEqual = costEqualsWithoutQuantity(cost);
        if (saveIfEqual != null) {
            cost = saveIfEqual;
        }
        cost.setTotalAmount(cost.getAmount() * cost.getQuantity());
        return costRepo.save(cost);
    }

    public Cost costEqualsWithoutQuantity(Cost cost) {
        System.out.println("cost: " + cost);

        List<Cost> foundCosts = costRepo.findCostByName(cost.getName());
        if (foundCosts != null) {
            SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");
            for (Cost foundCost : foundCosts) {
                System.out.println("foundCost: " + foundCost);

                if (foundCost.getName().equals(cost.getName())
                        && foundCost.getCategory().getId().equals(cost.getCategory().getId())
                        && simpleDate.format(foundCost.getDate()).equals(simpleDate.format(cost.getDate()))
                        && foundCost.getAmount().equals(cost.getAmount())
                        && foundCost.getMode().getId().equals(cost.getMode().getId())) {
                    System.out.println("beléptem a forba!");
                    foundCost.setQuantity(foundCost.getQuantity() + cost.getQuantity());
                    foundCost.setTotalAmount(foundCost.getAmount() * foundCost.getQuantity());
                    return foundCost;
                }
            }
        }
        return null;
    }

    public void deleteCost(Long id) {
        log.info("Deleting cost by id: {}", id);
        costRepo.findById(id).ifPresent(value -> {
            if (value.getCategory().getCountCost() - value.getQuantity() > 0) {
                value.getCategory().setCountCost(value.getCategory().getCountCost() - value.getQuantity());
            } else {
                value.getCategory().setCountCost(0L);
            }
        });
        costRepo.deleteById(id);
    }
}
