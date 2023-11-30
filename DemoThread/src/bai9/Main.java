/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bai9;

/**
 *
 * @author huutuan
 */
public class Main {
    public static void main(String[] args) {
        Data d = new Data();
        InputThread t1 = new InputThread(d);
        OutputThread t2 = new OutputThread(d);
        t1.start();
        t2.start();
    }
}
