/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonphse.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sonphse.cart.Cart;
import sonphse.cart.Item;
import sonphse.mobile.MobileDAO;
import sonphse.order.OrderDAO;
import sonphse.order.OrderDTO;
import sonphse.order.OrderInsertError;
import sonphse.orderdetail.OrderDetailDAO;
import sonphse.user.UserDTO;

/**
 *
 * @author Huangshan
 */
@WebServlet(name = "OrderProductsServlet", urlPatterns = {"/OrderProductsServlet"})
public class OrderProductsServlet extends HttpServlet {
    
    private final String LOGIN_PAGE = "login.jsp";
    private final String SEARCH_USER_PAGE = "SearchUser.jsp";
    private final String VIEW_CART = "viewCart.jsp";

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
        String url = LOGIN_PAGE;
        OrderInsertError errors = new OrderInsertError();
        boolean error = false;
        try {
            HttpSession session = request.getSession(false);
            if (session != null) {
                UserDTO user = (UserDTO) session.getAttribute("USER_INFO");
                if (user != null) {
                    Cart cart = (Cart) session.getAttribute("CART");
                    if (cart != null) {
                        cart.setCustomID(user.getUserId());
                        //get current date time;
                        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        LocalDateTime now = LocalDateTime.now();
                        // insert info to OrderDTO
                        OrderDTO bill = new OrderDTO();
                        bill.setUserId(cart.getCustomID());
                        bill.setOrderDate(Date.valueOf(dateFormatter.format(now)));
                        
                        String errMsg = "";
                        for (Map.Entry<String, Item> e : cart.getItems().entrySet()) {
                            MobileDAO dao = new MobileDAO();
                            int quantities = dao.getQuantity(e.getKey());
                            if (quantities - e.getValue().getQuantity() < 0) {
                                error = true;
                                errMsg += e.getValue().getMobile().getMobileName() + ", ";
                            }
                        }
                        if (error) {
                            errors.setQuantityError("Sorry, " + errMsg +" out of items, please buy this later");
                            request.setAttribute("ERRORS", errors);
                            url = VIEW_CART;
                        } else {
                            OrderDAO dao = new OrderDAO();
                            dao.inserOrderAndDetail(cart.getItems(), bill);
                            session.removeAttribute("CART"); // destroy cart
                            request.setAttribute("OrderSuccess", "Thank you for your order, we will deliver your product soon");
                            url = SEARCH_USER_PAGE;
                        }
                    } else {
                        url = SEARCH_USER_PAGE;
                    } // end if cart
                } else {
                    request.setAttribute("ORDER_LOGIN", "Please login to order your products");
                } // end if user
            } // end if session
        } catch (SQLException e) {
            log("OrderProductServlet_SQLException_" + e.getMessage());
        } catch (NamingException e) {
            log("OrderProductServlet_NamingException_" + e.getMessage());
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
