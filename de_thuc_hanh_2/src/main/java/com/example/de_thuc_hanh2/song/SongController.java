/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.de_thuc_hanh2.song;

import java.io.IOException;
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
public class SongController {
    private SongDAO songDAO = new SongDAO();
    
    @GetMapping("/songs")
    public String getAllSongs(Model model) throws IOException{
        List<Song> songs = songDAO.getAllSongs();
        model.addAttribute("songs",songs);
        return "songs";
    }
    
    @GetMapping("/song/{id}")
    public String getSong(Model model, @PathVariable String id){
        model.addAttribute("id",id);
        Song song = songDAO.getSong(Integer.valueOf(id));
        model.addAttribute("song",song);
        return "song-detail";
    }
    
    @PostMapping("/song/save/{id}")
    public String addSong(Song song, @PathVariable String id, Model model){
        if(songDAO.checkExist(song.getName(), song.getAuthor())){
            songDAO.insertSong(song);
        }else{
            String error = "Song does exist!!!";
            model.addAttribute("error", error);
            model.addAttribute("id", -1);
            return "song-detail";
        }
        
        return "redirect:/songs";
    }
    
    @PutMapping("/song/save/{id}")
    public String updateSong(Song song, @PathVariable String id, Model model){
        Song p = songDAO.getSong(Integer.valueOf(id));
        if(song.getName().equals(p.getName()) && song.getAuthor().equals(p.getAuthor())){
            songDAO.updateSong(song);
            return "redirect:/songs";
        }
        if(songDAO.checkExist(song.getName(), song.getAuthor())){
            songDAO.updateSong(song);
            return "redirect:/songs";
        }        
        String error = "Song does exist!!!";
        model.addAttribute("error", error);
        model.addAttribute("id", id);
        return "song-detail";
    }
    
    @DeleteMapping("/song/delete/{id}")
    public String deletePhone(Song song, @PathVariable String id){
        songDAO.deletePhone(song);
        return "redirect:/songs";
    }
}
