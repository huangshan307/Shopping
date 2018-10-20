/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonphse.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sonphse.mobile.MobileDAO;
import sonphse.mobile.MobileDTO;
import sonphse.mobile.MobileSearchUserError;

/**
 *
 * @author Huangshan
 */
@WebServlet(name = "SearchUserServlet", urlPatterns = {"/SearchUserServlet"})
public class SearchUserServlet extends HttpServlet {

    private final String SEARCH_USER_PAGE = "SearchUser.jsp";

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

        String url = SEARCH_USER_PAGE;

        String strMin = request.getParameter("txtMin");
        String strMax = request.getParameter("txtMax");

        MobileSearchUserError errors = new MobileSearchUserError();
        boolean error = false;

        try {
            if (strMin != null || strMax != null) {
                if (strMin.trim().length() > 0 || strMax.trim().length() > 0) {
                    if (!strMin.trim().matches("^(\\d*\\.?\\d+)$")) {
                        error = true;
                        errors.setMinNumberFormat("Min must be a float number");
                    }
                    if (!strMax.trim().matches("^(\\d*\\.?\\d+)$")) {
                        error = true;
                        errors.setMaxNumberFormat("Max must be a float number");
                    }
                    if (error) {
                        request.setAttribute("ERRORS", errors);
                    } else {
                        float min = Float.parseFloat(strMin);
                        float max = Float.parseFloat(strMax);
                        MobileDAO dao = new MobileDAO();
                        List<MobileDTO> list = dao.searchWithPrice(min, max);
                        request.setAttribute("LIST_SEARCH", list);
                    }
                }
            }
        } catch (NumberFormatException e) {
            log("SearchUser_NumberFormatException_" + e.getMessage());
        } catch (SQLException e) {
            log("SearchUser_SQLException_" + e.getMessage());
        } catch (NamingException e) {
            log("SearchUser_NamingException_" + e.getMessage());
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
