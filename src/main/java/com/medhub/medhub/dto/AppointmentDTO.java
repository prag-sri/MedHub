package com.medhub.medhub.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AppointmentDTO {
    private Long id;
    private LocalDateTime appointmentTime;
    private Long doctorId;
    private Long patientId;
}
