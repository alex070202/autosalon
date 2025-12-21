package com.autosalon.autosalon.dto;

import com.autosalon.autosalon.model.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarRequestDTO {

    private String model;
    private Integer year;
    private Integer mileage;
    private BigDecimal price;

    private FuelType fuelType;
    private BodyType bodyType;
    private TransmissionType transmission;
    private Integer horsePower;
    private Color color;

    private Long brandId;
}
