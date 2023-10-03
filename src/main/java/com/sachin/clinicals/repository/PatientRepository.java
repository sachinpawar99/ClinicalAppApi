package com.sachin.clinicals.repository;

import com.sachin.clinicals.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient,Integer> {
    List<Patient> findByFirstNameContainingOrLastNameContaining(String firstName, String lastName);


}
