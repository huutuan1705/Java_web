/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.server.SystemIntelligent.DAO;

import com.server.SystemIntelligent.model.TrafficLightLabel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author huutuan
 */
public class TrafficLightLabelDAO extends DAO{
    public List<TrafficLightLabel> getLables(){
        String sql = "SELECT * FROM traffic_light_label";
        List<TrafficLightLabel> labels = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String id = rs.getString("idTrafficLabel");
                String title = rs.getString("title");
                TrafficLightLabel label = new TrafficLightLabel(id, title);
                labels.add(label);
//                return label;
            }
            return labels;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
}
