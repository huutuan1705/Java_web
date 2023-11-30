/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.DemoRestAPI.service;

import com.example.DemoRestAPI.domain.Assignment;
import com.example.DemoRestAPI.domain.User;
import com.example.DemoRestAPI.repsitory.AssignmentRepository;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author huutuan
 */
@Service
public class AssignmentService {
    @Autowired 
    private AssignmentRepository assignmentRepo;
    
    public Assignment save(User user){
        Assignment assignment = new Assignment();
        assignment.setStatus("Needs to be submit");
        assignment.setUser(user);
        
        return assignmentRepo.save(assignment);
    }
    
    public Set<Assignment> findByUser(User user){
        return assignmentRepo.findByUser(user);
    }

    public Optional<Assignment> findById(Long assignmentId) {
        return assignmentRepo.findById(assignmentId);
    }

    public Assignment save(Assignment assignment) {
        return assignmentRepo.save(assignment);
    }
}
