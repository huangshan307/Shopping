/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonphse.servlets;

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
import sonphse.user.LoginError;
import sonphse.user.UserDAO;
import sonphse.user.UserDTO;

/**
 *
 * @author Huangshan
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    private final String LOGIN_JSP_PAGE = "login.jsp";
    private final String SEARCH_USER_PAGE = "SearchUser.jsp";
    private final String SEARCH_STAFF_PAGE = "SearchStaff.jsp";

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

        String url = LOGIN_JSP_PAGE;
        String username = request.getParameter("txtUsername");
        String passwordStr = request.getParameter("txtPassword");
        int password;

        LoginError errors = new LoginError();

        try {
            if (username != null || passwordStr != null) {
                password = Integer.valueOf(passwordStr.trim());

                UserDTO dto = UserDAO.checkLogin(username, password);

                if (dto != null) {
                    if (dto.getRole() == 0) {
                        url = SEARCH_USER_PAGE;
                    } else if (dto.getRole() == 1 || dto.getRole() == 2) {
                        url = SEARCH_STAFF_PAGE;
                    } // end if dto getRole

                    HttpSession session = request.getSession();
                    session.setAttribute("USER_INFO", dto);
                } else {
                    errors.setUsernameOrPassword("Username or password is incorrect!");
                    request.setAttribute("ERRORS", errors);
                } // end if dto
            }

        } catch (SQLException e) {
            log("LoginServlet_SQLException_" + e.getMessage());
        } catch (NamingException e) {
            log("LoginServlet_NamingException_" + e.getMessage());
        } catch (NumberFormatException e) {
            log("LoginServlet_NumberFormatException_" + e.getMessage());
            errors.setPasswordNumberFormat("Please enter an integer number for your password!");
            request.setAttribute("ERRORS", errors);
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
