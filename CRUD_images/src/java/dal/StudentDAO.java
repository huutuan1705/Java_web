/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Student;

/**
 *
 * @author NguyenHuuTuan
 */
public class StudentDAO extends DBContext {

    public List<Student> getAll() {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT [Id]\n"
                + "      ,[StudentName]\n"
                + "      ,[StudentImage]\n"
                + "  FROM [dbo].[Student]";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Student s = new Student(rs.getInt("id"), rs.getString("StudentName"),
                        rs.getString("StudentImage"));
                list.add(s);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public Student getStudentById(int id) {
        String sql = "SELECT [Id]\n"
                + "      ,[StudentName]\n"
                + "      ,[StudentImage]\n"
                + "  FROM [dbo].[Student]\n"
                + "  where id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Student c = new Student(rs.getInt("id"), rs.getString("StudentName"),
                        rs.getString("StudentImage"));
                return c;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public void insert(Student s) {
        String sql = "INSERT INTO [dbo].[Student]\n"
                + "           ([Id]\n"
                + "           ,[StudentName]\n"
                + "           ,[StudentImage])\n"
                + "     VALUES (?,?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, s.getId());
            st.setString(2, s.getName());
            st.setString(3, s.getImage());
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM [dbo].[Student]\n"
                + "      WHERE id=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void update(Student s) {
        String sql = "UPDATE [dbo].[Student]\n"
                + "   SET [StudentName] = ?\n"
                + "      ,[StudentImage] = ?\n"
                + " WHERE id=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, s.getName());
            st.setString(2, s.getImage());
            st.setInt(3, s.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        StudentDAO s = new StudentDAO();
        List<Student> list = s.getAll();
        System.out.println(list.get(0).getName());
    }
}
