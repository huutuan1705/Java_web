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
public class RegistrationBean {

    private String id;
    private StudentBean student;
    private SemesterBean semester;
    private ClassBean classBean;

    public RegistrationBean() {
    }

    public RegistrationBean(String id, StudentBean student, SemesterBean semester, ClassBean classBean) {
        this.id = id;
        this.student = student;
        this.semester = semester;
        this.classBean = classBean;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public StudentBean getStudent() {
        return student;
    }

    public void setStudent(StudentBean student) {
        this.student = student;
    }

    public SemesterBean getSemester() {
        return semester;
    }

    public void setSemester(SemesterBean semester) {
        this.semester = semester;
    }

    public ClassBean getClassBean() {
        return classBean;
    }

    public void setClassBean(ClassBean classBean) {
        this.classBean = classBean;
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

    public List<RegistrationBean> getAllResByStudentID(String id) {
        List<RegistrationBean> registrations = new ArrayList<>();
        String sql = "SELECT [id]\n"
                + "      ,[classID]\n"
                + "      ,[SemesterID]\n"
                + "  FROM [dbo].[Registration] where StudentID = ?";

        ClassBean cb = new ClassBean();
        StudentBean stuB = new StudentBean();
        SemesterBean sb = new SemesterBean();
        try {
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String Id = rs.getString("id");
                ClassBean classBean = cb.getClassByID(rs.getString("classID"));
                StudentBean studentBean = stuB.getStudentByID(id);
                SemesterBean semesterBean = sb.getSemesterByID(rs.getString("SemesterID"));
                registrations.add(new RegistrationBean(Id, studentBean, semesterBean, classBean));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return registrations;
    }

    public RegistrationBean getResByID(String id) {
        String sql = "select * from Registration where id=?";

        ClassBean cb = new ClassBean();
        StudentBean stuB = new StudentBean();
        SemesterBean sb = new SemesterBean();
        try {
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ClassBean classBean = cb.getClassByID(rs.getString("classID"));
                StudentBean studentBean = stuB.getStudentByID(rs.getString("StudentID"));
                SemesterBean semesterBean = sb.getSemesterByID(rs.getString("SemesterID"));
                return new RegistrationBean(id, studentBean, semesterBean, classBean);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
