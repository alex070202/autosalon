package com.autosalon.autosalon.controller;

import com.autosalon.autosalon.dto.CarRequestDTO;
import com.autosalon.autosalon.dto.CarResponseDTO;
import com.autosalon.autosalon.dto.CarStatusUpdateDTO;
import com.autosalon.autosalon.model.BodyType;
import com.autosalon.autosalon.model.CarStatus;
import com.autosalon.autosalon.model.Color;
import com.autosalon.autosalon.model.FuelType;
import com.autosalon.autosalon.service.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/cars")
@CrossOrigin
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    // ======================
    // GET ALL CARS
    // ======================
    @GetMapping
    public ResponseEntity<List<CarResponseDTO>> getAllCars() {
        return ResponseEntity.ok(carService.getAllCars());
    }

    // ======================
    // GET CAR BY ID
    // ======================
    @GetMapping("/{id}")
    public ResponseEntity<CarResponseDTO> getCarById(@PathVariable Long id) {
        return ResponseEntity.ok(carService.getCarById(id));
    }

    // ======================
    // CREATE CAR
    // ======================
    @PostMapping
    public ResponseEntity<CarResponseDTO> createCar(
            @RequestBody CarRequestDTO requestDTO) {

        CarResponseDTO createdCar = carService.createCar(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCar);
    }

    // ======================
    // UPDATE CAR
    // ======================
    @PutMapping("/{id}")
    public ResponseEntity<CarResponseDTO> updateCar(
        @PathVariable Long id,
        @RequestBody CarRequestDTO requestDTO) {

    return ResponseEntity.ok(carService.updateCar(id, requestDTO));
    }

    // ======================
    // GET CARS BY BRANDID
    // ======================
    @GetMapping("/brand/{brandId}")
    public ResponseEntity<List<CarResponseDTO>> getCarsByBrand(
        @PathVariable Long brandId) {

    return ResponseEntity.ok(carService.getCarsByBrand(brandId));
    }
    // GET CARS BY BRANDName
    @GetMapping("/brand-name/{brandName}")
    public ResponseEntity<List<CarResponseDTO>> getCarsByBrandName(
        @PathVariable String brandName
    ) {
        return ResponseEntity.ok(carService.getCarsByBrandName(brandName));
    }
    // ======================
    // GET CARS status
    // ======================

    @GetMapping("/status/{status}")
    public ResponseEntity<List<CarResponseDTO>> getCarsByStatus(
        @PathVariable CarStatus status
    ) {
        return ResponseEntity.ok(carService.getCarsByStatus(status));
    }

    @GetMapping("/filter")
    public ResponseEntity<List<CarResponseDTO>> filterCars(
        @RequestParam(required = false) BigDecimal minPrice,
        @RequestParam(required = false) BigDecimal maxPrice,
        @RequestParam(required = false) FuelType fuelType,
        @RequestParam(required = false) BodyType bodyType,
        @RequestParam(required = false) Color color,
        @RequestParam(required = false) String brandName
    ) {
      return ResponseEntity.ok(
            carService.filterCars(minPrice, maxPrice, fuelType, bodyType, color,brandName)
    );
    }

    // ======================
    // DELETE CAR
    // ======================
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
    carService.deleteCar(id);
    return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<CarResponseDTO> updateCarStatus(
        @PathVariable Long id,
        @RequestBody CarStatusUpdateDTO dto
    ) {
        return ResponseEntity.ok(
            carService.updateCarStatus(id, dto.getStatus())
        );
    }

}
