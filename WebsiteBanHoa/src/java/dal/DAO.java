/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Product;

/**
 *
 * @author huutuan
 */
public class DAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/websitebanhoa";
    private String jdbcUserName = "root";
    private String jdbcPassWord = "123456789";
    
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
    
    public List<Product> getAllProduct(){
        List<Product> products = new ArrayList<>();
        String sql = "select * from product";
        try{
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                String image = rs.getString("image");
                String description = rs.getString("description");
                int quantity = rs.getInt("quantity");
                products.add(new Product(id, name, image, price, description, quantity));
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return products;
    }
    
    public Product getProductById(int id){
        String sql = "select * from product where id=?";
        try{
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                String image = rs.getString("image");
                String description = rs.getString("description");
                int quantity = rs.getInt("quantity");
                return new Product(id, name, image, price, description, quantity);
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return null;
    }
    
    public void updateProduct(Product product){
        String sql = "UPDATE product SET name=?,image=?,price=?,description=?, quantity=? WHERE id=?";
        try{
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, product.getName());
            ps.setString(2, product.getImage());
            ps.setDouble(3, product.getPrice());
            ps.setString(4, product.getDescription());
            ps.setInt(5, product.getQuantity());
            ps.setInt(6, product.getId());
            
            ps.executeUpdate();
            
            ps.close();
            connection.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        DAO d=new DAO();
        System.out.println(d.getAllProduct().get(0).getName());
    }
}
