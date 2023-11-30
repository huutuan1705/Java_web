/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.List;
import model.Category;
import model.Product;

/**
 *
 * @author NguyenHuuTuan
 */
public class ListServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ListServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ListServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        DAO d = new DAO();
        List<Category> list = d.getAll();
        request.setAttribute("categories", list);
        String cid_raw = request.getParameter("key");
        String key = request.getParameter("key2");
        String from_raw = request.getParameter("fromdate");
        String to_raw = request.getParameter("todate");
        String fromPrice_raw = request.getParameter("fromprice");
        String toPrice_raw = request.getParameter("toprice");
        Double price1, price2;
        Date from, to;
        int cid;
        try{
            cid =(cid_raw==null)?0:Integer.parseInt(cid_raw);
            price1 = ((fromPrice_raw==null || fromPrice_raw.equals("")))?null:
                    Double.parseDouble(fromPrice_raw);
            price2 = ((toPrice_raw==null || toPrice_raw.equals("")))?null:
                    Double.parseDouble(toPrice_raw);
            from = ((from_raw==null || from_raw.equals("")))?null:
                    Date.valueOf(from_raw);
            to = ((to_raw==null || to_raw.equals("")))?null:
                    Date.valueOf(to_raw);
            
            List<Product> products = d.search(key, from, to, price1, price2, cid);
            request.setAttribute("products", products);
            request.getRequestDispatcher("list.jsp").forward(request, response);
        }catch(NumberFormatException e){
            System.out.println(e);
        }
        
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
