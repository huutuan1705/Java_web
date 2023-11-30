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
public class RateScoreBean {
    private String id;
    private float point1, point2, point3, finalScore;

    public RateScoreBean() {
    }

    public RateScoreBean(String id, float point1, float point2, float point3, float finalScore) {
        this.id = id;
        this.point1 = point1;
        this.point2 = point2;
        this.point3 = point3;
        this.finalScore = finalScore;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public float getPoint1() {
        return point1;
    }

    public void setPoint1(float point1) {
        this.point1 = point1;
    }

    public float getPoint2() {
        return point2;
    }

    public void setPoint2(float point2) {
        this.point2 = point2;
    }

    public float getPoint3() {
        return point3;
    }

    public void setPoint3(float point3) {
        this.point3 = point3;
    }

    public float getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(float finalScore) {
        this.finalScore = finalScore;
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
    
    public RateScoreBean getRateScoreByID(String id){
        String sql = "select * from RateScore where id=?";
        try{
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                float point1 = rs.getFloat("point1");
                float point2 = rs.getFloat("point2");
                float point3 = rs.getFloat("point3");
                float finalScore = rs.getFloat("finalScore");
                return new RateScoreBean(id, point1, point2, point3, finalScore);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
