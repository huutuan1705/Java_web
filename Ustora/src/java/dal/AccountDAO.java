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
import model.Person;

/**
 *
 * @author NguyenHuuTuan
 */
public class AccountDAO extends DBContext {

    public List<Person> getAllAccount() {
        List<Person> list = new ArrayList<>();
        String sql = "SELECT [id]\n"
                + "      ,[name]\n"
                + "      ,[username]\n"
                + "      ,[password]\n"
                + "      ,[email]\n"
                + "      ,[role]\n"
                + "      ,[phoneNumber]\n"
                + "      ,[gender]\n"
                + "  FROM [dbo].[Person]";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String email = rs.getString("email");
                int role = rs.getInt("role");
                String phone = rs.getString("phoneNumber");
                String gender = rs.getString("gender");
                Person p = new Person(name, username, password, email, phone, gender, role);
                list.add(p);
            }
        } catch (SQLException e) {

        }
        return list;
    }

    public Person checkLoginAccount(String user, String pass) {
        String sql = "SELECT [username]\n"
                + "      ,[password]\n"
                + "      ,[name]\n"
                + "      ,[email]\n"
                + "      ,[role]\n"
                + "      ,[phoneNumber]\n"
                + "      ,[gender]\n"
                + "  FROM [dbo].[Person]\n"
                + "  where username=? and password=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, user);
            st.setString(2, pass);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String email = rs.getString("email");
                int role = rs.getInt("role");
                String phone = rs.getString("phoneNumber");
                String gender = rs.getString("gender");
                Person p = new Person(name, username, password, email, phone, gender, role);
                return p;
            }
        } catch (SQLException e) {

        }
        return null;
    }
    
    public Person checkRegisterAccount(String user) {
        String sql = "SELECT [username]\n"
                + "      ,[password]\n"
                + "      ,[name]\n"
                + "      ,[email]\n"
                + "      ,[role]\n"
                + "      ,[phoneNumber]\n"
                + "      ,[gender]\n"
                + "  FROM [dbo].[Person]\n"
                + "  where username=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, user);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String email = rs.getString("email");
                int role = rs.getInt("role");
                String phone = rs.getString("phoneNumber");
                String gender = rs.getString("gender");
                Person p = new Person(name, username, password, email, phone, gender, role);
                return p;
            }
        } catch (SQLException e) {

        }
        return null;
    }

    public void insertAccount(Person p) {
        String sql = "INSERT INTO [dbo].[Person]\n"
                + "           ([name]\n"
                + "           ,[username]\n"
                + "           ,[password]\n"
                + "           ,[email]\n"
                + "           ,[role]\n"
                + "           ,[phoneNumber]\n"
                + "           ,[gender])\n"
                + "     VALUES (?,?,?,?,?,?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, p.getName());
            st.setString(2, p.getUsername());
            st.setString(3, p.getPassword());
            st.setString(4, p.getEmail());
            st.setInt(5, 2);
            st.setString(6, p.getPhone());
            st.setString(7, p.getGenger());
            st.executeUpdate();
        } catch (SQLException e) {

        }
    }

    public static void main(String[] args) {
        AccountDAO d = new AccountDAO();
        System.out.println(d.getAllAccount().get(0).getName());
    }
}
