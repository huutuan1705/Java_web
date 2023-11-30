/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.DemoRestAPI.repsitory;

import com.example.DemoRestAPI.domain.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author huutuan
 */
public interface UserRepository extends JpaRepository<User, Long>{

    public Optional<User> findByUsername(String username);
    
}
