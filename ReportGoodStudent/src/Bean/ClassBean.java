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
public class ClassBean {
    private String id, nameClass;
    private int numberOfStudent;
    private SubjectBean subjectBean;

    public ClassBean() {
    }

    public ClassBean(String id, String nameClass, int numberOfStudent, SubjectBean subjectBean) {
        this.id = id;
        this.nameClass = nameClass;
        this.numberOfStudent = numberOfStudent;
        this.subjectBean = subjectBean;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getNameClass() {
        return nameClass;
    }
    public void setNameClass(String nameClass) {
        this.nameClass = nameClass;
    }
    public int getNumberOfStudent() {
        return numberOfStudent;
    }
    public void setNumberOfStudent(int numberOfStudent) {
        this.numberOfStudent = numberOfStudent;
    }
    public SubjectBean getSubjectBean() {
        return subjectBean;
    }
    public void setSubjectBean(SubjectBean subjectBean) {
        this.subjectBean = subjectBean;
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
    
    public ClassBean getClassByID(String id){
        String sql = "select * from Class where id=?";
        SubjectBean s = new SubjectBean();
        try{
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                String nameClass = rs.getString("nameClass");
                int numberOfStudent = rs.getInt("numberOfStudent");
                SubjectBean subjectBean = s.getSubjectByID(rs.getString("subjectID"));
                return new ClassBean(id, nameClass, numberOfStudent, subjectBean);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
