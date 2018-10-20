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
import sonphse.mobile.MobileDAO;
import sonphse.mobile.MobileUpdateError;
import sonphse.user.UserDTO;

/**
 *
 * @author Huangshan
 */
@WebServlet(name = "UpdateMobileServlet", urlPatterns = {"/UpdateMobileServlet"})
public class UpdateMobileServlet extends HttpServlet {

    private final String SEARCH_STAFF_PAGE = "SearchStaff.jsp";

    private final String ERROR_PAGE_NOT_FOUND = "404.html";

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

        String url = SEARCH_STAFF_PAGE;

        String type = request.getParameter("type");
        String lastSearchValue = request.getParameter("lastSearchValue");
        String mobileId = request.getParameter("mobileId");
        String description = request.getParameter("txtDescription");
        String priceStr = request.getParameter("txtPrice");
        String quanlityStr = request.getParameter("txtQuanlity");
        String sale = request.getParameter("notSale");

        try {
            HttpSession session = request.getSession(false);
            if (session != null) {
                UserDTO user = (UserDTO) session.getAttribute("USER_INFO");
                if (user != null) {
                    if (user.getRole() == 1 || user.getRole() == 2) {
                        
                        if (type != null 
                                || lastSearchValue != null 
                                || mobileId != null 
                                || description != null 
                                || priceStr != null 
                                || quanlityStr != null 
                                || sale != null) {
                            
                            MobileUpdateError errors = new MobileUpdateError();
                            boolean error = false;

                            if (description.trim().length() < 1 || description.trim().length() > 250) {
                                error = true;
                                errors.setDescriptionLength("Mobile's description is not empty and max is 250 chars");
                            }
                            if (!priceStr.trim().matches("^(\\d*\\.?\\d+)$")) {
                                error = true;
                                errors.setPriceNumberFormat("Please enter a float numer. Ex: 99.9, 10");
                            }
                            if (!quanlityStr.trim().matches("^(\\d+)$")) {
                                error = true;
                                errors.setQuanlityNumberFormat("Please enter an integer number. Ex: 100");
                            }

                            if (!error) {
                                boolean notSale = true;
                                if (sale != null) {
                                    notSale = !sale.equals("SALE");
                                }
                                float price = Float.parseFloat(priceStr);
                                int quanlity = Integer.parseInt(quanlityStr);

                                MobileDAO dao = new MobileDAO();
                                boolean result = dao.updateMobile(mobileId, description, price, quanlity, notSale);
                                if (result) {
                                    url = "OrderServlet"
                                            + "?btnAction=Search"
                                            + "&type=" + type
                                            + "&txtSearchValue=" + lastSearchValue;
                                }
                            } else {
                                request.setAttribute("ERRORS", errors);
                                url = "OrderServlet?btnAction=Search"
                                        + "&type=" + type
                                        + "&txtSearchValue=" + lastSearchValue;
                            }
                        }
                    } else {
                        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                        url = ERROR_PAGE_NOT_FOUND;
                    }
                } else {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    url = ERROR_PAGE_NOT_FOUND;
                }
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                url = ERROR_PAGE_NOT_FOUND;
            }
        } catch (NumberFormatException e) {
            log("UpdateMobileServlet_NumberFormatException_" + e.getMessage());
        } catch (SQLException e) {
            log("UpdateMobileServlet_SQLException_" + e.getMessage());
        } catch (NamingException e) {
            log("UpdateMobileServlet_NamingException_" + e.getMessage());
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
