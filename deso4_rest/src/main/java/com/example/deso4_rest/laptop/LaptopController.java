/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.deso4_rest.laptop;

import java.util.List;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author huutuan
 */
@RestController
@CrossOrigin
public class LaptopController {
    private LaptopDAO d = new LaptopDAO();
    
    @GetMapping("/laptops")
    public List<Laptop> getAllLaptops() throws Exception{
        List<Laptop> laptops = d.getAllLaptops();
        return laptops;
    }
    
    @GetMapping("/laptop/{id}")
    public Laptop getLaptop(@PathVariable String id){
        Laptop laptop = d.getLaptop(Integer.parseInt(id));
        return laptop;
    }
    
    @GetMapping("/laptops/{tmp}")
    public List<Laptop> getLaptopsByNameAndBrand(@PathVariable String tmp){
        List<Laptop> laptops = d.searchByNameAndBrand(tmp);
        return laptops;
    }
}
