package com.joel.Practice.repo;

import com.joel.Practice.model.entity.Patient;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepo extends JpaRepository<@NonNull Patient, @NonNull Long> {

}
