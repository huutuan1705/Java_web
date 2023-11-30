/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.DemoJWT.Repo;

import com.example.DemoJWT.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author huutuan
 */
public interface RoleRepo extends JpaRepository<Role, Long>{
    Role findByName(String name);
}
