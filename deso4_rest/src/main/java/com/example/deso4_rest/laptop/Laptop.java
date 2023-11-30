/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.deso4_rest.laptop;

import java.time.LocalDate;

/**
 *
 * @author huutuan
 */
public class Laptop {
    private int id;
    private String name;
    private String date;
    private String brand;
    private boolean sold;

    public Laptop() {
    }

    public Laptop(int id, String name, String date, String brand, boolean sold) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.brand = brand;
        this.sold = sold;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }
    
}
