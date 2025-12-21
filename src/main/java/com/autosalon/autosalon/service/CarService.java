package com.autosalon.autosalon.service;

import com.autosalon.autosalon.dto.*;
import com.autosalon.autosalon.model.*;
import com.autosalon.autosalon.repository.BrandRepository;
import com.autosalon.autosalon.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CarService {

        private final CarRepository carRepository;
        private final BrandRepository brandRepository;

        public CarService(CarRepository carRepository, BrandRepository brandRepository) {
                this.carRepository = carRepository;
                this.brandRepository = brandRepository;
        }

        // CREATE
        public CarResponseDTO createCar(CarRequestDTO dto) {

                Brand brand = brandRepository.findById(dto.getBrandId())
                                .orElseThrow(() -> new RuntimeException("Brand not found"));

                Car car = Car.builder()
                                .model(dto.getModel())
                                .year(dto.getYear())
                                .mileage(dto.getMileage())
                                .price(dto.getPrice())
                                .fuelType(dto.getFuelType())
                                .bodyType(dto.getBodyType())
                                .transmission(dto.getTransmission())
                                .horsePower(dto.getHorsePower())
                                .color(dto.getColor())
                                .brand(brand)
                                .build();

                Car savedCar = carRepository.save(car);
                return mapToResponseDTO(savedCar);
        }

        // GET ALL
        public List<CarResponseDTO> getAllCars() {
                return carRepository.findAll()
                                .stream()
                                .map(this::mapToResponseDTO)
                                .toList();
        }

        // GET BY BRANDname
        public List<CarResponseDTO> getCarsByBrandName(String brandName) {

                Brand brand = brandRepository.findByNameIgnoreCase(brandName.trim())
                                .orElseThrow(() -> new RuntimeException("Brand not found"));

                return carRepository.findByBrand_Id(brand.getId())
                                .stream()
                                .map(this::mapToResponseDTO)
                                .toList();
        }

        // GET BY ID
        public CarResponseDTO getCarById(Long id) {
                Car car = carRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Car not found"));

                return mapToResponseDTO(car);
        }

        public CarResponseDTO updateCar(Long id, CarRequestDTO dto) {

                Car car = carRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Car not found"));

                Brand brand = brandRepository.findById(dto.getBrandId())
                                .orElseThrow(() -> new RuntimeException("Brand not found"));

                car.setModel(dto.getModel());
                car.setYear(dto.getYear());
                car.setMileage(dto.getMileage());
                car.setPrice(dto.getPrice());
                car.setFuelType(dto.getFuelType());
                car.setBodyType(dto.getBodyType());
                car.setTransmission(dto.getTransmission());
                car.setHorsePower(dto.getHorsePower());
                car.setColor(dto.getColor());
                car.setBrand(brand);

                return mapToResponseDTO(carRepository.save(car));
        }

        public void deleteCar(Long id) {
                if (!carRepository.existsById(id)) {
                        throw new RuntimeException("Car not found");
                }
                carRepository.deleteById(id);
        }

        public List<CarResponseDTO> getCarsByBrand(Long brandId) {

                return carRepository.findByBrand_Id(brandId)
                                .stream()
                                .map(this::mapToResponseDTO)
                                .toList();
        }

        public List<CarResponseDTO> filterCars(
                        BigDecimal minPrice,
                        BigDecimal maxPrice,
                        FuelType fuelType,
                        BodyType bodyType,
                        Color color,
                        String brandName) {
                List<Car> cars;

                // ✅ BRAND FILTER
                if (brandName != null && !brandName.isBlank()) {
                        Brand brand = brandRepository.findByNameIgnoreCase(brandName.trim())
                                        .orElseThrow(() -> new RuntimeException("Brand not found"));

                        cars = carRepository.findByBrand_Id(brand.getId());
                } else {
                        cars = carRepository.findAll();
                }

                // PRICE
                if (minPrice != null && maxPrice != null) {
                        cars = cars.stream()
                                        .filter(c -> c.getPrice().compareTo(minPrice) >= 0 &&
                                                        c.getPrice().compareTo(maxPrice) <= 0)
                                        .toList();
                }

                // FUEL
                if (fuelType != null) {
                        cars = cars.stream()
                                        .filter(c -> c.getFuelType() == fuelType)
                                        .toList();
                }

                // BODY
                if (bodyType != null) {
                        cars = cars.stream()
                                        .filter(c -> c.getBodyType() == bodyType)
                                        .toList();
                }

                // COLOR
                if (color != null) {
                        cars = cars.stream()
                                        .filter(c -> c.getColor() == color)
                                        .toList();
                }

                return cars.stream()
                                .map(this::mapToResponseDTO)
                                .toList();
        }

        // --------------------
        // MAPPING
        // --------------------
        private CarResponseDTO mapToResponseDTO(Car car) {

                return CarResponseDTO.builder()
                                .id(car.getId())
                                .model(car.getModel())
                                .year(car.getYear())
                                .mileage(car.getMileage())
                                .price(car.getPrice())
                                .fuelType(car.getFuelType())
                                .bodyType(car.getBodyType())
                                .transmission(car.getTransmission())
                                .horsePower(car.getHorsePower())
                                .color(car.getColor())
                                .status(car.getStatus())
                                .brandId(car.getBrand().getId())
                                .brandName(car.getBrand().getName())
                                .build();
        }

        public List<CarResponseDTO> getCarsByStatus(CarStatus status) {
                return carRepository.findByStatus(status)
                .stream()
                .map(this::mapToResponseDTO)
                .toList();
        }


        public CarResponseDTO updateCarStatus(Long carId, CarStatus newStatus) {

                Car car = carRepository.findById(carId)
                        .orElseThrow(() -> new RuntimeException("Car not found"));

        // ❗ бизнес правило: SOLD е краен статус
                if (car.getStatus() == CarStatus.SOLD) {
                        throw new RuntimeException("Sold car status cannot be changed");
                }

                car.setStatus(newStatus);

                return mapToResponseDTO(carRepository.save(car));
        }


}
