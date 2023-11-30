/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.deso2.phone;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

/**
 *
 * @author huutuan
 */
@Controller
public class PhoneController {
    private PhoneDAO phoneDAO = new PhoneDAO();
    
    @GetMapping("/phones")
    public String getAllPhones(Model model) throws IOException{
        List<Phone> phones = phoneDAO.getAllPhones();
        model.addAttribute("phones",phones);
        return "phones";
    }
    
    @GetMapping("/phone/{id}")
    public String getPhone(Model model, @PathVariable String id){
        model.addAttribute("id",id);
        Phone phone = phoneDAO.getPhone(Integer.valueOf(id));
        model.addAttribute("phone",phone);
        return "phone-detail";
    }
    
    @PostMapping("/phone/save/{id}")
    public String addPhone(Phone phone, @PathVariable String id, Model model){
        if(phoneDAO.checkExist(phone.getName(), phone.getBrand())){
            phoneDAO.insertPhone(phone);
        }else{
            String error = "Student does exist!!!";
            model.addAttribute("error", error);
            model.addAttribute("id", -1);
            return "phone-detail";
        }
        
        return "redirect:/phones";
    }
    
    @PutMapping("/phone/save/{id}")
    public String updatePhone(Phone phone, @PathVariable String id, Model model){
        Phone p = phoneDAO.getPhone(Integer.valueOf(id));
        if(phone.getName().equals(p.getName()) && phone.getBrand().equals(p.getBrand())){
            phoneDAO.updatePhone(phone);
            return "redirect:/phones";
        }
        if(phoneDAO.checkExist(phone.getName(), phone.getBrand())){
            phoneDAO.updatePhone(phone);
            return "redirect:/phones";
        }        
        String error = "Student does exist!!!";
        model.addAttribute("error", error);
        model.addAttribute("id", id);
        return "phone-detail";
    }
    
    @DeleteMapping("/phone/delete/{id}")
    public String deletePhone(Phone phone, @PathVariable String id){
        phoneDAO.deletePhone(phone);
        return "redirect:/phones";
    }
}
