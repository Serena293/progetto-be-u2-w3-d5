package com.epicode.progetto_be_u2_w3_d5.auth;

import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String password;
}
