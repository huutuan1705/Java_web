/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Bean;

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
public class StudentBean {
    private String id, fullname, academicYear;

    public StudentBean() {
    }

    public StudentBean(String id, String fullname, String academicYear) {
        this.id = id;
        this.fullname = fullname;
        this.academicYear = academicYear;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }
    
    protected Connection getConnection(){
        Connection connection = null;
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName= QLDKTC", 
                    "sa", "12345");
        }catch(SQLException e){
            e.printStackTrace();
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        return connection;
    }
    
    public List<StudentBean> getAllStudent(){
        List<StudentBean> students = new ArrayList<>();
        String sql = "select * from Student";
        try{
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String id = rs.getString("id");
                String name = rs.getString("studentName");
                String academicYear = rs.getString("academicYear");
                students.add(new StudentBean(id, name, academicYear));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return students;
    }
    
    public StudentBean getStudentByID(String id){
        String sql = "select * from Student where id=?";
        try{
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                String name = rs.getString("studentName");
                String academicYear = rs.getString("academicYear");
                return new StudentBean(id, name, academicYear);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
