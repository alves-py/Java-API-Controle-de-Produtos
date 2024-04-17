package com.example.controledeprodutos.shared;

import com.example.controledeprodutos.model.Users.UserRole;

import java.util.Date;

public record RegisterDTO(String login,
        String password,
        String first_name,
        String last_name,
        UserRole role) {
}
