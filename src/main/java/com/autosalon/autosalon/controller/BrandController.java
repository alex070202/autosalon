package com.autosalon.autosalon.controller;

import com.autosalon.autosalon.dto.BrandResponseDTO;
import com.autosalon.autosalon.dto.CarResponseDTO;
import com.autosalon.autosalon.repository.BrandRepository;
import com.autosalon.autosalon.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/brands")
@RequiredArgsConstructor
public class BrandController {

    private final BrandRepository brandRepository;
    private final CarRepository carRepository;

    @GetMapping
    public List<BrandResponseDTO> getAllBrands() {
        return brandRepository.findAll()
                .stream()
                .map(BrandResponseDTO::fromEntity)
                .toList();
    }

    @GetMapping("/{brandId}/cars")
    public List<CarResponseDTO> getCarsByBrand(@PathVariable Long brandId) {
        return carRepository.findByBrand_Id(brandId)
                .stream()
                .map(CarResponseDTO::fromEntity)
                .toList();
    }
}
