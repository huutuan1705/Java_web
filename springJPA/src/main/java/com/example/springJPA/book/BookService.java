/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.springJPA.book;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author huutuan
 */
@Service
public class BookService {
    @Autowired
    private BookRepository repo;
    
    public List<Book> getBooks(){
        return repo.findAll();
    }
}
