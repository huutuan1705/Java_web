/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.DemoJWT.Repo;

import com.example.DemoJWT.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author huutuan
 */
public interface UserRepo extends JpaRepository<User, Long>{
    User findByUsername(String username);
}
