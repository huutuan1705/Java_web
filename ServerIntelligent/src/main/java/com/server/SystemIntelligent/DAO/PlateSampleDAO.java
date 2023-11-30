/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.server.SystemIntelligent.DAO;

import com.server.SystemIntelligent.model.Admin;
import com.server.SystemIntelligent.model.PlateLabel;
import com.server.SystemIntelligent.model.PlateSample;
import com.server.SystemIntelligent.model.PlateTrainDataset;
import com.server.SystemIntelligent.model.PositionPlateLabel;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
public class PlateSampleDAO extends DAO {

    public List<Pair<PlateSample, Integer>> getAllSamples() {
        String sql = "SELECT tls.*, a.*, tt.*, COUNT(ptll.idPlatecPos) AS positionCount\n"
                + "FROM plate_sample tls\n"
                + "LEFT JOIN admin a ON tls.idAdmin2 = a.idadmin\n"
                + "LEFT JOIN plate_train_dataset tt ON tls.idPlateTrainDataset = tt.idPlateTrain\n"
                + "LEFT JOIN position_plate_label ptll ON tls.idPlateSpl = ptll.idPlateSpl\n"
                + "GROUP BY tls.idPlateSpl, a.idadmin, tt.idPlateTrain;";

        List<Pair<PlateSample, Integer>> list = new ArrayList<>();
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
                String idData = rs.getString("tt.idPlateTrain");
                String name = rs.getString("tt.name");
                String path = rs.getString("tt.path");
                PlateTrainDataset dataset = new PlateTrainDataset(idData, name, path);

                //TrafficLightSample
                String idSample = rs.getString("tls.idPlateSpl");
                String nameImg = rs.getString("tls.nameImg");
                path = rs.getString("tls.path");
                String createDate = rs.getString("tls.createDate");
                PlateSample sample = new PlateSample(idSample, nameImg, path, createDate, admin, dataset);

                //TrafficLightLabel
                int cnt = rs.getInt("positionCount");

                list.add(new Pair<PlateSample, Integer>(sample, cnt));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteSample(String idSample) throws IOException {
        String sql = "DELETE FROM plate_sample WHERE idPlateSpl = ?";
        String sql1 = "SELECT * FROM plate_sample WHERE idPlateSpl = ?";
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
