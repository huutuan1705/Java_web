/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import model.Product;

/**
 *
 * @author huutuan
 */
@MultipartConfig()
@WebServlet(name="UpdateProductServlet", urlPatterns={"/updateProduct"})
public class UpdateProductServlet extends HttpServlet {
   
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
            out.println("<title>Servlet UpdateProductServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateProductServlet at " + request.getContextPath () + "</h1>");
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
        String id_raw = request.getParameter("id");
        int id;
        DAO d = new DAO();
        try{
            id = Integer.parseInt(id_raw);
            Product p = d.getProductById(id);
            request.setAttribute("product", p);
            request.getRequestDispatcher("updateProduct.jsp").forward(request, response);
        }catch(NumberFormatException e){
            
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
        request.setCharacterEncoding("UTF-8");
        String id_raw = request.getParameter("id");
        String name = request.getParameter("name");
        
        Part image = request.getPart("image");
        String realPath = image.getSubmittedFileName();
        String path = "images/"+realPath;
        
        String description = request.getParameter("description");
        String price_raw = request.getParameter("price");
        String quantity_raw = request.getParameter("quantity");
        int id, price, quantity;
        DAO d = new DAO();
        try{
            id = Integer.parseInt(id_raw);
            price = Integer.parseInt(price_raw);
            quantity = Integer.parseInt(quantity_raw);
            Product p = d.getProductById(id);
            if(!p.getImage().equals(path) && !path.equals("images/")){
                String imageName = request.getServletContext().getRealPath(realPath);
                image.write(imageName);
                Product pNew = new Product(id, name, path, price, description, quantity);
                d.updateProduct(pNew);                
            }else{
                Product pNew = new Product(id, name, p.getImage() ,price , description, quantity);
                d.updateProduct(pNew);               
            }
            
        }catch(NumberFormatException e){
            
        }
        response.sendRedirect("home");
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
