/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.nio.file.Files;
import java.nio.file.Paths;
import model.Product;

/**
 *
 * @author NguyenHuuTuan
 */
@MultipartConfig()
@WebServlet(name="AdminUpdateProductServlet", urlPatterns={"/adminUpdate"})
public class AdminUpdateProductServlet extends HttpServlet {
   
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
            out.println("<title>Servlet AdminUpdateProductServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminUpdateProductServlet at " + request.getContextPath () + "</h1>");
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
    
    private final String UPLOAD_DIRECTORY = "images";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String id = request.getParameter("id");
        ProductDAO d = new ProductDAO();
        Product p = d.getProductById(id);
        request.setAttribute("product", p);
        request.getRequestDispatcher("AdminUpdateProduct.jsp").forward(request, response);
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
        String id = request.getParameter("product_id");
        String name = request.getParameter("product_name");
        String type = request.getParameter("product_type");
        String describe = request.getParameter("product_description");
        String price_raw = request.getParameter("price");
        String quantity_raw = request.getParameter("available_quantity");
        
        Part part = request.getPart("file");
        String realPath = request.getServletContext().getRealPath("/images");
        String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
        if(!Files.exists(Paths.get(realPath))){
            Files.createDirectory(Paths.get(realPath));
        }

        String path = "images/"+fileName;
        
        ProductDAO d = new ProductDAO();
        try{
            double price = Double.parseDouble(price_raw);
            int quantity = Integer.parseInt(quantity_raw);
            Product p = d.getProductById(id);
            
            if(!p.getImage().equals(path) && !path.equals("images/")){
                part.write(realPath+"/"+fileName);
                
                Product pNew = new Product(id, name, type, describe, path, price, quantity);
                d.updateProduct(pNew);
                response.sendRedirect("adminlist");
            }else{
                Product pNew = new Product(id, name, type, describe, p.getImage(), price, quantity);
                d.updateProduct(pNew);
                response.sendRedirect("adminlist");
            }
        }catch(NumberFormatException e){
            
        }
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
