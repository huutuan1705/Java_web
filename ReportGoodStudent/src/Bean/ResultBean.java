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
public class ResultBean {

    private String id;
    private float point1, point2, point3, finalScore;
    private RegistrationBean registration;

    public ResultBean() {
    }

    public ResultBean(String id, float point1, float point2, float point3, float finalScore, RegistrationBean registration) {
        this.id = id;
        this.point1 = point1;
        this.point2 = point2;
        this.point3 = point3;
        this.finalScore = finalScore;
        this.registration = registration;
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

    public RegistrationBean getRegistration() {
        return registration;
    }

    public void setRegistration(RegistrationBean registration) {
        this.registration = registration;
    }

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName= QLDKTC",
                    "sa", "12345");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public ResultBean getResultByResGisID(String ResGisID) {
        String sql = "SELECT [id]\n"
                + "      ,[point1]\n"
                + "      ,[point2]\n"
                + "      ,[point3]\n"
                + "      ,[finalScore]\n"
                + "  FROM [dbo].[Result] where registrationID = ?";

        RegistrationBean regB = new RegistrationBean();
        try {
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, ResGisID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String id = rs.getString("id");
                float point1 = rs.getFloat("point1");
                float point2 = rs.getFloat("point2");
                float point3 = rs.getFloat("point3");
                float finalScore = rs.getFloat("finalScore");
                RegistrationBean registrationBean = regB.getResByID(ResGisID);
                return new ResultBean(id, point1, point2, point3, finalScore, registrationBean);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public ResultBean getResultByResGisID(RegistrationBean regis) {
        String sql = "SELECT [id]\n"
                + "      ,[point1]\n"
                + "      ,[point2]\n"
                + "      ,[point3]\n"
                + "      ,[finalScore]\n"
                + "  FROM [dbo].[Result] where registrationID = ?";

        RegistrationBean regB = new RegistrationBean();
        try {
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, regis.getId());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String id = rs.getString("id");
                float point1 = rs.getFloat("point1");
                float point2 = rs.getFloat("point2");
                float point3 = rs.getFloat("point3");
                float finalScore = rs.getFloat("finalScore");
                RegistrationBean registrationBean = regis;
                return new ResultBean(id, point1, point2, point3, finalScore, registrationBean);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public Object[] toObjects(){
        return new Object[]{
            registration.getClassBean().getSubjectBean().getId(),
            registration.getClassBean().getSubjectBean().getNameSubject(),
            String.format("%.0f", registration.getClassBean().getSubjectBean().getRateScoreBean().getPoint1()*100),
            String.format("%.0f", registration.getClassBean().getSubjectBean().getRateScoreBean().getPoint2()*100),
            String.format("%.0f", registration.getClassBean().getSubjectBean().getRateScoreBean().getPoint3()*100),
            String.format("%.0f", registration.getClassBean().getSubjectBean().getRateScoreBean().getFinalScore()*100),
            point1, point2, point3, finalScore,
            String.format("%.1f",point1*registration.getClassBean().getSubjectBean().getRateScoreBean().getPoint1()+
                point2*registration.getClassBean().getSubjectBean().getRateScoreBean().getPoint2()+
                point3*registration.getClassBean().getSubjectBean().getRateScoreBean().getPoint3()+
                finalScore*registration.getClassBean().getSubjectBean().getRateScoreBean().getFinalScore())            
        };
    }
}
