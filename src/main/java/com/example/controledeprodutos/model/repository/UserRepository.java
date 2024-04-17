package com.example.controledeprodutos.model.repository;

import com.example.controledeprodutos.model.Users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, String> {
    UserDetails findByLogin(String login);
}
