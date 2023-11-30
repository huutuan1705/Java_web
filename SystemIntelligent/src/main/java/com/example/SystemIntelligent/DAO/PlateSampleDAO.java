/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.SystemIntelligent.DAO;

import com.example.SystemIntelligent.model.Admin;
import com.example.SystemIntelligent.model.PlateLabel;
import com.example.SystemIntelligent.model.PlateSample;
import com.example.SystemIntelligent.model.PlateTrainDataset;
import com.example.SystemIntelligent.model.PositionPlateLabel;
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

    public Pair<PlateSample, List<PositionPlateLabel>> getSample(String id) {
        String sql1 = "SELECT tls.*, tt.*, a.* \n"
                + "FROM plate_sample as tls, plate_train_dataset as tt, admin as a\n"
                + "WHERE tls.idPlateTrainDataset = tt.idPlateTrain\n"
                + "and tls.idAdmin2 = a.idadmin and tls.idPlateSpl = ?;";

        String sql2 = "SELECT ptll.*, label.*\n"
                + "FROM position_plate_label as ptll, plate_label as label\n"
                + "WHERE ptll.idPlateLabel = label.idPlateLabel\n"
                + "and ptll.idPlateSpl = ?;";
        try {
            PreparedStatement ps1 = connection.prepareStatement(sql1);
            PreparedStatement ps2 = connection.prepareStatement(sql2);

            ps1.setString(1, id);
            ps2.setString(1, id);

            ResultSet rs1 = ps1.executeQuery();
            if (rs1.next()) {
                String idAdmin = rs1.getString("a.idadmin");
                String username = rs1.getString("a.username");
                String password = rs1.getString("a.password");
                String fullname = rs1.getString("a.fullname");
                String email = rs1.getString("a.email");
                String tel = rs1.getString("a.tel");
                Admin admin = new Admin(idAdmin, username, password, fullname, email, tel);

                String idData = rs1.getString("tt.idPlateTrain");
                String name = rs1.getString("tt.name");
                String path = rs1.getString("tt.path");
                PlateTrainDataset dataset = new PlateTrainDataset(idData, name, path);

                String idSample = rs1.getString("tls.idPlateSpl");
                String nameImg = rs1.getString("tls.nameImg");
                path = rs1.getString("tls.path");
                String createDate = rs1.getString("tls.createDate");
                PlateSample sample = new PlateSample(idSample, nameImg, path, createDate, admin, dataset);

                ResultSet rs2 = ps2.executeQuery();
                List<PositionPlateLabel> listPos = new ArrayList<>();
                while (rs2.next()) {
                    String idLabel = rs2.getString("label.idPlateLabel");
                    String nameLabel = rs2.getString("label.title");
                    PlateLabel label = new PlateLabel(idLabel, nameLabel);

                    String idPos = rs2.getString("ptll.idPlatecPos");
                    double xTop = rs2.getDouble("ptll.xTop");
                    double yTop = rs2.getDouble("ptll.yTop");
                    double xBot = rs2.getDouble("ptll.xBot");
                    double yBot = rs2.getDouble("ptll.yBot");

                    PositionPlateLabel position = new PositionPlateLabel(idPos, xTop, yTop, xBot, yBot, sample, label);
                    listPos.add(position);
                }
                return new Pair<PlateSample, List<PositionPlateLabel>>(sample, listPos);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void deleteSample(String idSample) throws IOException {
        String sql = "DELETE FROM plate_sample WHERE idPlateSpl = ?";
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
