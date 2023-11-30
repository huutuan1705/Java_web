/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.springJPA.book;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author huutuan
 */
@Controller
public class BookController {
    @Autowired
    private BookService service;
    
    @GetMapping("/books")
    public String getAllBooks(Model model) throws Exception{
        List<Book> books = service.getBooks();
        model.addAttribute("books", books);
        return "books";
    }
}
