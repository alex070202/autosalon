package com.autosalon.autosalon.dto;

import com.autosalon.autosalon.model.Brand;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BrandResponseDTO {

    private Long id;
    private String name;

    public static BrandResponseDTO fromEntity(Brand brand) {
        return BrandResponseDTO.builder()
                .id(brand.getId())
                .name(brand.getName())
                .build();
    }
}
