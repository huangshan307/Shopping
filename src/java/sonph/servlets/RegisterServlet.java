/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonph.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sonph.account.AccountDAO;
import sonph.account.AccountInsertError;

/**
 *
 * @author Huangshan
 */
@WebServlet(name = "RegisterServlet", urlPatterns = {"/RegisterServlet"})
public class RegisterServlet extends HttpServlet {

    private final String REGISTER_PAGE = "register.jsp";
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
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String lastName = request.getParameter("lastName");
        String middleName = request.getParameter("middleName");
        String firstName = request.getParameter("firstName");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        
        AccountInsertError errors = new AccountInsertError();
        
        String url = REGISTER_PAGE;
        
        try {
            if (username != null || password != null || lastName != null || middleName != null || firstName != null || address != null || phone != null) {
                if (username.trim().length() < 1 || username.trim().length() > 20) {
                    errors.setUsername("Username is not empty and >= 20");
                    errors.setError(true);
                }
                if (password.trim().length() < 1 || password.trim().length() > 30) {
                    errors.setPassword("Password is not empty and >= 30");
                    errors.setError(true);
                }
                if (lastName.trim().length() < 1 || lastName.trim().length() > 15) {
                    errors.setLastName("Last name is not empty and >= 15");
                    errors.setError(true);
                }
                if (middleName.trim().length() < 1 || middleName.trim().length() > 30) {
                    errors.setMiddleName("Middle name is not empty and >= 30");
                    errors.setError(true);
                }
                if (firstName.trim().length() < 1 || firstName.trim().length() > 15) {
                    errors.setFirstName("First name is not empty and >= 15");
                    errors.setError(true);
                }
                if (address.trim().length() < 1 || address.trim().length() > 250) {
                    errors.setAddress("Address is not empty and >= 250");
                    errors.setError(true);
                }
                if (!phone.trim().matches("^[0-9]{10,11}$")) {
                    errors.setPhone("Phone is incorrect!");
                    errors.setError(true);
                    log("RegisterServlet_NumberFormat: " + phone);
                }
                
                if (errors.isError()) {
                    request.setAttribute("ERRORS", errors);
                } else {
                    AccountDAO dao = new AccountDAO();
                    boolean result = dao.insertAccount(username, password, lastName, middleName, firstName, address, phone);
                    if (result) {
                        url = LOGIN_PAGE;
                    }
                }
            }
        } catch (NoSuchAlgorithmException ex) {
            log("RegisterServlet_NoSuchAlgorithmException: " + ex.getMessage());
        } catch (NamingException ex) {
            log("RegisterServlet_NamingException: " + ex.getMessage());
        } catch (SQLException ex) {
            if (ex.getMessage().contains("duplicate")) {
                errors.setUsernameExist("This username was already, please choose another one.");
                errors.setError(true);
                request.setAttribute("ERRORS", errors);
                url = REGISTER_PAGE;
            }
            log("RegisterServlet_SQLException: " + ex.getMessage());
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
