/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.server.SystemIntelligent.controller;

import com.server.SystemIntelligent.DAO.AdminDAO;
import com.server.SystemIntelligent.DAO.CharSampleDAO;
import com.server.SystemIntelligent.DAO.PlateSampleDAO;
import com.server.SystemIntelligent.DAO.PositionTrafficLightLabelDAO;
import com.server.SystemIntelligent.DAO.TrafficLightLabelDAO;
import com.server.SystemIntelligent.DAO.TrafficLightSampleDAO;
import com.server.SystemIntelligent.model.Admin;
import com.server.SystemIntelligent.model.CharSample;
import com.server.SystemIntelligent.model.PlateSample;
import com.server.SystemIntelligent.model.PositionPlateLabel;
import com.server.SystemIntelligent.model.PositionTrafficLightLabel;
import com.server.SystemIntelligent.model.TrafficLightLabel;
import com.server.SystemIntelligent.model.TrafficLightSample;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import javafx.util.Pair;
import javax.servlet.http.HttpSession;
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
public class ServerController {
    private AdminDAO adminDAO = new AdminDAO();
    private CharSampleDAO charSampleDAO = new CharSampleDAO();
    private PlateSampleDAO plateSampleDAO = new PlateSampleDAO();
    private TrafficLightLabelDAO trafficLightLabelDAO = new TrafficLightLabelDAO();
    private TrafficLightSampleDAO trafficLightSampleDAO = new TrafficLightSampleDAO();
    private PositionTrafficLightLabelDAO positionTrafficLabelDAO = new PositionTrafficLightLabelDAO();
    
    @GetMapping("/login")
    public String getLoginForm(){
        return "login";
    }
    
    
    @PostMapping("/login")
    public String postLoginForm(@ModelAttribute("username") String username, Model model,
            @ModelAttribute("password") String password, HttpSession session){
//        System.out.println(username + " " + password);
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
        model.addAttribute("sampleRaw", sample.getKey());
        return "trafficDetail";
    }
    
    @PostMapping("/updateTrafficImg/{id}")
    public String updateTrafficImg(@PathVariable String id, HttpSession session, //@ModelAttribute TrafficLightSample sampleRaw,
            @RequestParam("image_input") MultipartFile image) throws IOException{
        Admin admin = (Admin)session.getAttribute("admin");
        if(admin == null){
            return "redirect:/login";
        }
//        System.out.println(sampleRaw.getNameImg());
        TrafficLightSample sample = trafficLightSampleDAO.getSample(id).getKey();
        String folder = sample.getPath();
        
        String oldImg = sample.getNameImg();
        String newImg = image.getOriginalFilename();
        
        Path oldPath = Paths.get(folder, oldImg);
        Path newPath = Paths.get(folder, newImg);
        
        Files.write(newPath, image.getBytes());
        Files.delete(oldPath);
        
        sample.setNameImg(newImg);
        trafficLightSampleDAO.updateSample(sample);
        return "redirect:/trafficmanage/detailsample/" + id;
    }
    
    @GetMapping("/deleteTrafficPos/{idSample}&{idPos}")
    public String deletePos(@PathVariable String idSample, @PathVariable String idPos, HttpSession session){
        Admin admin = (Admin)session.getAttribute("admin");
        if(admin == null){
            return "redirect:/login";
        }
        System.out.println(idPos);
        positionTrafficLabelDAO.deletePosition(idPos);
        
        return "redirect:/trafficmanage/detailsample/" + idSample;
    }
    
    @GetMapping("/addTrafficPos/{idSample}")
    public String getAddTrafficPosForm(@PathVariable String idSample, HttpSession session,
            Model model){
        Admin admin = (Admin)session.getAttribute("admin");
        if(admin == null){
            return "redirect:/login";
        }
        
        TrafficLightSample sample = trafficLightSampleDAO.getSample(idSample).getKey();
        List<TrafficLightLabel> labels = trafficLightLabelDAO.getLables();
        
        model.addAttribute("labels", labels);
        model.addAttribute("sample", sample);
        return "addTrafficPos";
    }
    
    @PostMapping("/addTrafficPos/{idSample}")
    public String addTrafficPosForm(@PathVariable String idSample, HttpSession session,
            @ModelAttribute("title") String title,
            @ModelAttribute("xtop") String xtop,
            @ModelAttribute("ytop") String ytop,
            @ModelAttribute("xbot") String xbot,
            @ModelAttribute("ybot") String ybot){
        
        Admin admin = (Admin)session.getAttribute("admin");
        if(admin == null){
            return "redirect:/login";
        }
        Pair<TrafficLightSample, List<PositionTrafficLightLabel>> sample = 
                trafficLightSampleDAO.getSample(idSample);
        
        //get number of last position of sample
        List<PositionTrafficLightLabel> list = sample.getValue();
        String idPos_raw = list.get(list.size()-1).getIdTrafficPos();
        
        String numberId = "";
        for(int i=idPos_raw.length()-1;i>=0;i--){
            if(idPos_raw.charAt(i)>='0' && idPos_raw.charAt(i)<='9'){
                numberId += idPos_raw.charAt(i);
            }
            else{
                break;
            }
        }
        int numberIdPos = 0;
        for(int i=0;i<numberId.length();i++){
            numberIdPos = numberIdPos*10 + ((int)numberId.charAt(i)-48);
        }
        
        String idPos = idSample + "_lbl" + (numberIdPos+1);
        
        System.out.println(idPos);
        
        PositionTrafficLightLabel position = new PositionTrafficLightLabel();
        position.setIdTrafficPos(idPos);
        position.setXBot(Double.parseDouble(xbot));
        position.setYBot(Double.parseDouble(ybot));
        position.setXTop(Double.parseDouble(xtop));
        position.setYTop(Double.parseDouble(ytop));
        
        List<TrafficLightLabel> listLabel = trafficLightLabelDAO.getLables();
        TrafficLightLabel trafficLightLabel = new TrafficLightLabel();
        for(int i=0;i<listLabel.size();i++){
            if(title.equals(listLabel.get(i).getIdTrafficLabel())){
                trafficLightLabel = listLabel.get(i);
            }
        }
        position.setLabel(trafficLightLabel);
        position.setSample(sample.getKey());
//        System.out.println(position.getLabel().getTitle());
        positionTrafficLabelDAO.addPosition(position);
        
        return "redirect:/trafficmanage/detailsample/" + idSample;
    }
    
    @GetMapping("/updateTrafficPosition/{idSample}&{idPos}")
    public String getUpdateTrafficPositionForm(@PathVariable String idSample, @PathVariable String idPos,
            HttpSession session, Model model){
        Admin admin = (Admin)session.getAttribute("admin");
        if(admin == null){
            return "redirect:/login";
        }
        Pair<TrafficLightSample, List<PositionTrafficLightLabel>> sample = 
                trafficLightSampleDAO.getSample(idSample);
        
        List<TrafficLightLabel> labels = trafficLightLabelDAO.getLables();
        
        List<PositionTrafficLightLabel> listPos = sample.getValue();
        PositionTrafficLightLabel position = new PositionTrafficLightLabel();
        for(int i=0;i<listPos.size();i++){
            if(listPos.get(i).getIdTrafficPos().equals(idPos)){
                position = listPos.get(i);
                break;
            }
        }
        
        model.addAttribute("position", position);
        model.addAttribute("labels", labels);
        model.addAttribute("sample", sample);
        return "trafficUpdatePos";
    }
    
    @PutMapping("/updateTrafficPos/{idPos}")
    public String updateTrafficPosition(@PathVariable String idPos, HttpSession session,
            @ModelAttribute("idTrafficSample") String idSample, 
            @ModelAttribute("title") String title,
            @ModelAttribute("xtop") String xtop,
            @ModelAttribute("ytop") String ytop,
            @ModelAttribute("xbot") String xbot,
            @ModelAttribute("ybot") String ybot){
        Admin admin = (Admin)session.getAttribute("admin");
        if(admin == null){
            return "redirect:/login";
        }
        
        Pair<TrafficLightSample, List<PositionTrafficLightLabel>> sample = 
                trafficLightSampleDAO.getSample(idSample);
        List<PositionTrafficLightLabel> listPos = sample.getValue();
        PositionTrafficLightLabel position = new PositionTrafficLightLabel();
        for(int i=0;i<listPos.size();i++){
            if(listPos.get(i).getIdTrafficPos().equals(idPos)){
                position = listPos.get(i);
                break;
            }
        }
        
        position.setXBot(Double.parseDouble(xbot));
        position.setYBot(Double.parseDouble(ybot));
        position.setXTop(Double.parseDouble(xtop));
        position.setYTop(Double.parseDouble(ytop));
        
        List<TrafficLightLabel> listLabel = trafficLightLabelDAO.getLables();
        TrafficLightLabel trafficLightLabel = new TrafficLightLabel();
        for(int i=0;i<listLabel.size();i++){
            if(title.equals(listLabel.get(i).getIdTrafficLabel())){
                trafficLightLabel = listLabel.get(i);
            }
        }
        position.setLabel(trafficLightLabel);
        positionTrafficLabelDAO.updatePosition(position);
        
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
    
    @PostMapping("/deleteplate/{id}")
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
    
    @PostMapping("/deletechar/{id}")
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
}
