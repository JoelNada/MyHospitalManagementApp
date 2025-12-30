package com.joel.Practice.service;

import com.joel.Practice.model.dto.PatientDTO;
import com.joel.Practice.model.entity.Patient;

import java.util.List;


public interface PatientService {
    public List<PatientDTO> getAllPatients();
    public String addPatient(PatientDTO patientDTO);
}
