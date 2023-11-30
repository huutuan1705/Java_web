/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.SystemIntelligent.model;

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
public class PositionCharLabel {
    private String idCharPos;
    private double xTop, yTop, xBot, yBot;
    private CharSample sample;
    private CharLabel label;
}
