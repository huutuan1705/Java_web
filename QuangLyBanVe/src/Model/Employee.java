/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author huutuan
 */
public class Employee {
    private String id, fullname;
    private double price;
    private String role, phoneNumber;

    public Employee() {
    }

    public Employee(String id, String fullname, double price, String role, String phoneNumber) {
        this.id = id;
        this.fullname = fullname;
        this.price = price;
        this.role = role;
        this.phoneNumber = phoneNumber;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public Object[] tooObjects(){
        return new Object[]{
            id, fullname, price, role, phoneNumber
        };
    }
}
