package com.medhub.medhub.dto;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import static io.lettuce.core.pubsub.PubSubOutput.Type.message;

@Data
public class DoctorDTO {
    private Long id;

    @NotBlank(message = "Specialization cannot be blank")
    private String specialization;

    @NotNull(message = "User ID is required")
    private Long userId;
}
