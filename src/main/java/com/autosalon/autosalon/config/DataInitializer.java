package com.autosalon.autosalon.config;

import com.autosalon.autosalon.model.*;
import com.autosalon.autosalon.repository.BrandRepository;
import com.autosalon.autosalon.repository.CarRepository;
import com.autosalon.autosalon.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final BrandRepository brandRepository;
    private final CarRepository carRepository;

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {

        // --------- USERS (да не зависи от brands) ----------
        if (userRepository.count() == 0) {
            userRepository.save(User.builder()
                    .username("admin")
                    .password(passwordEncoder.encode("admin123"))
                    .role(Role.ADMIN)
                    .build());

            userRepository.save(User.builder()
                    .username("user")
                    .password(passwordEncoder.encode("user123"))
                    .role(Role.USER)
                    .build());
        }

        // --------- BRANDS + CARS ----------
        if (brandRepository.count() > 0) {
            return;
        }

        Brand bmw = brandRepository.save(Brand.builder().name("BMW").country("Germany").build());
        Brand audi = brandRepository.save(Brand.builder().name("Audi").country("Germany").build());
        Brand honda = brandRepository.save(Brand.builder().name("Honda").country("Japan").build());
        Brand mercedes = brandRepository.save(Brand.builder().name("Mercedes-Benz").country("Germany").build());
        Brand volkswagen = brandRepository.save(Brand.builder().name("Volkswagen").country("Germany").build());

        carRepository.saveAll(List.of(
                Car.builder()
                        .model("320d")
                        .year(2019)
                        .mileage(120000)
                        .price(BigDecimal.valueOf(22000))
                        .fuelType(FuelType.DIESEL)
                        .bodyType(BodyType.SEDAN)
                        .transmission(TransmissionType.AUTOMATIC)
                        .horsePower(190)
                        .color(Color.BLACK)
                        .brand(bmw)
                        .build(),

                Car.builder()
                        .model("A4")
                        .year(2020)
                        .mileage(85000)
                        .price(BigDecimal.valueOf(25500))
                        .fuelType(FuelType.DIESEL)
                        .bodyType(BodyType.SEDAN)
                        .transmission(TransmissionType.AUTOMATIC)
                        .horsePower(190)
                        .color(Color.GRAY)
                        .brand(audi)
                        .build(),

                Car.builder()
                        .model("C-Class")
                        .year(2018)
                        .mileage(140000)
                        .price(BigDecimal.valueOf(23000))
                        .fuelType(FuelType.PETROL)
                        .bodyType(BodyType.SEDAN)
                        .transmission(TransmissionType.MANUAL)
                        .horsePower(156)
                        .color(Color.SILVER)
                        .brand(mercedes)
                        .build(),

                Car.builder()
                        .model("E46")
                        .year(2002)
                        .mileage(20000)
                        .price(BigDecimal.valueOf(5000))
                        .fuelType(FuelType.PETROL)
                        .bodyType(BodyType.SEDAN)
                        .transmission(TransmissionType.MANUAL)
                        .horsePower(170)
                        .color(Color.BLACK)
                        .brand(bmw)
                        .build(),

                Car.builder()
                        .model("Golf-4")
                        .year(1999)
                        .mileage(300000)
                        .price(BigDecimal.valueOf(2000))
                        .fuelType(FuelType.DIESEL)
                        .bodyType(BodyType.WAGON)
                        .transmission(TransmissionType.MANUAL)
                        .horsePower(101)
                        .color(Color.SILVER)
                        .brand(volkswagen)
                        .build(),

                Car.builder()
                        .model("CR-V")
                        .year(2015)
                        .mileage(90000)
                        .price(BigDecimal.valueOf(30000))
                        .fuelType(FuelType.LPG)
                        .bodyType(BodyType.SUV)
                        .transmission(TransmissionType.AUTOMATIC)
                        .horsePower(150)
                        .color(Color.RED)
                        .brand(honda)
                        .build(),

                Car.builder()
                        .model("Q5")
                        .year(2013)
                        .mileage(200000)
                        .price(BigDecimal.valueOf(12000))
                        .fuelType(FuelType.DIESEL)
                        .bodyType(BodyType.SUV)
                        .transmission(TransmissionType.AUTOMATIC)
                        .horsePower(350)
                        .color(Color.WHITE)
                        .brand(audi)
                        .build()
        ));
    }
}
