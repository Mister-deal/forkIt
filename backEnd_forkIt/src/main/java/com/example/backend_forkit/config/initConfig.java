package com.example.backend_forkit.config;

import com.example.backend_forkit.entity.Role;
import com.example.backend_forkit.entity.User;
import com.example.backend_forkit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class initConfig implements CommandLineRunner {
    @Autowired
    private UserService service;




    @Override
    public void run(String... args) throws Exception {

    }
}
