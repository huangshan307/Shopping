/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonph.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sonph.account.AccountDAO;
import sonph.account.AccountDTO;
import sonph.customers.CustomerDAO;
import sonph.customers.CustomerDTO;

/**
 *
 * @author Huangshan
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    private final String INVALID_PAGE = "invalid.html";
    private final String SEARCH_PAGE = "search.jsp";
    private final String LOGIN_PAGE = "login.html";

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
        PrintWriter out = response.getWriter();
        String url = INVALID_PAGE;
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        try {
            HttpSession session = request.getSession();

            AccountDTO account = (AccountDTO) session.getAttribute("USER");
            if (account != null) {
                url = SEARCH_PAGE;
            } else {
                if (username != null || password != null) {
                    AccountDAO dao = new AccountDAO();
                    AccountDTO user = dao.checkLogin(username, password);
                    if (user != null) {
                        CustomerDAO daoCust = new CustomerDAO();
                        CustomerDTO cus = daoCust.getCustomer(username);

                        user.setFirstName(cus.getFirstName());
                        user.setLastName(cus.getLastName());
                        user.setMiddleName(cus.getMiddleName());

                        url = SEARCH_PAGE;
                        //Create a session

                        session.setAttribute("USER", user);
                    }
                } else {
                    url = LOGIN_PAGE;
                }
            }

        } catch (SQLException e) {
            log("LoginServlet_SQLException: " + e.getMessage());
        } catch (NamingException e) {
            log("LoginServlet_NamingException: " + e.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);

            out.close();
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
        processRequest(request, response);
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
        processRequest(request, response);
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
