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
import model.Product;

/**
 *
 * @author NguyenHuuTuan
 */
public class ProductDAO extends DBContext {

    public List<Product> getAllProduct() {
        List<Product> list = new ArrayList<>();
        String sql = "select * from Product";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String id = rs.getString("id");
                String type = rs.getString("type");
                String name = rs.getString("name");
                String describe = rs.getString("describe");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                String image = rs.getString("image");
                Product p = new Product(id, name, type, describe, image, price, quantity);
                list.add(p);
            }
        } catch (SQLException e) {

        }
        return list;
    }

    public Product getProductById(String id) {
        String sql = "SELECT [id]\n"
                + "      ,[name]\n"
                + "      ,[describe]\n"
                + "      ,[price]\n"
                + "      ,[quantity]\n"
                + "      ,[image]\n"
                + "      ,[type]\n"
                + "  FROM [dbo].[Product]\n"
                + "  where id=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                String Id = rs.getString("id");
                String type = rs.getString("type");
                String name = rs.getString("name");
                String describe = rs.getString("describe");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                String image = rs.getString("image");
                Product p = new Product(Id, name, type, describe, image, price, quantity);
                return p;
            }
        } catch (SQLException e) {

        }
        return null;
    }

    public void insertProduct(Product p) {
        String sql = "INSERT INTO [dbo].[Product]\n"
                + "           ([id]\n"
                + "           ,[name]\n"
                + "           ,[describe]\n"
                + "           ,[price]\n"
                + "           ,[quantity]\n"
                + "           ,[image]\n"
                + "           ,[type])\n"
                + "     VALUES (?,?,?,?,?,?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, p.getId());
            st.setString(2, p.getName());
            st.setString(3, p.getDescribe());
            st.setDouble(4, p.getPrice());
            st.setInt(5, p.getQuantity());
            st.setString(6, p.getImage());
            st.setString(7, p.getType());
            st.executeUpdate();
        } catch (SQLException e) {

        }
    }

    public void deleteProduct(String id) {
        String sql = "DELETE FROM [dbo].[Product]\n"
                + "      WHERE id=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, id);
            st.executeUpdate();
        } catch (SQLException e) {

        }
    }

    public void updateProduct(Product p) {
        String sql = "UPDATE [dbo].[Product]\n"
                + "   SET [type] = ?\n"
                + "      ,[name] = ?\n"
                + "      ,[describe] = ?\n"
                + "      ,[price] = ?\n"
                + "      ,[quantity] = ?\n"
                + "      ,[image] = ?\n"
                + " WHERE id=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, p.getType());
            st.setString(2, p.getName());
            st.setString(3, p.getDescribe());
            st.setDouble(4, p.getPrice());
            st.setInt(5, p.getQuantity());
            st.setString(6, p.getImage());
            st.setString(7, p.getId());
            st.executeUpdate();
        } catch (SQLException e) {

        }
    }
}
