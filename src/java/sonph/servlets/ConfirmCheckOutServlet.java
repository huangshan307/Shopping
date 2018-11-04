/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonph.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sonph.cartObj.Cart;
import sonph.cartObj.Item;
import sonph.shoesSizesDetails.ShoesSizesDetailDAO;
import sonph.shoesSizesDetails.ShoesSizesDetailDTO;

/**
 *
 * @author Huangshan
 */
@WebServlet(name = "ConfirmCheckOutServlet", urlPatterns = {"/ConfirmCheckOutServlet"})
public class ConfirmCheckOutServlet extends HttpServlet {

    private final String VIEW_CART_PAGE = "viewCart.jsp";
    private final String CONFIRM_PAGE = "confirm.jsp";
    private final String CHECK_OUT_PAGE = "checkOut.jsp";
    private final String REJECT_PAGE = "reject.jsp";

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
        String url = VIEW_CART_PAGE;
        String button = request.getParameter("btnAction");
        try {
            if (button != null) {
                if (button.equals("Back")) {
                    url = VIEW_CART_PAGE;
                } else {
                    HttpSession session = request.getSession(false);
                    if (session != null) {
                        Cart cartObj = (Cart) session.getAttribute("CART");
                        if (cartObj != null) {
                            
                            switch (button) {
                                case "Confirm":
                                    Map<Item, Integer> errorQuantityItem = (Map<Item, Integer>) session.getAttribute("ErrorQuantityItem");
                                    if (errorQuantityItem != null) {
                                        session.removeAttribute("ErrorQuantityItem");
                                    }
                                    errorQuantityItem = new LinkedHashMap<>();
                                    
                                    for (String shoesID : cartObj.getList().keySet()) {
                                        Map<String, Item> listItem = cartObj.getList().get(shoesID);
                                        for (String sizesID : listItem.keySet()) {
                                            Item item = listItem.get(sizesID);
                                            // get quantity from database with shoesId and sizesID
                                            ShoesSizesDetailDAO dao = new ShoesSizesDetailDAO();
                                            ShoesSizesDetailDTO dto = dao.getWithShoesIDSizesId(shoesID, sizesID);
                                            
                                            if (dto.getQuantity() < item.getQuantity()) {
                                                errorQuantityItem.put(item, dto.getQuantity());
                                            }
                                        }
                                    }
                                    
                                    if (!errorQuantityItem.isEmpty()) {
                                        url = CHECK_OUT_PAGE;
                                        session.setAttribute("ErrorQuantityItem", errorQuantityItem);
                                    } else {
                                        url = CONFIRM_PAGE;
                                    }
                                    
                                    break;
                                case "Cancel":
                                    url = CHECK_OUT_PAGE;
                                    break;
                                case "OK":
                                    url = REJECT_PAGE;
                                    break;
                                default:
                                    break;
                            }
                            
                        }
                    }
                }
            }
        } catch (NamingException ex) {
            log("ConfirmCheckOutServlet_NamingException: " + ex.getMessage());
        } catch (SQLException ex) {
            log("ConfirmCheckOutServlet_SQLException: " + ex.getMessage());
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
