package com.kiri.costappback.testdata;


import com.kiri.costappback.model.Category;
import com.kiri.costappback.model.Cost;
import com.kiri.costappback.model.Mode;
import com.kiri.costappback.repo.CategoryRepo;
import com.kiri.costappback.repo.CostRepo;
import com.kiri.costappback.repo.ModeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RequiredArgsConstructor
@Component
public class TestDataGen {
    private final CreateData createData;
    private final ModeRepo modeRepo;
    private final CategoryRepo categoryRepo;
    private final CostRepo costRepo;

    @Transactional
    public void createTestData() throws ParseException {
        Mode cashMode = createData.createMode("készpénzes");
        Mode cardMode = createData.createMode("bankártyás");
        modeRepo.save(cashMode);
        modeRepo.save(cardMode);

        Category animal = createData.createCategory("állatok", 0L);
        Category food = createData.createCategory("élelmiszer", 10L);
        Category clothing = createData.createCategory("ruházat", 2L);
        Category travel = createData.createCategory("közlekedés", 0L);
        Category entertainment = createData.createCategory("szórakozás", 0L);
        Category any = createData.createCategory("egyéb", 0L);
        categoryRepo.save(animal);
        categoryRepo.save(food);
        categoryRepo.save(clothing);
        categoryRepo.save(travel);
        categoryRepo.save(entertainment);
        categoryRepo.save(any);


        Cost tshirt = createData.createCost("póló", 2000L, 2, new SimpleDateFormat("yyyy-MM-dd").parse("2022-05-11"), cashMode, clothing);
        Cost cheese = createData.createCost("sajt", 650L, 10, new SimpleDateFormat("yyyy-MM-dd").parse("2022-05-11"), cashMode, food);
        costRepo.save(tshirt);
        costRepo.save(cheese);


    }
}
