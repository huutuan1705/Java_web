/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author NguyenHuuTuan
 */
public class DBMySQLContext {
    protected Connection connection;

    public DBMySQLContext() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_demo", "root", "123456789");
        }catch(ClassNotFoundException | SQLException ex){
            System.out.println(ex);
        }
    }
    
}
