/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.deso2.phone;

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
public class PhoneDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/deso2";
    private String jdbcUserName = "root";
    private String jdbcPassWord = "123456789";
    
    private static final String SELECT_ALL_PHONES = "select * from phone";
    private static final String SELECT_PHONE_BY_ID = "select * from phone where id=?";
    private static final String INSERT_PHONE_SQL = "INSERT INTO phone VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE_PHONE_SQL = "UPDATE phone SET name=?,price=?,brand=?,sold=? WHERE id=?";
    private static final String DELETE_PHONE_SQL = "DELETE FROM phone WHERE id=?";
    private static final String CHECK_EXIST_SQL = "select * from phone where name=? and brand=?";

    public PhoneDAO() {
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
    
    public List<Phone> getAllPhones(){
        List<Phone> phones = new ArrayList<>();
        try{
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(SELECT_ALL_PHONES);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int price = rs.getInt("price");
                String brand = rs.getString("brand");
                int sold = rs.getInt("sold");
                phones.add(new Phone(id, name, price, brand, sold==0 ? false:true));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return phones;
    }
    
    public Phone getPhone(int id){
        Phone phone = new Phone();
        try{
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(SELECT_PHONE_BY_ID);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                phone.setId(rs.getInt("id"));
                phone.setName(rs.getString("name"));
                phone.setPrice(rs.getInt("price"));
                phone.setBrand(rs.getString("brand"));
                phone.setSold(rs.getInt("sold") != 0 ? true : false);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return phone;
    }
    
    public void insertPhone(Phone phone){
        try{
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(INSERT_PHONE_SQL);
            
            ps.setInt(1, Integer.valueOf(phone.getId()));
            ps.setString(2, phone.getName());
            ps.setInt(3, phone.getPrice());
            ps.setString(4, phone.getBrand());
            ps.setInt(5, phone.isSold() ? 1 : 0);
            
            ps.executeUpdate();
            
            ps.close();
            connection.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public void updatePhone(Phone phone){
        try{
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(UPDATE_PHONE_SQL);
            
            ps.setString(1, phone.getName());
            ps.setInt(2, phone.getPrice());
            ps.setString(3, phone.getBrand());
            ps.setInt(4, phone.isSold() ? 1 : 0);
            ps.setInt(5, Integer.valueOf(phone.getId()));
            ps.executeUpdate();
            
            ps.close();
            connection.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public void deletePhone(Phone phone){
        try{
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(DELETE_PHONE_SQL);
            
            ps.setInt(1, Integer.valueOf(phone.getId()));
            ps.executeUpdate();
            
            ps.close();
            connection.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public boolean checkExist(String name, String brand){
        try{
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(CHECK_EXIST_SQL);
            ps.setString(1, name);
            ps.setString(2, brand);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                return false;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return true;
    }
}
