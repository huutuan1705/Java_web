/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.controller;

import com.example.demo.model.Book;
import com.example.demo.model.Category;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author huutuan
 */
@Controller
public class controller {
    private DAO d = new DAO();
    
    @GetMapping("/")
    public String getHomePage(Model model){
        return "homePage";
    }
    
    @GetMapping("/adminBooks")
    public String getAllBooks(Model model) throws Exception{
        return "redirect:/adminBooks/page/1";
    }
    
    @GetMapping("/adminBooks/page/{numberPage}")
    public String getBookByPage(Model model, @PathVariable String numberPage){
        List<Book> allBooks = d.getAllBooks();
        
        int page, numPerPage = 6;
        int size = allBooks.size();
        
        int num =(size%numPerPage==0?(size/numPerPage):(size/numPerPage+1));
        
        page = Integer.parseInt(numberPage);
        int start, end;
        start = (page-1)*numPerPage;
        end = Math.min(page*numPerPage, size);
        List<Book> books = d.getListbyPage(allBooks, start, end);
        
        model.addAttribute("adminBooks",books);
        model.addAttribute("page", page);
        model.addAttribute("num", num);
        return "adminBooks";
    }
    
    @GetMapping("/adminBookDetail/{id}")
    public String getBook(Model model, @PathVariable String id){
        model.addAttribute("id",id);
        Book book = d.getBook(Integer.valueOf(id));
        model.addAttribute("adminBook",book);
                
        List<Category> categories = d.getAllCategory();
        model.addAttribute("adminCategories", categories);
        return "book-detail";
    }
    
    @PostMapping("/adminBook/save/{id}")
    public String addBook(@PathVariable String id, @ModelAttribute(name = "adminBook") Book book, 
            Model model, @RequestParam("image_input") MultipartFile multipartFile)throws IOException{ 
        String filename = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        book.setImage(filename);
        
        Book savedBook = d.addBook(book);
        String uploadDir = "book-image/" + savedBook.getId();
        
        Path uploadPath = Paths.get(uploadDir);
        
        if(!Files.exists(uploadPath)){
            Files.createDirectories(uploadPath);
        }
        
        try(InputStream inputStream = multipartFile.getInputStream()){
            Path filePath = uploadPath.resolve(filename);
            System.out.println(filePath.toString());
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        }catch(IOException e){
            System.out.println(e);
        }
        
        
        return "redirect:/adminBooks";
    }
    
    @PutMapping("/adminBook/save/{id}")
    public String updateBook(@PathVariable String id, @ModelAttribute(name = "adminBook") Book book, 
            Model model, @RequestParam("image_input") MultipartFile multipartFile)throws IOException{
        String filename = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        book.setImage(filename);
        
        Book savedBook = d.updateBook(book);
        String uploadDir = "book-image/" + savedBook.getId();
        
        Path uploadPath = Paths.get(uploadDir);
        
        if(!Files.exists(uploadPath)){
            Files.createDirectories(uploadPath);
        }
        
        try(InputStream inputStream = multipartFile.getInputStream()){
            Path filePath = uploadPath.resolve(filename);
            System.out.println(filePath.toString());
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        }catch(IOException e){
            System.out.println(e);
        }
        return "redirect:/adminBooks";
    }
}
