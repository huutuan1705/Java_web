/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demoSpring.book;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author huutuan
 */
@RestController
@CrossOrigin
public class BookController {

    @GetMapping("/books")
    
    public List<Book> getAllBooks(Model model) throws Exception {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List<Book> books = new ArrayList<Book>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/book_demo", "root", "123456789");
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from book");
            while (resultSet.next()) {
                int bookcode = resultSet.getInt("bookcode");
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                String category = resultSet.getString("category");
                int approved = resultSet.getInt("approved");
                books.add(new Book(bookcode, title, author, category, approved == 0 ? false : true));
            }
        } // End of try block		
        catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("books", books);
        return books;
    }

    @GetMapping("/book/{bookcode}")
    public Book getBook(Model model, @PathVariable String bookcode) {
        model.addAttribute("bookcode", bookcode);
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet result = null;

        Book book = new Book();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/book_demo", "root", "123456789");
            ps = connection.prepareStatement("select * from book where bookcode = ?");
            ps.setInt(1, Integer.valueOf(bookcode));
            result = ps.executeQuery();
            while (result.next()) {
                book.setBookcode(result.getInt("bookcode"));
                book.setTitle(result.getString("title"));
                book.setAuthor(result.getString("author"));
                book.setCategory(result.getString("category"));
                book.setApproved(result.getInt("approved") != 0 ? true : false);
            }
        } // End of try block
        catch (Exception e) {
            e.printStackTrace();
        }
        return book;
    }

    @PostMapping("/book/save/{bookcode}")
    public String addBook(@RequestBody @Validated Book book, @PathVariable String bookcode) {
        Connection connection = null;
        PreparedStatement ps = null;
        int result = 0;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/book_demo", "root", "123456789");
            ps = connection.prepareStatement("INSERT INTO book VALUES (?, ?, ?, ?, ?)");
            ps.setInt(1, Integer.valueOf(book.getBookcode()));
            ps.setString(2, book.getTitle());
            ps.setString(3, book.getAuthor());
            ps.setString(4, book.getCategory());
            ps.setInt(5, book.isApproved() ? 1 : 0);
            result = ps.executeUpdate();

            ps.close();
            connection.close();

            // Redirect the response to success page
            return "redirect:/books";
        } // End of try block
        catch (Exception e) {
            e.printStackTrace();
        }
        return "error"; // tạo trang Error
    }

    @PutMapping("/book/save/{bookcode}")
    public void updateBook(@RequestBody @Validated Book book, @PathVariable String bookcode) {
        Connection connection = null;
        PreparedStatement ps = null;
        int result = 0;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/book_demo", "root", "123456789");
            ps = connection.prepareStatement("UPDATE book SET title=?,author=?,category=?,approved=? WHERE bookcode=?");
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setString(3, book.getCategory());
            ps.setInt(4, book.isApproved() ? 1 : 0);
            ps.setInt(5, Integer.valueOf(book.getBookcode()));
            result = ps.executeUpdate();

            ps.close();
            connection.close();

            // Redirect the response to success page
            
        } // End of try block
        catch (Exception e) {
            e.printStackTrace();
        }
 // tạo trang Error
    }
    
    @DeleteMapping("/book/delete/{bookcode}")
    public void deleteBook(@RequestBody @Validated Book book, @PathVariable String bookcode){
        Connection connection = null;
        PreparedStatement ps = null;
        int result = 0;
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/book_demo", "root", "123456789");
            ps = connection.prepareStatement("DELETE FROM book WHERE bookcode=?");
            ps.setInt(1, Integer.valueOf(book.getBookcode()));
            result = ps.executeUpdate();
            
            ps.close();
            connection.close();

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
