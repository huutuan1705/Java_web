/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author NguyenHuuTuan
 */
public class DoiTienNewServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet DoiTienNewServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DoiTienNewServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        //get from form
//        String money_raw = request.getParameter("money");
//        String change = request.getParameter("change");
//        
//        //get from web.xml
//        String usds = getServletContext().getInitParameter("usd");
//        String yens = getServletContext().getInitParameter("yen");
//        
//        double money, usd, yen;
//        PrintWriter out = response.getWriter();
//        try{
//            money = Double.parseDouble(money_raw);
//            usd = Double.parseDouble(usds);
//            yen = Double.parseDouble(yens);
//            double money_change;
//            if(change.equals("0")){
//                money_change = money/usd;
//            }else{
//                money_change = money/yen;
//            }
//            //out.println("Tien: "+money_change);
//            request.setAttribute("money", money_change);
//            request.getRequestDispatcher("DoiTien.jsp").forward(request, response);
//        }catch(NumberFormatException e){
//            request.setAttribute("error", "Nhap so");
//            request.getRequestDispatcher("DoiTien.jsp").forward(request, response);
//        }
        request.getRequestDispatcher("DoiTien.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //get from form
        String money_raw = request.getParameter("money");
        //String change = request.getParameter("change");
        String[] change = request.getParameterValues("change");
        //get from web.xml
        String usds = getServletContext().getInitParameter("usd");
        String yens = getServletContext().getInitParameter("yen");

        double money, usd, yen;
        //PrintWriter out = response.getWriter();
        try {
            money = Double.parseDouble(money_raw);
            usd = Double.parseDouble(usds);
            yen = Double.parseDouble(yens);
            //double money_change;
            String kqua = "";
//            if (change.equals("0")) {
//                money_change = money / usd;
//            } else {
//                money_change = money / yen;
//            }

//            if (change == null) {
//                kqua = "Ban chua chon loai tien doi";
//            } else if (change.length == 1) {
//                if (change[0].equals("0")) {
//                    kqua = "Doi sang USD: " + (money / usd);
//                } else {
//                    kqua = "Doi sang yen: " + (money / yen);
//                }
//            }else{
//                kqua = "Doi sang USD: " + (money / usd) + " Doi sang yen: " +(money / yen);
//            }

            if (change.equals("0")) {
                kqua = "Doi sang USD: " + (money / usd);
            } else {
                kqua = "Doi sang yen: " + (money / yen);
            }
                //out.println("Tien: "+money_change);
                request.setAttribute("kqua", kqua);
                request.getRequestDispatcher("DoiTien.jsp").forward(request, response);
            }catch (NumberFormatException e) {
            request.setAttribute("error", "Nhap so");
            request.getRequestDispatcher("DoiTien.jsp").forward(request, response);
        }
        }

        /**
         * Returns a short description of the servlet.
         *
         * @return a String containing servlet description
         */
        @Override
        public String getServletInfo
        
            () {
        return "Short description";
        }// </editor-fold>

    }
