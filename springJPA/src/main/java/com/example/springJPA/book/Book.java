/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.springJPA.book;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author huutuan
 */
@Entity
public class Book {
    @Id
    private int bookcode;
    private String title, author, category;
    private boolean approved;

    public Book() {
    }

    public Book(int bookcode, String title, String author, String category, boolean approved) {
        this.bookcode = bookcode;
        this.title = title;
        this.author = author;
        this.category = category;
        this.approved = approved;
    }

    public int getBookcode() {
        return bookcode;
    }

    public void setBookcode(int bookcode) {
        this.bookcode = bookcode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }
    
}