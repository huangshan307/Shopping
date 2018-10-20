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
import javax.servlet.http.HttpSession;
import sonphse.mobile.MobileDAO;
import sonphse.mobile.MobileDTO;
import sonphse.user.UserDTO;

/**
 *
 * @author Huangshan
 */
@WebServlet(name = "SearchStaffServlet", urlPatterns = {"/SearchStaffServlet"})
public class SearchStaffServlet extends HttpServlet {

    private final String SEARCH_STAFF_PAGE = "SearchStaff.jsp";
    private final String ORDER_SERVLET = "OrderServlet";
    private final String LOGIN_PAGE = "login.jsp";

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

        String type = request.getParameter("type");
        String searchValue = request.getParameter("txtSearchValue");
        String url = LOGIN_PAGE;

        try {
            HttpSession session = request.getSession(false);
            if (session != null) {
                UserDTO user = (UserDTO) session.getAttribute("USER_INFO");
                if (user != null) {
                    MobileDAO dao = new MobileDAO();
                    List<MobileDTO> list;

                    if (searchValue != null && searchValue.trim().length() > 0) {
                        if (type != null && type.trim().length() > 0) {
                            if (type.equals("Name")) {
                                list = dao.searchByName(searchValue);
                            } else {
                                list = dao.searchById(searchValue);
                            }
                            if (list != null) {
                                request.setAttribute("LIST_SEARCH", list);
                            } //end if list
                        } // end if type
                    }

                    url = SEARCH_STAFF_PAGE;
                } else {
                    url = LOGIN_PAGE;
                }
            } else {
                url = LOGIN_PAGE;
            }

        } catch (SQLException e) {
            log("SearchStaffServlet_SQLException_" + e.getMessage());
        } catch (NamingException e) {
            log("SearchStaffServlet_NamingException_" + e.getMessage());
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
