package com.kiri.costappback.service;

import com.kiri.costappback.model.Mode;
import com.kiri.costappback.repo.ModeRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ModeService {
    private final ModeRepo modeRepo;

    public List<Mode> findAllMode(){
        log.info("Fetching all modes");
        return modeRepo.findAll();
    }

    public Mode addMode(Mode mode){
        log.info("Saving new mode: {}", mode.getName());
        return modeRepo.save(mode);
    }
}
