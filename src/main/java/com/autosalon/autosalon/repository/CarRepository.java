package com.autosalon.autosalon.repository;

import com.autosalon.autosalon.model.BodyType;
import com.autosalon.autosalon.model.Car;
import com.autosalon.autosalon.model.CarStatus;
import com.autosalon.autosalon.model.Color;
import com.autosalon.autosalon.model.FuelType;

import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {

    List<Car> findByBrand_NameIgnoreCase(String brandName);

    List<Car> findByBrand_Id(Long brandId);

    List<Car> findByStatus(CarStatus status);


    List<Car> findByPriceBetween(BigDecimal min, BigDecimal max);

    List<Car> findByFuelType(FuelType fuelType);

    List<Car> findByBodyType(BodyType bodyType);

    List<Car> findByColor(Color color);

}
