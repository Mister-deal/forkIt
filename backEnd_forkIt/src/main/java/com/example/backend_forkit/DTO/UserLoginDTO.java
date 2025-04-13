package com.example.backend_forkit.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class UserLoginDTO {

    private UUID id;
    private String email;
    private String password;
}
