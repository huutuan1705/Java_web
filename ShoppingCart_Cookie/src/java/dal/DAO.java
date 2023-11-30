/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import model.Customer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.Cart;
import model.Item;
import model.Product;

/**
 *
 * @author ADMIN
 */
public class DAO extends DBContext{
    public Customer getAccount(String user,String pass){
        String sql="select * from customer where username=? and password=?";
        try{
            PreparedStatement st=connection.prepareStatement(sql);
            st.setString(1, user);
            st.setString(2, pass);
            ResultSet rs=st.executeQuery();
            if(rs.next()){
                return new Customer(rs.getInt("id"),
                                    rs.getString("name"),
                                    rs.getDouble("amount"),
                                    user, pass);
            }
        }catch(SQLException e){
            System.out.println(e);
        }
        return null;
    }
    public List<Product> getAll(){
        List<Product> list=new ArrayList<>();
        String sql="select * from product";
        try{
            PreparedStatement st= connection.prepareStatement(sql);
            ResultSet rs=st.executeQuery();
            while(rs.next()){
                Product p=new Product(rs.getInt("id"),
                                      rs.getString("name"),
                                      rs.getDouble("price"),
                                      rs.getInt("quantity"));
                list.add(p);
            }
        }catch(SQLException e){
            System.out.println(e);
        }
        
        return list;
    }
    public Product getProductById(int id){
        String sql="select * from product where id=?";
        try{
            PreparedStatement st= connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs=st.executeQuery();
            if(rs.next()){
                return new Product(rs.getInt("id"),
                                      rs.getString("name"),
                                      rs.getDouble("price"),
                                      rs.getInt("quantity"));
            }
        }catch(SQLException e){
            System.out.println(e);
        }
        
        return null;
    }
    public void addOrder(Customer c,Cart cart){
        LocalDate curDate=LocalDate.now();
        String date=curDate.toString();
        try{
            //add order
            String sql="insert into [order] values(?,?,?)";
            PreparedStatement st=connection.prepareStatement(sql);
            st.setString(1, date);
            st.setInt(2, c.getId());
            st.setDouble(3, cart.getTotalMoney());
            st.executeUpdate();
            //lay id cua order vua add
            String sql1="select top 1 id from [order] order by id desc";
            PreparedStatement st1=connection.prepareStatement(sql1);
            ResultSet rs=st1.executeQuery();
            //add bang OrderDetail
            if(rs.next()){
                int oid=rs.getInt("id");
                for(Item i:cart.getItems()){
                    String sql2="insert into [orderline] values(?,?,?,?)";
                    PreparedStatement st2=connection.prepareStatement(sql2);
                    st2.setInt(1, oid);
                    st2.setInt(2, i.getProduct().getId());
                    st2.setInt(3, i.getQuantity());
                    st2.setDouble(4, i.getPrice());
                    st2.executeUpdate();
                }
            }
            //cap nhat lai so luong san pham
            String sql3="update product set quantity=quantity-? where id=?";
            PreparedStatement st3=connection.prepareStatement(sql3);
            for(Item i:cart.getItems()){
                st3.setInt(1, i.getQuantity());
                st3.setInt(2, i.getProduct().getId());
                st3.executeUpdate();
            }
        }catch(SQLException e){
            
        }
    }
    public static void main(String[] args) {
        DAO d=new DAO();
        System.out.println(d.getAll().get(0).getName());
    }
    
}
