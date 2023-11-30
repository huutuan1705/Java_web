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

/**
 *
 * @author huutuan
 */
public class SemesterBean {
    private String id, name, start, end;
    private int minCredits, maxCredits;

    public SemesterBean() {
    }

    public SemesterBean(String id, String name, String start, String end, int minCredits, int maxCredits) {
        this.id = id;
        this.name = name;
        this.start = start;
        this.end = end;
        this.minCredits = minCredits;
        this.maxCredits = maxCredits;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public int getMinCredits() {
        return minCredits;
    }

    public void setMinCredits(int minCredits) {
        this.minCredits = minCredits;
    }

    public int getMaxCredits() {
        return maxCredits;
    }

    public void setMaxCredits(int maxCredits) {
        this.maxCredits = maxCredits;
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
    
    public SemesterBean getSemesterByID(String id){
        String sql = "select * from Semester where id=?";
        SubjectBean s = new SubjectBean();
        try{
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                String name = rs.getString("name");
                String startTime = rs.getString("startTime");
                String endTIme = rs.getString("endTIme");
                int minCredits = rs.getInt("minCredits");
                int maxCredits = rs.getInt("maxCredits");
                return new SemesterBean(id, name, startTime, endTIme, minCredits, maxCredits);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
