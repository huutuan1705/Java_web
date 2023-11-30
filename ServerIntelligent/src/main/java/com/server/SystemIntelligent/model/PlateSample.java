/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.server.SystemIntelligent.model;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Base64;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author huutuan
 */
@NoArgsConstructor()
@AllArgsConstructor()
@Getter()
@Setter()
public class PlateSample {
    private String idPlateSample, nameImg, path, createDate;
    private Admin createPerson;
    private PlateTrainDataset dataset;
    
    public String getConvertToBase64() throws FileNotFoundException, IOException{
        FileInputStream inputStream = new FileInputStream(new File(getPath() + getNameImg()));
        byte[] buffer = new byte[4096];
        
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            byteArrayOutputStream.write(buffer, 0, bytesRead);
        }
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        inputStream.close();
        return Base64.getEncoder().encodeToString(byteArray);
    }
}
