package com.joel.Practice.model.entity;

import com.joel.Practice.helper.constants.BloodType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "patients")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String patientCode;
    @Column(nullable = false)
    private LocalDate birthDate;
    @Enumerated(EnumType.STRING)
    private BloodType bloodType;
    private String gender;
    private String phoneNumber;
    private Address address;
    private Double heightCm;
    private Double weightKg;
    private String emergencyContactName;
    private String emergencyContactPhone;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;
}


@Getter
@Setter
@Embeddable
class Address{
    private String city;
    private String state;
}