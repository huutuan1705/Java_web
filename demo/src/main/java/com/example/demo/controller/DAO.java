/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.controller;

import com.example.demo.model.Book;
import com.example.demo.model.Category;
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
public class DAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/online_library";
    private String jdbcUserName = "root";
    private String jdbcPassWord = "123456789";
    
    private static final String SELECT_ALL_BOOKS = "SELECT * FROM online_library.book";    
    private static final String SELECT_BOOKS_BY_ID = "SELECT * FROM online_library.book where id = ?";
    
    private static final String SELECT_ALL_CATEGORIES = "select * from category"; 
    private static final String INSERT_BOOK_SQL = "INSERT INTO book VALUES(?,?,?,?,?,?,?,?)";
    private static final String UPDATE_BOOK = "UPDATE book SET title=?,author=?,"
            + "description=?,releaseDate=?,pages=?,categoryID=?,image=? WHERE id=?";
    
    public DAO() {
    }
    
    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUserName, jdbcPassWord);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }
    
    public Category getCategoryByID(int id){
        String sql = SELECT_ALL_CATEGORIES + " where id=?";
        Category category = new Category();
        try {
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                category = new Category(id, name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return category;
    }
    
    public List<Category> getAllCategory(){
        List<Category> categories = new ArrayList<>();
        try {
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(SELECT_ALL_CATEGORIES);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                categories.add(new Category(id, name));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }
    
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        try {
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(SELECT_ALL_BOOKS);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String author = rs.getString("author");
                String description = rs.getString("description");
                String releaseDate = rs.getString("releaseDate");
                int pages = rs.getInt("pages");
                int category = (rs.getInt("categoryID"));
                String image = rs.getString("image");
                books.add(new Book(id, title, author, description, releaseDate, pages, category, image));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }
    
    public Book getBook(int id) {
        Book book = new Book();
        try {
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(SELECT_BOOKS_BY_ID);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                book.setId(rs.getInt("id"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setDescription(rs.getString("description"));
                book.setReleaseDate(rs.getString("releaseDate"));
                book.setPages(rs.getInt("pages"));
                book.setCategoryID((rs.getInt("categoryID")));
                book.setImage(rs.getString("image"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }
    
    public Book addBook(Book book){
        try{
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(INSERT_BOOK_SQL);
            
            ps.setInt(1, book.getId());
            ps.setString(2, book.getTitle());
            ps.setString(3, book.getAuthor());
            ps.setString(4, book.getDescription());
            ps.setString(5, book.getReleaseDate());
            ps.setInt(6, book.getPages());
            ps.setInt(7, book.getCategoryID());
            ps.setString(8, book.getImage());
            
            ps.executeUpdate();           
            ps.close();
            connection.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return book;
    }
    
    public Book updateBook(Book book){
        try{
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(UPDATE_BOOK);

            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setString(3, book.getDescription());
            ps.setString(4, book.getReleaseDate());
            ps.setInt(5, book.getPages());
            ps.setInt(6, book.getCategoryID());
            ps.setString(7, book.getImage());
            ps.setInt(8, book.getId());
            
            ps.executeUpdate();           
            ps.close();
            connection.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return book;
    }
    
    public List<Book> getListbyPage(List<Book> list, int start, int end){
        ArrayList<Book> arr = new ArrayList<>();
        for(int i=start; i<end;i++){
            arr.add(list.get(i));
        }
        return arr;
    }
}
