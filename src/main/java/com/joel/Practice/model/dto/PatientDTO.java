package com.joel.Practice.model.dto;

import com.joel.Practice.helper.constants.BloodType;
import com.joel.Practice.model.dto.AuthRegisterDTO.AuthRegisterDTO;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
public class PatientDTO {
    private AuthRegisterDTO user;
    private LocalDate birthDate;
    private String gender;
    private BloodType bloodType;
    private String phoneNumber;
    private Address address;
    private Double heightCm;
    private Double weightKg;
    private String emergencyContactName;
    private String emergencyContactPhone;
    private String patientCode;
}

@Getter
@Setter
class Address{
    private String city;
    private String state;
}