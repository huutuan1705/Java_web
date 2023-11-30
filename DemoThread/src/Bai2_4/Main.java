/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Bai2_4;

import java.util.Scanner;

/**
 *
 * @author huutuan
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Input n: ");
        int n = Integer.parseInt(sc.nextLine());
        Thread1 t1 = new Thread1(n);
        Thread12 t2 = new Thread12(n);
        t1.start();
        t2.start();
    }
}
