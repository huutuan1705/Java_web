/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.SystemIntelligent.DAO;


import com.example.SystemIntelligent.model.Admin;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author huutuan
 */
public class AdminDAO extends DAO{
    public Admin check_login(String username, String password){
        String sql = "SELECT * FROM admin WHERE username = ? and password = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String id = rs.getString("idadmin");
                String fullname = rs.getString("fullname");
                String email = rs.getString("email");
                String tel = rs.getString("tel");
                Admin admin = new Admin(email, username, password, fullname, email, tel);
                return admin;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    } 
}
