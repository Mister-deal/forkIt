package com.example.backend_forkit.config;

import com.example.backend_forkit.entity.Member;
import com.example.backend_forkit.entity.Role;
import com.example.backend_forkit.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class initConfig implements CommandLineRunner {
    @Autowired
    private MemberService service;

    private void thereIsAdmin() {
        List<Member> members = service.findUsersByRole(Role.ADMIN);
        if(members.isEmpty()){
            Member admin= Member.builder().
                    username("admin").
                    firstname("max").
                    lastname("admin").
                    email("admin@gmail.fr").
                    password("admin").
                    created_at(LocalDateTime.now()).
                    role(Role.ADMIN).
                    active(true).
                    build();

            service.createUser(admin);
        }
    }

    private void thereIsUser(){
        List<Member> members =service.findUsersByRole(Role.ADMIN);
        if(members.isEmpty()){
            Member member = Member.builder().
                    username("user").
                    firstname("max").
                    lastname("user").
                    email("user@mail.fr").
                    password("user").
                    created_at(LocalDateTime.now()).
                    role(Role.USER).
                    active(true).
                    build();

            service.createUser(member);
        }
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
