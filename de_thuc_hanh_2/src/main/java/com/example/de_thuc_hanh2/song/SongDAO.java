/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.de_thuc_hanh2.song;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author huutuan
 */
public class SongDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/thuc_hanh2";
    private String jdbcUserName = "root";
    private String jdbcPassWord = "123456789";
    
    private static final String SELECT_ALL_SONGS = "select * from song";
    private static final String SELECT_SONG_BY_ID = "select * from song where id=?";
    private static final String INSERT_SONG_SQL = "INSERT INTO song VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE_SONG_SQL = "UPDATE song SET name=?,author=?,date=?,published=? WHERE id=?";
    private static final String DELETE_SONG_SQL = "DELETE FROM song WHERE id=?";
    private static final String CHECK_EXIST_SQL = "select * from song where name=? and author=?";

    public SongDAO() {
    }
    
    protected Connection getConnection(){
        Connection connection = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUserName, jdbcPassWord);
        }catch(SQLException e){
            e.printStackTrace();
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        return connection;
    }
    
    public List<Song> getAllSongs(){
        List<Song> songs = new ArrayList<>();
        try{
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(SELECT_ALL_SONGS);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String author = rs.getString("author");
                String date = rs.getString("date");
                int published = rs.getInt("published");
                songs.add(new Song(id, name, author, date, published==0 ? false:true));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return songs;
    }
    
    public Song getSong(int id){
        Song song = new Song();
        try{
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(SELECT_SONG_BY_ID);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                song.setId(rs.getInt("id"));
                song.setName(rs.getString("name"));
                song.setAuthor(rs.getString("author"));
                song.setDate(rs.getString("date"));
                song.setPublished(rs.getInt("published") != 0 ? true : false);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return song;
    }
    
    public void insertSong(Song song){
        try{
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(INSERT_SONG_SQL);
            
            ps.setInt(1, Integer.valueOf(song.getId()));
            ps.setString(2, song.getName());
            ps.setString(3, song.getAuthor());
            ps.setString(4, song.getDate());
            ps.setInt(5, song.isPublished()? 1 : 0);
            
            ps.executeUpdate();
            
            ps.close();
            connection.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public boolean checkExist(String name, String author){
        try{
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(CHECK_EXIST_SQL);
            ps.setString(1, name);
            ps.setString(2, author);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                return false;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return true;
    }
    
    public void updateSong(Song song){
        try{
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(UPDATE_SONG_SQL);
            
            ps.setString(1, song.getName());
            ps.setString(2, song.getAuthor());
            ps.setString(3, song.getDate());
            ps.setInt(4, song.isPublished()? 1 : 0);
            ps.setInt(5, Integer.valueOf(song.getId()));
            ps.executeUpdate();
            
            ps.close();
            connection.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public void deletePhone(Song song){
        try{
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(DELETE_SONG_SQL);
            
            ps.setInt(1, Integer.valueOf(song.getId()));
            ps.executeUpdate();
            
            ps.close();
            connection.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
