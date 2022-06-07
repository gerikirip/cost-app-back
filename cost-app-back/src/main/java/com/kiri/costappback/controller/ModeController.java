package com.kiri.costappback.controller;

import com.kiri.costappback.model.Mode;
import com.kiri.costappback.service.ModeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/mode")
@RequiredArgsConstructor
public class ModeController {
    private final ModeService modeService;

    @GetMapping("/all")
    public ResponseEntity<List<Mode>> getAllMode(){
        List<Mode> modes = modeService.findAllMode();
        return new ResponseEntity<>(modes, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Mode> addMode(@RequestBody @Valid Mode mode){
        Mode newMode = modeService.addMode(mode);
        return new ResponseEntity<>(newMode, HttpStatus.CREATED);
    }
}
