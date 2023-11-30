/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.server.SystemIntelligent.DAO;

import com.server.SystemIntelligent.model.Admin;
import com.server.SystemIntelligent.model.CharSample;
import com.server.SystemIntelligent.model.CharTrainDataset;
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
public class CharSampleDAO extends DAO {

    public List<Pair<CharSample, Integer>> getAllSamples() {
        String sql = "SELECT tls.*, a.*, tt.*, COUNT(ptll.idCharPos) AS positionCount\n"
                + "FROM char_sample tls\n"
                + "LEFT JOIN admin a ON tls.idAdmin3 = a.idadmin\n"
                + "LEFT JOIN char_train_dataset tt ON tls.idCharTrainDataset = tt.idCharTrain\n"
                + "LEFT JOIN position_char_label ptll ON tls.idCharSpl = ptll.idCharSample\n"
                + "GROUP BY tls.idCharSpl, a.idadmin, tt.idCharTrain;";

        List<Pair<CharSample, Integer>> list = new ArrayList<>();
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
                String idData = rs.getString("tt.idCharTrain");
                String name = rs.getString("tt.name");
                String path = rs.getString("tt.path");
                CharTrainDataset dataset = new CharTrainDataset(idData, name, path);

                //TrafficLightSample
                String idSample = rs.getString("tls.idCharSpl");
                String nameImg = rs.getString("tls.nameImg");
                path = rs.getString("tls.path");
                String createDate = rs.getString("tls.createDate");
                CharSample sample = new CharSample(idSample, nameImg, path, createDate, admin, dataset);

                //TrafficLightLabel
                int cnt = rs.getInt("positionCount");

                list.add(new Pair<CharSample, Integer>(sample, cnt));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteSample(String idSample) {
        String sql = "DELETE FROM char_sample WHERE idCharSpl = ?";
        String sql1 = "SELECT * FROM char_sample WHERE idCharSpl = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            PreparedStatement ps1 = connection.prepareStatement(sql1);
            
            ps1.setString(1, idSample);
            ResultSet rs = ps1.executeQuery();
            String path = "";
            String nameImg = "";
            if (rs.next()){
                path = rs.getString("path");
                nameImg = rs.getString("nameImg");
            }
            String absolutePath = path + nameImg;
            absolutePath = absolutePath.replace("/", "\\\\");
            File file = new File(absolutePath);
            if(file.delete()) { 
                System.out.println(file.getName() + " is deleted!");
             } else {
                System.out.println("Delete operation is failed.");
                }
            
            ps.setString(1, idSample);

            ps.execute();
            ps.close();
            ps1.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
