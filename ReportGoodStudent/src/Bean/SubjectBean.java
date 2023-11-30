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
public class SubjectBean {
    private String id, nameSubject;
    private int credits;
    private RateScoreBean rateScoreBean;

    public SubjectBean() {
    }

    public SubjectBean(String id, String nameSubject, int credits, RateScoreBean rateScoreBean) {
        this.id = id;
        this.nameSubject = nameSubject;
        this.credits = credits;
        this.rateScoreBean = rateScoreBean;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNameSubject() {
        return nameSubject;
    }

    public void setNameSubject(String nameSubject) {
        this.nameSubject = nameSubject;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public RateScoreBean getRateScoreBean() {
        return rateScoreBean;
    }

    public void setRateScoreBean(RateScoreBean rateScoreBean) {
        this.rateScoreBean = rateScoreBean;
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
    
    public SubjectBean getSubjectByID(String id){
        String sql = "select * from Subject where id=?";
        RateScoreBean r = new RateScoreBean();
        try{
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                String nameSubject = rs.getString("nameSubject");
                int credits = rs.getInt("credits");
                RateScoreBean rateScoreBean = r.getRateScoreByID(rs.getString("rateScoreID"));
                return new SubjectBean(id, nameSubject, credits, rateScoreBean);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
