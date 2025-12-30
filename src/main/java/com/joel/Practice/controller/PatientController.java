package com.joel.Practice.controller;

import com.joel.Practice.model.dto.PatientDTO;
import com.joel.Practice.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/patients/")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping("")
    public ResponseEntity<?> patient(){
        String message="Patient controller API main route.";
        return ResponseEntity.ok().body(message);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/get-patients")
    public ResponseEntity<?> getPatients(){
        return ResponseEntity.ok().body(patientService.getAllPatients());
    }

    @PostMapping("/add-patient")
    public ResponseEntity<?> addPatient(@RequestBody PatientDTO patientDTO){
        String message = patientService.addPatient(patientDTO);
        return ResponseEntity.ok().body(message);
    }



}
