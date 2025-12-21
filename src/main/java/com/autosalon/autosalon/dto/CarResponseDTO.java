package com.autosalon.autosalon.dto;

import com.autosalon.autosalon.model.*;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class CarResponseDTO {

    private Long id;
    private String model;
    private Integer year;
    private Integer mileage;
    private BigDecimal price;
    private FuelType fuelType;
    private BodyType bodyType;
    private TransmissionType transmission;
    private Integer horsePower;
    private Color color;
    private CarStatus status;

    private Long brandId;
    private String brandName;

    public static CarResponseDTO fromEntity(Car car) {
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
}
