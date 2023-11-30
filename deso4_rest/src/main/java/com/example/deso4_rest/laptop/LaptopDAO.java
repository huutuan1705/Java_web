/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.deso4_rest.laptop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author huutuan
 */
public class LaptopDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/deso4_rest";
    private String jdbcUserName = "root";
    private String jdbcPassWord = "123456789";
    
    private static final String SELECT_ALL_LAPTOPS = "select * from laptop";
    private static final String SELECT_LAPTOP_BY_ID = "select * from laptop where id=?";

    public LaptopDAO() {
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
    
    public List<Laptop> getAllLaptops(){
        List<Laptop> laptops = new ArrayList<>();
        try{
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(SELECT_ALL_LAPTOPS);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String date = rs.getString("ngaysx");
                String brand = rs.getString("brand");
                int sold = rs.getInt("sold");
                laptops.add(new Laptop(id, name, date, brand, sold==0 ? false:true));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return laptops;
    }
    
    public Laptop getLaptop(int id){
        Laptop laptop = new Laptop();
        try{
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(SELECT_LAPTOP_BY_ID);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                laptop.setId(rs.getInt("id"));
                laptop.setName(rs.getString("name"));
                laptop.setDate(rs.getString("ngaysx"));
                laptop.setBrand(rs.getString("brand"));
                laptop.setSold(rs.getInt("sold") != 0 ? true : false);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return laptop;
    }
    
    public List<Laptop> searchByNameAndBrand(String tmp){
        List<Laptop> laptops = new ArrayList<>();
        String sql = "SELECT * FROM deso4_rest.laptop where "
                + "name like '%"+tmp+"%' and brand like '%"+tmp+"%';";
        try{
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String date = rs.getString("ngaysx");
                String brand = rs.getString("brand");
                int sold = rs.getInt("sold");
                laptops.add(new Laptop(id, name, date, brand, sold==0 ? false:true));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return laptops;
    }
}
