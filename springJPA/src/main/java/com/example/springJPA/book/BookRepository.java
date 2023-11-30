/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.springJPA.book;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author huutuan
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Integer>{
    static final String SELECT_ALL_BOOKS = "Select * from book";
    
    @Query(value = SELECT_ALL_BOOKS, nativeQuery = true)
    public List<Book> findAll();
}
