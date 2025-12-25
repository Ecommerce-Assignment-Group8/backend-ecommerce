package com.example.backend.controller;

import com.example.backend.entity.Package;
import com.example.backend.repository.PackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/packages")
public class PackageController {

    @Autowired
    private PackageRepository packageRepository;

    @GetMapping("/trainer/{trainerId}")
    public ResponseEntity<List<Package>> getPackagesByTrainer(@PathVariable Integer trainerId) {
        return ResponseEntity.ok(packageRepository.findByTrainerId(trainerId));
    }
}
