/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.AccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Person;

/**
 *
 * @author NguyenHuuTuan
 */
@WebServlet(name="RegisterServlet", urlPatterns={"/register"})
public class RegisterServlet extends HttpServlet {
   
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
            out.println("<title>Servlet RegisterServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegisterServlet at " + request.getContextPath () + "</h1>");
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
        request.getRequestDispatcher("register.jsp").forward(request, response);
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
        String name = request.getParameter("name");
        String user = request.getParameter("user");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String pass = request.getParameter("pass");
        String conPass = request.getParameter("conPass");
        String gender_raw = request.getParameter("gender");
        String gender = "";
        if(gender_raw.equals("male")){
            gender += "male";
        }else{
            gender += "female";
        }
        AccountDAO d = new AccountDAO();
        Person p = d.checkRegisterAccount(user);
        if(p!=null){
            request.setAttribute("errorUsernameExist", "Username does exist!!");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }else{
            if(!pass.equals(conPass)){
                request.setAttribute("errorConfirmPassword", "Confirm password and password are not same!!");
                request.getRequestDispatcher("register.jsp").forward(request, response);
            }else{
                Person pNew = new Person(name, user, pass, email, phone, gender, 2);
                d.insertAccount(pNew);
                response.sendRedirect("login");
            }   
        }
        
//        if(!pass.equals(conPass)){
//            request.setAttribute("errorConfirmPassword", "Confirm password and password are not same!!");
//            request.getRequestDispatcher("register.jsp").forward(request, response);
//        }else{
//            if(p!=null){
//                request.setAttribute("errorUsernameExist", "Username does exist!!");
//                request.getRequestDispatcher("register.jsp").forward(request, response);
//            }else{
//                Person pNew = new Person(name, user, pass, email, phone, gender, 2);
//                d.insertAccount(pNew);
//                response.sendRedirect("login");
//            }           
//        }
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
