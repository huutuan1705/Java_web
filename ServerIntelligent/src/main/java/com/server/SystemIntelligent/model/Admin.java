/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.server.SystemIntelligent.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author huutuan
 */
@NoArgsConstructor()
@AllArgsConstructor()
@Getter()
@Setter()
public class Admin {
    private String idAdmin;
    private String username;
    private String password;
    private String fullname;
    private String email, tel;
    
}
