package com.joel.Practice.service.serviceImpl;

import com.joel.Practice.exceptions.customExceptions.RoleNotFoundException;
import com.joel.Practice.model.dto.PatientDTO;
import com.joel.Practice.model.entity.Patient;
import com.joel.Practice.model.entity.Role;
import com.joel.Practice.model.entity.User;
import com.joel.Practice.repo.PatientRepo;
import com.joel.Practice.repo.RoleRepo;
import com.joel.Practice.repo.UserRepo;
import com.joel.Practice.service.PatientService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepo patientRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<PatientDTO> getAllPatients() {
        return convertEntityToDto(patientRepo.findAll());
    }

    @Override
    public String addPatient(PatientDTO patientDTO) {
       User user = modelMapper.map(patientDTO.getUser(), User.class);
       user.setPassword(passwordEncoder.encode(user.getPassword()));
       Role role = roleRepo.findByRole("ROLE_PATIENT").orElseThrow(()->new RoleNotFoundException("Role Not Found !!"));
       user.getRoles().add(role);
       userRepo.save(user);
       Patient patient = convertDtoToEntity(patientDTO);
       String patientCode = "PAT100"+ Year.now()+patient.getPhoneNumber().substring(1,4)+patientRepo.count() + 1;
       patient.setPatientCode(patientCode);
       patient.setUser(user);
       patientRepo.save(patient);
       return "Saved Patient Details";
    }

    private Patient convertDtoToEntity(PatientDTO patientDTO){
        return modelMapper.map(patientDTO, Patient.class);
    }


    private List<PatientDTO> convertEntityToDto(List<Patient> patients){
        return patients
                .stream()
                .map(patient -> modelMapper.map(patient,PatientDTO.class))
                .toList();
    }

}
