package com.kiri.costappback.controller;

import com.kiri.costappback.model.Cost;
import com.kiri.costappback.dto.AddCostRequest;
import com.kiri.costappback.service.CostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cost")
@RequiredArgsConstructor
public class CostController {
    private final CostService costService;

    @GetMapping("/all")
    public ResponseEntity<List<Cost>> getAllCost(){
        List<Cost> costs = costService.findAllCost();
        return new ResponseEntity<>(costs, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Cost> addCost(@RequestBody AddCostRequest addCostRequest){
        Cost newCost = costService.addCost(addCostRequest);
        return new ResponseEntity<>(newCost, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCost(@PathVariable("id") Long id){
        costService.deleteCost(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
