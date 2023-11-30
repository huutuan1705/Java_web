/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.de_thuc_hanh3.student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author huutuan
 */
public class StudentDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/thuc_hanh3";
    private String jdbcUserName = "root";
    private String jdbcPassWord = "123456789";
    
    private static final String SELECT_ALL_STUDENTS = "select * from student";
    private static final String SELECT_STUDENT_BY_ID = "select * from student where id=?";

    public StudentDAO() {
    }
    
    protected Connection getConnection(){
        Connection connection = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUserName, jdbcPassWord);
        }catch(SQLException e){
            e.printStackTrace();
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        return connection;
    }
    
    public List<Student> getAllStudent(){
        List<Student> students = new ArrayList<>();
        try{
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(SELECT_ALL_STUDENTS);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String dob = rs.getString("dob");
                String major = rs.getString("major");
                int vaccinated = rs.getInt("vaccinated");
                students.add(new Student(id, name, dob, major, vaccinated==0 ? false:true));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return students;
    }
    
    public Student getStudent(int id){
        Student student = new Student();
        try{
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(SELECT_STUDENT_BY_ID);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setDob(rs.getString("dob"));
                student.setMajor(rs.getString("major"));
                student.setVaccinated(rs.getInt("vaccinated") != 0 ? true : false);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return student;
    }
    
    public List<Student> getStudentByNameAndMajor(String tmp){
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM thuc_hanh3.student where "
                + "name like '%"+tmp+"%' and major like '%"+tmp+"%';";
        try{
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String dob = rs.getString("dob");
                String major = rs.getString("major");
                int vaccinated = rs.getInt("vaccinated");
                students.add(new Student(id, name, dob, major, vaccinated==0 ? false:true));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return students;
    }
}
