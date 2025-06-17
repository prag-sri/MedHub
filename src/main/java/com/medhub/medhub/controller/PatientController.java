package com.medhub.medhub.controller;

import com.medhub.medhub.dto.PatientDTO;
import com.medhub.medhub.service.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController {

    private PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PreAuthorize("hasAnyRole('ADMIN'")
    @PostMapping
    public ResponseEntity<PatientDTO> createPatient(@RequestBody PatientDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(patientService.createPatient(dto));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<PatientDTO> getPatientById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(patientService.getPatientById(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<PatientDTO> updatePatient(@PathVariable Long id, @RequestBody PatientDTO dto){
        return ResponseEntity.status(HttpStatus.OK).body(patientService.updatePatient(id,dto));
    }


    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<String> deletePatient(@PathVariable Long id){
        patientService.deletePatient(id);
        return ResponseEntity.status(HttpStatus.OK).body("Patient deleted successfully!");
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<PatientDTO>> getAllPatients(){
        return ResponseEntity.status(HttpStatus.OK).body(patientService.getAllPatients());
    }
}
