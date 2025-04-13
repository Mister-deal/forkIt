package com.example.backend_forkit.service;

import com.example.backend_forkit.entity.Role;
import com.example.backend_forkit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {


    @Autowired
    private final UserRepository userRepository;
   /*
    @Lazy
    @Autowired
    private AuthenticationManager authenticationManager;
    @Lazy
    @Autowired
    private PasswordEncoder passwordEncoder;

    */

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findUsersByRole(Role role) {
        return userRepository.searchUserByRole(role);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
