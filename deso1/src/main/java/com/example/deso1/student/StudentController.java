/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.deso1.student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

/**
 *
 * @author huutuan
 */
@Controller
public class StudentController {

    @GetMapping("/students")
    public String getAllStudent(Model model) throws Exception {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List<Student> students = new ArrayList<Student>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/deso1", "root", "123456789");
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from student");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String dob = resultSet.getString("dob");
                String major = resultSet.getString("major");
                int vaccinated = resultSet.getInt("vaccinated");
                students.add(new Student(id, name, dob, major, vaccinated == 0 ? false : true));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        model.addAttribute("students", students);
        return "students";
    }

    @GetMapping("/student/{id}")
    public String getStudent(Model model, @PathVariable String id) {
        model.addAttribute("id", id);
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet result = null;

        Student student = new Student();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/deso1", "root", "123456789");
            ps = connection.prepareStatement("select * from student where id = ?");
            ps.setString(1, id);
            result = ps.executeQuery();
            while (result.next()) {
                student.setId(result.getInt("id"));
                student.setName(result.getString("name"));
                student.setDob(result.getString("dob"));
                student.setMajor(result.getString("major"));
                student.setVaccinated(result.getInt("vaccinated") != 0 ? true : false);
            }
        } // End of try block
        catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("student", student);
        return "student-detail";
    }

    public Student check(String name, String dob) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/deso1", "root", "123456789");
            ps = connection.prepareStatement("select * from student where name = ? and dob = ?");
            ps.setString(1, name);
            ps.setString(2, dob);
            resultSet = ps.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name_raw = resultSet.getString("name");
                String dob_raw = resultSet.getString("dob");
                String major = resultSet.getString("major");
                int vaccinated = resultSet.getInt("vaccinated");
                Student s = new Student(id, name_raw, dob_raw, major, vaccinated == 0 ? false : true);

                return s;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
//    @GetMapping("/addStudent/{id}")
//    public String Addnew(Model model){
//        return "add-student";
//    }

    @PostMapping("/student/save/{id}")
    public String addStudent(Model model, Student student, @PathVariable String id) {
        Connection connection = null;
        PreparedStatement ps = null;
        int result = 0;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/deso1", "root", "123456789");
            ps = connection.prepareStatement("INSERT INTO student VALUES (?, ?, ?, ?, ?)");
            String error;
            if (check(student.getName(), student.getDob()) == null) {
                ps.setInt(1, Integer.valueOf(student.getId()));
                ps.setString(2, student.getName());
                ps.setString(3, student.getDob());
                ps.setString(4, student.getMajor());
                ps.setInt(5, student.isVaccinated() ? 1 : 0);
                result = ps.executeUpdate();
            }else{
                error = "Student does exist!!!";
                model.addAttribute("error", error);
                model.addAttribute("id", -1);
                return "student-detail";
            }

            ps.close();
            connection.close();

            return "redirect:/students";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error";
    }

    @PutMapping("/student/save/{id}")
    public String updateStudent(Student student, @PathVariable String id) {
        Connection connection = null;
        PreparedStatement ps = null;
        int result = 0;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/deso1", "root", "123456789");
            ps = connection.prepareStatement("UPDATE student SET name=?,dob=?,major=?,vaccinated=? WHERE id=?");
            ps.setString(1, student.getName());
            ps.setString(2, student.getDob());
            ps.setString(3, student.getMajor());
            ps.setInt(4, student.isVaccinated() ? 1 : 0);
            ps.setInt(5, Integer.valueOf(student.getId()));
            result = ps.executeUpdate();

            ps.close();
            connection.close();

            // Redirect the response to success page
            return "redirect:/students";
        } // End of try block
        catch (Exception e) {
            e.printStackTrace();
        }
        return "error"; // tạo trang Error
    }

    @DeleteMapping("/student/delete/{id}")
    public String deleteStudent(Student student, @PathVariable String id) {
        Connection connection = null;
        PreparedStatement ps = null;
        int result = 0;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/deso1", "root", "123456789");
            ps = connection.prepareStatement("DELETE FROM student WHERE id=?");
            ps.setInt(1, Integer.valueOf(student.getId()));
            result = ps.executeUpdate();

            ps.close();
            connection.close();

            return "redirect:/students";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error";
    }
}
