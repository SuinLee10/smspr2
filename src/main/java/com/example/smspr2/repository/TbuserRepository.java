package com.example.smspr2.repository;

import com.example.smspr2.domain.Tbpost;
import com.example.smspr2.domain.Tbuser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TbuserRepository extends JpaRepository<Tbuser, String> {
    Tbuser findByUsernameAndPassword(String username, String password);
}
