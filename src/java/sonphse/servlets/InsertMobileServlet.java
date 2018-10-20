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
import sonphse.mobile.MobileInsertError;
import sonphse.user.UserDTO;

/**
 *
 * @author Huangshan
 */
@WebServlet(name = "InsertMobileServlet", urlPatterns = {"/InsertMobileServlet"})
public class InsertMobileServlet extends HttpServlet {

    private final String INSERT_MOBILE_PAGE = "insertMobile.jsp";

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

        String url = INSERT_MOBILE_PAGE;

        String mobileId = request.getParameter("txtMobileId");
        String description = request.getParameter("txtDesctiption");
        String strPrice = request.getParameter("txtPrice");
        String mobileName = request.getParameter("txtMobileName");
        String strYear = request.getParameter("txtYear");
        String strQuanlity = request.getParameter("txtQuanlity");
        String strNotSale = request.getParameter("txtNotSale");

        MobileInsertError errors = new MobileInsertError();
        boolean error = false;

        try {
            HttpSession session = request.getSession(false);
            if (session != null) {
                UserDTO user = (UserDTO) session.getAttribute("USER_INFO");
                if (user != null) {
                    if (user.getRole() == 1 || user.getRole() == 2) {
                        if (mobileId != null
                                || description != null
                                || strPrice != null
                                || mobileName != null
                                || strYear != null
                                || strQuanlity != null
                                || strNotSale != null) {
                            
                            if (mobileId.trim().length() > 10 || mobileId.trim().length() < 1) {
                                error = true;
                                errors.setMobileIdLength("Mobile id is not empty and max chars is 10");
                            }
                            if (description.trim().length() > 250 || description.trim().length() < 1) {
                                error = true;
                                errors.setDescriptionLength("Description is not null and max chars is 250");
                            }
                            if (mobileName.trim().length() > 20 || mobileName.trim().length() < 1) {
                                error = true;
                                errors.setMobileNameLength("Mobile name is not empty and max chars is 20");
                            }
                            if (!strYear.trim().matches("^(\\d{4})$")) {
                                error = true;
                                errors.setYearNumberFotmat("Year format is incorrect. Ex: 2018");
                            }
                            if (!strQuanlity.trim().matches("^(\\d+)$")) {
                                error = true;
                                errors.setQuantityNumberFormat("Please enter an integer number for this field");
                            }
                            if (!strPrice.trim().matches("^(\\d*\\.?\\d+)$")) {
                                error = true;
                                errors.setPriceNumberFormat("Please enter some number for this field");
                            }

                            if (error) {
                                request.setAttribute("ERRORS", errors);
                                url = INSERT_MOBILE_PAGE;
                            } else {
                                float price = Float.parseFloat(strPrice);
                                int year = Integer.parseInt(strYear);
                                int quanlity = Integer.parseInt(strQuanlity);
                                boolean notSale = false;
                                if (strNotSale != null) {
                                    notSale = strNotSale.trim().equals("NOTSALE");
                                }
                                MobileDAO dao = new MobileDAO();
                                boolean result = dao.insertMobile(mobileId, description, price, mobileName, year, quanlity, notSale);
                                if (result) {
                                    String messageSuccess = "Insert new mobile successfull";
                                    request.setAttribute("MSG_INSERT_SUCCESS", messageSuccess);
                                }
                                url = INSERT_MOBILE_PAGE;
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
            log("InsertMobileServlet_NumberFormatException_" + e.getMessage());
        } catch (NamingException e) {
            log("InsertMobileServlet_NamingException_" + e.getMessage());
        } catch (SQLException e) {
            log("InsertMobileServlet_SQLException_" + e.getMessage());
            if (e.getMessage().contains("duplicate")) {
                errors.setMobileIdExist("This mobile id was exist, please choose an other one");
                request.setAttribute("ERRORS", errors);
            }
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
