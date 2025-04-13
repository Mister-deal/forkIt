package com.example.backend_forkit.service;

import com.example.backend_forkit.config.jwt.JwtTokenProvider;
import com.example.backend_forkit.entity.Member;
import com.example.backend_forkit.entity.Role;
import com.example.backend_forkit.repository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MemberService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(MemberService.class);


    @Autowired
    private final MemberRepository memberRepository;

    @Lazy
    @Autowired
    private AuthenticationManager authenticationManager;
    @Lazy
    @Autowired
    private PasswordEncoder passwordEncoder;

    private final JwtTokenProvider provider;


    public MemberService(MemberRepository memberRepository, JwtTokenProvider provider) {
        this.memberRepository = memberRepository;
        this.provider = provider;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    public boolean createMember(Member member) {
        logger.debug("Creating user: {}", member);
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        memberRepository.save(member);
        logger.debug("User created successfully: {}", member);
        return true;
    }

    public List<Member> findUsersByRole(Role role) {
        return memberRepository.searchUserByRole(role);
    }

    public String generateToken(String email, String password) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return provider.generateToken(authentication);
    }

    @Transactional
    public Member updateUser(long id, Member userUpdateDetails) {
        logger.debug("Updating user with ID {}: {}", id, userUpdateDetails);
        Member member = getMemberById(id);

        member.setUsername(userUpdateDetails.getUsername());
        member.setEmail(userUpdateDetails.getEmail());
        member.setUpdated_at(LocalDateTime.now());

        memberRepository.save(member); // Save the updated member directly
        logger.debug("User updated successfully: {}", member);
        return member;
    }

    public boolean checkUserNameExists(String email) {
        return memberRepository.findByEmail(email).isPresent();
    }

    public boolean verifyUser(String email, String password) {
        return memberRepository.findByEmail(email)
                .map(user -> passwordEncoder.matches(password, user.getPassword()))
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
    }

    @Transactional
    public Member updatePassword(long id, String newPassword) {
        logger.debug("Updating password for user with ID {}", id);
        int updated = memberRepository.updatePasswordById(id, newPassword);
        if (updated > 0) {
            logger.debug("Password updated successfully for user with ID {}", id);
            return getMemberById(id);
        }
        logger.error("Failed to update password for user with ID {}", id);
        return null;
    }

    @Transactional(readOnly = true)
    public Member getMemberById(long id) {
        logger.debug("Fetching user by ID {}", id);
        return memberRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("User with ID {} not found", id);
                    return new EntityNotFoundException("User with id " + id + " not found");
                });
    }

    @Transactional(readOnly = true)
    public List<Member> getAllUsers() {
        logger.debug("Fetching all users");
        List<Member> members = memberRepository.findAll();
        logger.debug("Fetched {} users", members.size());
        return members;
    }

    @Transactional
    public boolean deleteUser(long id) {
        logger.debug("Deleting user with ID {}", id);
        Member member = getMemberById(id);
        if (member != null) {
            memberRepository.delete(member);
            logger.debug("User with ID {} deleted successfully", id);
            return true;
        }
        logger.error("Failed to delete user with ID {}", id);
        return false;
    }


}
