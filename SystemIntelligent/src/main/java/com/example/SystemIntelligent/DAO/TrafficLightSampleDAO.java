/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.SystemIntelligent.DAO;

import com.example.SystemIntelligent.model.Admin;
import com.example.SystemIntelligent.model.PositionTrafficLightLabel;
import com.example.SystemIntelligent.model.TrafficLightLabel;
import com.example.SystemIntelligent.model.TrafficLightSample;
import com.example.SystemIntelligent.model.TrafficTrainDataset;
import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.util.Pair;

/**
 *
 * @author huutuan
 */
public class TrafficLightSampleDAO extends DAO {
    public List<Pair<TrafficLightSample, Integer>> getAllSamples() {
        
        List<Pair<TrafficLightSample, Integer>> list = new ArrayList<>();
        String sql = "SELECT tls.*, a.*, tt.*, COUNT(ptll.idTrafficPos) AS positionCount\n"
                + "FROM traffic_light_sample tls\n"
                + "LEFT JOIN admin a ON tls.idAdmin = a.idadmin\n"
                + "LEFT JOIN traffic_train_dataset tt ON tls.idTrafficTrain = tt.idTrafficTrain\n"
                + "LEFT JOIN position_traffic_light_label ptll ON tls.idTrafficSpl = ptll.idTrafficSample\n"
                + "GROUP BY tls.idTrafficSpl, a.idadmin, tt.idTrafficTrain;";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                //Admin
                String idAdmin = rs.getString("a.idadmin");
                String username = rs.getString("a.username");
                String password = rs.getString("a.password");
                String fullname = rs.getString("a.fullname");
                String email = rs.getString("a.email");
                String tel = rs.getString("a.tel");
                Admin admin = new Admin(idAdmin, username, password, fullname, email, tel);

                //TrafficTrainDataset
                String idData = rs.getString("tt.idTrafficTrain");
                String name = rs.getString("tt.name");
                String path = rs.getString("tt.path");
                TrafficTrainDataset dataset = new TrafficTrainDataset(idData, name, path);

                //TrafficLightSample
                String idSample = rs.getString("tls.idTrafficSpl");
                String nameImg = rs.getString("tls.nameImg");
                path = rs.getString("tls.path");
                String createDate = rs.getString("tls.createDate");
                TrafficLightSample sample = new TrafficLightSample(idSample, nameImg, path, createDate, admin, dataset);

                int cnt = rs.getInt("positionCount");

                list.add(new Pair<TrafficLightSample, Integer>(sample, cnt));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Pair<TrafficLightSample, List<PositionTrafficLightLabel>> getSample(String id) {
        String sql1 = "SELECT tls.*, tt.*, a.*\n"
                + "FROM traffic_light_sample as tls,\n"
                + "traffic_train_dataset as tt, admin as a\n"
                + "WHERE tls.idTrafficTrain = tt.idTrafficTrain \n"
                + "and tls.idAdmin = a.idadmin and tls.idTrafficSpl = ?;";
        
        String sql2 = "SELECT ptll.*, label.*\n"
                + "FROM position_traffic_light_label as ptll, traffic_light_label as label\n"
                + "WHERE ptll.idTrafficLabel = label.idTrafficLabel\n"
                + "and ptll.idTrafficSample = ?;";
        try {
            PreparedStatement ps1 = connection.prepareStatement(sql1);
            PreparedStatement ps2 = connection.prepareStatement(sql2);
            
            ps1.setString(1, id);
            ps2.setString(1, id);
            
            ResultSet rs1 = ps1.executeQuery();
            if(rs1.next()){
                String idAdmin = rs1.getString("a.idadmin");
                String username = rs1.getString("a.username");
                String password = rs1.getString("a.password");
                String fullname = rs1.getString("a.fullname");
                String email = rs1.getString("a.email");
                String tel = rs1.getString("a.tel");
                Admin admin = new Admin(idAdmin, username, password, fullname, email, tel);
                
                String idData = rs1.getString("tt.idTrafficTrain");
                String name = rs1.getString("tt.name");
                String path = rs1.getString("tt.path");
                TrafficTrainDataset dataset = new TrafficTrainDataset(idData, name, path);
                
                String idSample = rs1.getString("tls.idTrafficSpl");
                String nameImg = rs1.getString("tls.nameImg");
                path = rs1.getString("tls.path");
                String createDate = rs1.getString("tls.createDate");
                TrafficLightSample sample = new TrafficLightSample(idSample, nameImg, path, createDate, admin, dataset);
                
                ResultSet rs2 = ps2.executeQuery();
                List<PositionTrafficLightLabel> listPos = new ArrayList<>();
                while(rs2.next()){
                    String idLabel = rs2.getString("label.idTrafficLabel");
                    String nameLabel = rs2.getString("label.title");
                    TrafficLightLabel label = new TrafficLightLabel(idLabel, nameLabel);
                    
                    String idPos = rs2.getString("ptll.idTrafficPos");
                    double xTop = rs2.getDouble("ptll.xTop");
                    double yTop = rs2.getDouble("ptll.yTop");
                    double xBot = rs2.getDouble("ptll.xBot");
                    double yBot = rs2.getDouble("ptll.yBot");
                    
                    PositionTrafficLightLabel position = new PositionTrafficLightLabel(idPos, xTop, yTop, xBot, yBot, sample, label);
                    listPos.add(position);
                }
                return new Pair<TrafficLightSample, List<PositionTrafficLightLabel>>(sample, listPos);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    
    public void deleteSample(String idSample) {
        System.out.println(idSample);
        String sql = "DELETE FROM traffic_light_sample WHERE idTrafficSpl = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            
            ps.setString(1, idSample);

            ps.execute();
            ps.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
