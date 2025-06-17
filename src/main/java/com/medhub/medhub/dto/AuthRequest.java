package com.medhub.medhub.dto;

import lombok.Data;

@Data
public class AuthRequest {
    private String username;
    private String password;
}
