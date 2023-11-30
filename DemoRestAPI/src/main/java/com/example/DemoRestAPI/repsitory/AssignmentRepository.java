/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.DemoRestAPI.repsitory;

import com.example.DemoRestAPI.domain.Assignment;
import com.example.DemoRestAPI.domain.User;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author huutuan
 */
public interface AssignmentRepository extends JpaRepository<Assignment, Long>{
    Set<Assignment> findByUser(User user);
}
