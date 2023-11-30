/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.SystemIntelligent.controller;

import com.example.SystemIntelligent.DAO.AdminDAO;
import com.example.SystemIntelligent.DAO.CharSampleDAO;
import com.example.SystemIntelligent.DAO.PlateSampleDAO;
import com.example.SystemIntelligent.DAO.PositionCharLabelDAO;
import com.example.SystemIntelligent.DAO.PositionPlateLabelDAO;
import com.example.SystemIntelligent.DAO.PositionTrafficLightLabelDAO;
import com.example.SystemIntelligent.DAO.TrafficLightSampleDAO;
import com.example.SystemIntelligent.model.Admin;
import com.example.SystemIntelligent.model.CharSample;
import com.example.SystemIntelligent.model.PlateSample;
import com.example.SystemIntelligent.model.PositionCharLabel;
import com.example.SystemIntelligent.model.PositionPlateLabel;
import com.example.SystemIntelligent.model.PositionTrafficLightLabel;
import com.example.SystemIntelligent.model.TrafficLightSample;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.util.Pair;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author huutuan
 */
@Controller
public class ServerController {
    private AdminDAO adminDAO = new AdminDAO();
    private TrafficLightSampleDAO trafficLightSampleDAO = new TrafficLightSampleDAO();
    private CharSampleDAO charSampleDAO = new CharSampleDAO();
    private PlateSampleDAO plateSampleDAO = new PlateSampleDAO();
    private PositionTrafficLightLabelDAO positionTrafficLabelDAO = new PositionTrafficLightLabelDAO();
    private PositionCharLabelDAO positionCharLabelDAO = new PositionCharLabelDAO();
    private PositionPlateLabelDAO positionPlateLabelDAO = new PositionPlateLabelDAO();
    
    @GetMapping("/login")
    public String getLoginForm(){
        return "login";
    }
    
    @PostMapping("/login")
    public String check_login(@ModelAttribute("username") String username, Model model,
            @ModelAttribute("password") String password, HttpSession session){
        Admin admin = adminDAO.check_login(username, password);
        if(admin==null){
            String error = "Username or password is incorrect!!";
            model.addAttribute("error",error);
            return "login";
        }
        session.setAttribute("admin",admin);
        return "redirect:/";
    }
    
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("admin");
        return "redirect:/login";
    }
    
    @GetMapping("/")
    public String home(HttpSession session){
        Admin admin = (Admin)session.getAttribute("admin");
        if(admin == null){
            return "redirect:/login";
        }
        return "home";
    }
    
    @GetMapping("/trafficmanage/page/{pageNumber}")
    public String trafficLightManage(HttpSession session, Model model, 
            @PathVariable String pageNumber) throws FileNotFoundException, IOException{
        Admin admin = (Admin)session.getAttribute("admin");
        if(admin == null){
            return "redirect:/login";
        }
        List<Pair<TrafficLightSample, Integer>> allTrafficSample = 
                trafficLightSampleDAO.getAllSamples();
        int page, numPerPage = 5;
        int size = allTrafficSample.size();
        
        int num =(size%numPerPage==0?(size/numPerPage):(size/numPerPage+1));
        
        page = Integer.parseInt(pageNumber);
        int start, end;
        start = (page-1)*numPerPage;
        end = Math.min(page*numPerPage, size);
        
        List<Pair<TrafficLightSample, Integer>> listSample = new ArrayList<>();
        for(int i=start;i<end;i++){
            listSample.add(allTrafficSample.get(i));
        }
        
        model.addAttribute("listSample", listSample);
        model.addAttribute("page", page);
        model.addAttribute("num", num==0?1:num);
        return "trafficManageMain";
    }
    
    @GetMapping("/deletetraffic/{id}")
    public String deleteTrafficSample(@PathVariable String id, HttpSession session) 
            throws IOException{
        System.out.println(id);
        Admin admin = (Admin)session.getAttribute("admin");
        if(admin == null){
            return "redirect:/login";
        }
        trafficLightSampleDAO.deleteSample(id);
        return "redirect:/trafficmanage/page/1";
    }
    
    @GetMapping("/trafficmanage/detailsample/{id}")
    public String getDetailTrafficSample(@PathVariable("id") String id, HttpSession session,
            Model model){
        Admin admin = (Admin)session.getAttribute("admin");
        if(admin == null){
            return "redirect:/login";
        }
        
        Pair<TrafficLightSample, List<PositionTrafficLightLabel>> sample = 
                trafficLightSampleDAO.getSample(id);
        
        model.addAttribute("sample", sample);
        
        return "trafficSampleDetail";
    }
    
    @GetMapping("/deleteTrafficPos/{idSample}&{idPos}")
    public String deleteTrafficPos(@PathVariable String idSample, @PathVariable String idPos, HttpSession session){
        Admin admin = (Admin)session.getAttribute("admin");
        if(admin == null){
            return "redirect:/login";
        }
        System.out.println(idPos);
        positionTrafficLabelDAO.deletePosition(idPos);
        
        return "redirect:/trafficmanage/detailsample/" + idSample;
    }
    
    @GetMapping("/platemanage/page/{pageNumber}")
    public String plateManage(HttpSession session, Model model, 
            @PathVariable String pageNumber) throws FileNotFoundException, IOException{
        Admin admin = (Admin)session.getAttribute("admin");
        if(admin == null){
            return "redirect:/login";
        }
        List<Pair<PlateSample, Integer>> allPlateSample = 
            plateSampleDAO.getAllSamples();
        int page, numPerPage = 5;
        int size = allPlateSample.size();
        
        int num =(size%numPerPage==0?(size/numPerPage):(size/numPerPage+1));
        
        page = Integer.parseInt(pageNumber);
        int start, end;
        start = (page-1)*numPerPage;
        end = Math.min(page*numPerPage, size);
        
        List<Pair<PlateSample, Integer>> listSample = new ArrayList<>();
        for(int i=start;i<end;i++){
            listSample.add(allPlateSample.get(i));
        }
        
        model.addAttribute("listSample", listSample);
        model.addAttribute("page", page);
        model.addAttribute("num", num==0?1:num);
        return "plateManageMain";
    }
    
    @GetMapping("/platemanage/detailsample/{id}")
    public String getDetailPlateSample(@PathVariable("id") String id, HttpSession session,
            Model model){
        Admin admin = (Admin)session.getAttribute("admin");
        if(admin == null){
            return "redirect:/login";
        }
        
        Pair<PlateSample, List<PositionPlateLabel>> sample = 
                plateSampleDAO.getSample(id);
        
        model.addAttribute("sample", sample);
        
        return "plateSampleDetail";
    }
    
    @GetMapping("/deleteplate/{id}")
    public String deletePlateSample(@PathVariable String id, HttpSession session) 
            throws IOException{
        System.out.println(id);
        Admin admin = (Admin)session.getAttribute("admin");
        if(admin == null){
            return "redirect:/login";
        }
        plateSampleDAO.deleteSample(id);
        return "redirect:/platemanage/page/1";
    }
    
    @GetMapping("/deletePlatePos/{idSample}&{idPos}")
    public String deletePlatePos(@PathVariable("idSample") String idSample, 
            @PathVariable("idPos") String idPos, HttpSession session){
        Admin admin = (Admin)session.getAttribute("admin");
        if(admin == null){
            return "redirect:/login";
        }
        System.out.println(idPos);
        positionPlateLabelDAO.deletePosition(idPos);
        
        return "redirect:/platemanage/detailsample/" + idSample;
    }
    
    @GetMapping("/charmanage/page/{pageNumber}")
    public String charManage(HttpSession session, Model model, 
            @PathVariable String pageNumber) throws FileNotFoundException, IOException{
        Admin admin = (Admin)session.getAttribute("admin");
        if(admin == null){
            return "redirect:/login";
        }
        List<Pair<CharSample, Integer>> allCharSample = 
            charSampleDAO.getAllSamples();
        int page, numPerPage = 5;
        int size = allCharSample.size();
        
        int num =(size%numPerPage==0?(size/numPerPage):(size/numPerPage+1));
        
        page = Integer.parseInt(pageNumber);
        int start, end;
        start = (page-1)*numPerPage;
        end = Math.min(page*numPerPage, size);
        
        List<Pair<CharSample, Integer>> listSample = new ArrayList<>();
        for(int i=start;i<end;i++){
            listSample.add(allCharSample.get(i));
        }
        
        model.addAttribute("listSample", listSample);
        model.addAttribute("page", page);
        model.addAttribute("num", num==0?1:num);
        return "charManageMain";
    }
    
    @GetMapping("/charmanage/detailsample/{id}/page/{pageNumber}")
    public String getDetailCharSample(@PathVariable("id") String id, HttpSession session,
            Model model, @PathVariable("pageNumber") String pageNumber){
        Admin admin = (Admin)session.getAttribute("admin");
        if(admin == null){
            return "redirect:/login";
        }
        
        Pair<CharSample, List<PositionCharLabel>> sample = 
                charSampleDAO.getSample(id);
        int page, numPerPage = 2;
        int size = sample.getValue().size();
        int num =(size%numPerPage==0?(size/numPerPage):(size/numPerPage+1));
        
        page = Integer.parseInt(pageNumber);
        int start, end;
        start = (page-1)*numPerPage;
        end = Math.min(page*numPerPage, size);
        
        List<PositionCharLabel> listPosition = new ArrayList<>();
        for(int i=start;i<end;i++){
            listPosition.add(sample.getValue().get(i));
        }
        model.addAttribute("sample", sample);
        model.addAttribute("listPos", listPosition);
        model.addAttribute("page", page);
        model.addAttribute("num", num==0?1:num);
        return "charSampleDetail";
    }
    
    @GetMapping("/deletechar/{id}")
    public String deleteCharSample(@PathVariable String id, HttpSession session) 
            throws IOException{
        System.out.println(id);
        Admin admin = (Admin)session.getAttribute("admin");
        if(admin == null){
            return "redirect:/login";
        }
        charSampleDAO.deleteSample(id);
        return "redirect:/charmanage/page/1";
    }
    
    @GetMapping("/deleteCharPos/{idSample}&{idPos}")
    public String deleteCharPos(@PathVariable("idSample") String idSample, 
            @PathVariable("idPos") String idPos, HttpSession session){
        Admin admin = (Admin)session.getAttribute("admin");
        if(admin == null){
            return "redirect:/login";
        }
        System.out.println(idPos);
        positionCharLabelDAO.deletePosition(idPos);
        
        return "redirect:/charmanage/detailsample/" + idSample + "/page/1";
    }
}
