/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bai4_plus;

import java.util.Scanner;

/**
 *
 * @author huutuan
 */
public class Main1 {
    public static void main(String[] args) {
        ChuChay c = new ChuChay();
        Thread t = new Thread(c);
        t.start();
        Scanner sc = new Scanner(System.in);
        String in = sc.next();
        if((in = sc.next()).equalsIgnoreCase("c")){
            c.stop();
        }
    }
}
