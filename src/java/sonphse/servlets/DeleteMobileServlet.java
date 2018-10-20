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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sonphse.mobile.MobileDAO;
import sonphse.user.UserDTO;

/**
 *
 * @author Huangshan
 */
@WebServlet(name = "DeleteMobileServlet", urlPatterns = {"/DeleteMobileServlet"})
public class DeleteMobileServlet extends HttpServlet {

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
        
        String lastSearchValue = request.getParameter("lastSearchValue");
        String mobileId = request.getParameter("mobileId");
        String type = request.getParameter("type");
        
        try {
            HttpSession session = request.getSession(false);
            if (session != null) {
                UserDTO user = (UserDTO) session.getAttribute("USER_INFO");
                if (user != null) {
                    if (user.getRole() == 1 || user.getRole() == 2) {
                        MobileDAO dao = new MobileDAO();
                        boolean result = dao.deleteMobile(mobileId);
                        if (result) {
                            response.sendRedirect("OrderServlet?type=" + type + "&txtSearchValue=" + lastSearchValue + "&btnAction=Search");
                        } else {
                            response.sendRedirect("OrderServlet?btnAction=Search");
                        }
                        
                    } else {
                        response.sendError(HttpServletResponse.SC_NOT_FOUND);
                        // send error 404 if user is not Admin or Staff
                    } // end if user getRole
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                } // end if user
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND); // send error 404 if user didn't login
            } // end if session
        } catch(SQLException e) {
            log("DeleteMobileServlet_SQLException_" + e.getMessage());
        } catch(NamingException e) {
            log("DeleteMobileServlet_NamingException_" + e.getMessage());
        } finally {
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
