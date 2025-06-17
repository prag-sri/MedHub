package com.medhub.medhub.dto;

import lombok.Data;

@Data
public class PatientDTO {
    private Long id;
    private String medicalHistory;
    private Long userId;
}
