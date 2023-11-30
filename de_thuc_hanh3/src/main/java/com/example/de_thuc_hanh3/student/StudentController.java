/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.de_thuc_hanh3.student;

import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author huutuan
 */
@RestController
@CrossOrigin
public class StudentController {
    private StudentDAO d = new StudentDAO();
    
    @GetMapping("/students")
    public List<Student> getAllStudents() throws Exception{
        List<Student> students = d.getAllStudent();
        return students;
    }
    
    @GetMapping("/student/{id}")
    public Student getLaptop(@PathVariable String id){
        Student student = d.getStudent(Integer.parseInt(id));
        return student;
    }
    
    @GetMapping("/students/{tmp}")
    public List<Student> getStudentsByNameAndMajor(@PathVariable String tmp){
        List<Student> students = d.getStudentByNameAndMajor(tmp);
        return students;
    }
}
