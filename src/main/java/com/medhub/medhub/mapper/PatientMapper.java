package com.medhub.medhub.mapper;

import com.medhub.medhub.dto.PatientDTO;
import com.medhub.medhub.entity.AppUser;
import com.medhub.medhub.entity.Patient;

public class PatientMapper {
    public static PatientDTO toDTO(Patient patient){
        PatientDTO dto = new PatientDTO();
        dto.setId(patient.getId());
        dto.setMedicalHistory(patient.getMedicalHistory());
        dto.setUserId(patient.getUser().getId());
        return dto;
    }

    public static Patient toEntity(PatientDTO dto, AppUser user) {
        Patient patient = new Patient();
        patient.setMedicalHistory(dto.getMedicalHistory());
        patient.setUser(user);
        return patient;
    }
}
