package com.medhub.medhub.mapper;

import com.medhub.medhub.dto.AppointmentDTO;
import com.medhub.medhub.entity.Appointment;
import com.medhub.medhub.entity.Doctor;
import com.medhub.medhub.entity.Patient;

public class AppointmentMapper {
    public static AppointmentDTO toDTO(Appointment appointment){
        AppointmentDTO dto = new AppointmentDTO();
        dto.setId(appointment.getId());
        dto.setAppointmentTime(appointment.getAppointmentTime());
        dto.setDoctorId(appointment.getDoctor().getId());
        dto.setPatientId(appointment.getPatient().getId());
        return dto;
    }

    public static Appointment toEntity(AppointmentDTO dto, Doctor doctor, Patient patient) {
        Appointment appointment = new Appointment();
        appointment.setAppointmentTime(dto.getAppointmentTime());
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        return appointment;
    }
}
