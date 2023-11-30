/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.SystemIntelligent.DAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author huutuan
 */
public class PositionCharLabelDAO extends DAO{
    public void deletePosition(String id){
        String sql = "DELETE FROM position_char_label WHERE idCharPos = ?";
        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, id);
            
            ps.execute();
            ps.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
