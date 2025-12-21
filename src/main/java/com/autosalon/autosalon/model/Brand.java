package com.autosalon.autosalon.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "brands")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name; // BMW, Audi, Mercedes

    // Допълнителни полезни полета
    private String country;        // Germany, Japan
    private Integer foundedYear;   // 1916
    private String logoUrl;        // URL към лого (по-късно за frontend)

    @OneToMany(mappedBy = "brand", fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Car> cars;
}
