/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package ngay14_3;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author NguyenHuuTuan
 */
@WebServlet(name = "MySQLDemo", urlPatterns = {"/books"})
public class MySQLDemo extends HttpServlet {

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
            out.println("<title>Servlet MySQLDemo</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MySQLDemo at " + request.getContextPath() + "</h1>");
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
        response.setContentType("text/html");
        PrintWriter printer = response.getWriter();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultset = null;
        String keyWord = request.getParameter("key");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_demo", "root", "123456789");
            statement = connection.createStatement();
            resultset = statement.executeQuery("select * from book left outer join category\n"
                    + "on book.category = category.id");
            if (keyWord != null) {
                while (resultset.next()) {
                    int bookcode = resultset.getInt("bookcode");
                    String title = resultset.getString("title");
                    String author = resultset.getString("author");
                    String category = resultset.getString("category");
                    int approved = resultset.getInt("approved");
                    if (title.contains(keyWord)) {
                        printer.println("<br>" + bookcode + ", " + title + ", " + author
                                + ", " + category + ", " + approved);
                    }
                }
            } else {
                while (resultset.next()) {
                    int bookcode = resultset.getInt("bookcode");
                    String title = resultset.getString("title");
                    String author = resultset.getString("author");
                    String category = resultset.getString("category");
                    int approved = resultset.getInt("approved");
                    printer.println("<br>" + bookcode + ", " + title + ", " + author
                            + ", " + category + ", " + approved);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("AddBook.jsp").forward(request, response);
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
        response.setContentType("text/html");
        PrintWriter printer = response.getWriter();
        Connection connection = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_demo", "root", "123456789");
            PreparedStatement st = connection.prepareStatement("INSERT INTO book VALUES (?,?,?,?,?)");
            st.setInt(1, Integer.valueOf(request.getParameter("bookcode")));
            st.setString(2, request.getParameter("title"));
            st.setString(3, request.getParameter("author"));
            st.setString(4, request.getParameter("category"));
            st.setInt(5, request.getParameter("approved")!="null"?1:0);
            st.executeUpdate();
            st.close();
            connection.close();
            response.sendRedirect("books");
        }catch(Exception e){
            System.out.println(e);
        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
