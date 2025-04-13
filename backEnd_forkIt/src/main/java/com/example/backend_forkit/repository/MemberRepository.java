package com.example.backend_forkit.repository;

import com.example.backend_forkit.entity.Member;
import com.example.backend_forkit.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByPseudonym(String pseudonym);
    Optional<Member> findByEmail(String email);

    List<Member> searchUserByRole(Role role);


    @Modifying
    @Query("UPDATE Member m SET m.password = :password WHERE m.id = :id")
    int updatePasswordById(@Param("id") long id, @Param("password") String password);


    boolean existsByEmail(String email);

}
