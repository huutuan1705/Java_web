/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.server.SystemIntelligent.DAO;

import com.server.SystemIntelligent.model.PositionTrafficLightLabel;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author huutuan
 */
public class PositionTrafficLightLabelDAO extends DAO{
    public void updatePosition(PositionTrafficLightLabel position){
        String sql1 = "UPDATE position_traffic_light_label SET xTop=?, yTop=?, xBot=?, yBot=?,"
                + "idTrafficLabel=? WHERE idTrafficPos = ?";
        try{
            PreparedStatement ps1 = connection.prepareStatement(sql1);
            ps1.setDouble(1, position.getXTop());
            ps1.setDouble(2, position.getYTop());
            ps1.setDouble(3, position.getXBot());
            ps1.setDouble(4, position.getYBot());
            ps1.setString(5, position.getLabel().getIdTrafficLabel());
            ps1.setString(6, position.getIdTrafficPos());
            
            ps1.executeUpdate();
            ps1.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public void deletePosition(String id){
        String sql = "DELETE FROM position_traffic_light_label WHERE idTrafficPos = ?";
        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, id);
            
            ps.execute();
            ps.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public void addPosition(PositionTrafficLightLabel position){
        String sql = "INSERT INTO position_traffic_light_label VALUES(?,?,?,?,?,?,?)";
        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, position.getIdTrafficPos());
            ps.setDouble(2, position.getXTop());
            ps.setDouble(3, position.getYTop());
            ps.setDouble(4, position.getXBot());
            ps.setDouble(5, position.getYBot());
            ps.setString(6, position.getSample().getIdTrafficSample());
            ps.setString(7, position.getLabel().getIdTrafficLabel());
            
            ps.executeUpdate();           
            ps.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
