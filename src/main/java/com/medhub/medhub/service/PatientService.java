package com.medhub.medhub.service;

import com.medhub.medhub.dto.PatientDTO;
import com.medhub.medhub.entity.AppUser;
import com.medhub.medhub.entity.Patient;
import com.medhub.medhub.exception.PatientNotFoundException;
import com.medhub.medhub.exception.UserNotFoundException;
import com.medhub.medhub.mapper.PatientMapper;
import com.medhub.medhub.repository.AppUserRepository;
import com.medhub.medhub.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {
    
    private PatientRepository patientRepository;
    private AppUserRepository appUserRepository;
    
    public PatientService(PatientRepository patientRepository, AppUserRepository appUserRepository) {
        this.patientRepository = patientRepository;
        this.appUserRepository = appUserRepository;
    }
    
    public PatientDTO createPatient(PatientDTO dto) {
        AppUser user = appUserRepository.findById(dto.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User with ID: " + dto.getUserId() + " not found!"));
        Patient patient = PatientMapper.toEntity(dto,user);
        return PatientMapper.toDTO(patientRepository.save(patient));
    }

    public PatientDTO getPatientById(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException("Patient with ID: " + id + " not found!"));
        return PatientMapper.toDTO(patient);
    }

    public PatientDTO updatePatient(Long id, PatientDTO dto) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException("Patient with ID: " + id + " not found!"));
        AppUser user = appUserRepository.findById(dto.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User with ID: " + dto.getUserId() + " not found!"));
        patient.setMedicalHistory(dto.getMedicalHistory());
        patient.setUser(user);

        return PatientMapper.toDTO(patientRepository.save(patient));
    }

    public void deletePatient(Long id) {
        patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException("Patient with ID: " + id + " not found!"));
        patientRepository.deleteById(id);
    }

    public List<PatientDTO> getAllPatients() {
        return patientRepository.findAll()
                .stream()
                .map(PatientMapper::toDTO)
                .toList();
    }
}
