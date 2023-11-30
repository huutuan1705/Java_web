/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author NguyenHuuTuan
 */
//@WebServlet(name = "TinhServlet", urlPatterns = {"/tinh"})
public class TinhServlet extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //super.doPost(req, resp); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        String dai_raw = request.getParameter("dai");
        String rong_raw = request.getParameter("rong");
//        String cv = request.getParameter("cv");
//        String dt = request.getParameter("dt");
        String[] d = request.getParameterValues("d");
        double a,b;
        PrintWriter out = response.getWriter();
        String ms = "";
        try{
            a = Double.parseDouble(dai_raw);
            b = Double.parseDouble(rong_raw);
            
            //cach 1
            //khong chon
//            if(cv==null && dt==null){
//                ms="Chua chon gi!";
//            }else if(cv!=null && dt==null){
//                ms = "Chu vi:" + (a+b)*2;
//            }else if(cv==null && dt!=null){
//                ms="Dien tich: " + (a*b);
//            }else{
//                ms = "Chu vi:" + (a+b)*2 + " Dien tich: " + (a*b);
//            }

            //cach 2
            if(d==null){
                ms="Chua chon gi!";
            }else if(d.length==2){
                ms = "Chu vi:" + (a+b)*2 + " Dien tich: " + (a*b);
            }else if(d[0].equals("0")) {
                ms = "Chu vi:" + (a+b)*2;
            }else{
                ms="Dien tich: " + (a*b);
            }
            out.println("<h1>" + ms + "</h1>");
        }catch(NumberFormatException e){
            System.out.println(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //super.doGet(req, resp); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        request.getRequestDispatcher("index.html").forward(request, response);
    }
    
}
