package com.medhub.medhub.mapper;

import com.medhub.medhub.dto.DoctorDTO;
import com.medhub.medhub.entity.AppUser;
import com.medhub.medhub.entity.Doctor;

public class DoctorMapper {
    public static DoctorDTO toDTO(Doctor doctor){
        DoctorDTO dto = new DoctorDTO();
        dto.setId(doctor.getId());
        dto.setSpecialization(doctor.getSpecialization());
        dto.setUserId(doctor.getUser().getId());
        return dto;
    }

    public static Doctor toEntity(DoctorDTO dto, AppUser user) {
        Doctor doctor = new Doctor();
        doctor.setSpecialization(dto.getSpecialization());
        doctor.setUser(user);
        return doctor;
    }
}
