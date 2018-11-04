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
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sonph.account.AccountDTO;
import sonph.cartObj.Cart;
import sonph.cartObj.Item;
import sonph.customers.CustomerDAO;
import sonph.customers.CustomerDTO;
import sonph.orderDetails.OrderDetailDAO;
import sonph.orders.OrderDAO;
import sonph.shoesSizesDetails.ShoesSizesDetailDAO;
import sonph.shoesSizesDetails.ShoesSizesDetailDTO;

/**
 *
 * @author Huangshan
 */
@WebServlet(name = "OrderProductServlet", urlPatterns = {"/OrderProductServlet"})
public class OrderProductServlet extends HttpServlet {

    private final String VIEW_CART_PAGE = "viewCart.jsp";
    private final String LOGIN_HTML = "login.html";
    private final String SEARCH_PAGE = "search.jsp";

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
        String button = request.getParameter("btnAction");
        String url = VIEW_CART_PAGE;
        try {
            if (button != null) {
                if (button.equals("OK")) {

                    HttpSession session = request.getSession(false);
                    if (session != null) {
                        AccountDTO user = (AccountDTO) session.getAttribute("USER");
                        if (user != null) {
                            Cart cartObj = (Cart) session.getAttribute("CART");

                            if (cartObj != null) {

                                OrderDAO daoOrder = new OrderDAO();
                                String code = daoOrder.generateCode();
                                // get Customer id
                                CustomerDAO daoCust = new CustomerDAO();
                                CustomerDTO cust = daoCust.getCustomer(user.getUsername());
                                if (cust != null) {
                                    // insert cart to tbl_order
                                    boolean result = daoOrder.insertOrder(code, cust.getCustID(), cartObj.getTotalPrice());
                                    if (result) {
                                        
                                        int result2 = 0;

                                        Map<String, Map<String, Item>> list = cartObj.getList();
                                        if (!list.isEmpty()) {
                                            for (String keyList : list.keySet()) {
                                                Map<String, Item> listItem = list.get(keyList);
                                                
                                                result2 = this.insertItem(listItem, code);
                                                
                                                
                                            } // end for loop list.keySet
                                        } // end if list empty

                                        if (result2 > 0) {
                                            url = SEARCH_PAGE;
                                            request.setAttribute("ORDER_MSG", "Your order was ordered, thanks for use my services.");
                                            // clear cart when was orderd successfull
                                            session.removeAttribute("CART");
                                            session.removeAttribute("ErrorQuantityItem");
                                        } // end if result2 > 0
                                        
                                        
                                    } // end if result
                                } // end if cust null
                            } // end if cart obj

                        } else {
                            url = LOGIN_HTML;
                            // redirect to login page if user haven't login already
                            
                        } // end if user null
                        
                    } // end if session null

                } // end if button = OK
            } // end if button null
        } catch (NoSuchAlgorithmException ex) {
            log("OrderProductServle_NoSuchAlgorithmException: " + ex.getMessage());
        } catch (NamingException ex) {
            log("OrderProductServle_NamingException: " + ex.getMessage());
        } catch (SQLException ex) {
            log("OrderProductServle_SQLException: " + ex.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            out.close();
        }
    }
    
    /**
     * 
     * @param listItem
     * @param code
     * @return
     * @throws NamingException
     * @throws SQLException 
     */
    private int insertItem(Map<String, Item> listItem, String code) throws NamingException, SQLException {
        int result = 0;
        if (listItem != null) {
            if (!listItem.isEmpty()) {
                
                for (String keyListItem : listItem.keySet()) {
                    Item item = listItem.get(keyListItem);
                    if (item != null) {
                        OrderDetailDAO daoOrderDetail = new OrderDetailDAO();
                        ShoesSizesDetailDAO daoShoesSizes = new ShoesSizesDetailDAO();
                        
                        boolean rs = daoOrderDetail.insertOrderDetail(item.getShoes().getShoesID(), item.getSizes().getId(), 
                                    item.getQuantity(), 
                                    item.getSizes().getPrice(), 
                                    item.getPrice(), 
                                    code);
                        
                        ShoesSizesDetailDTO dtoShoesSizes = daoShoesSizes.getWithShoesIDSizesId(item.getShoes().getShoesID(), item.getSizes().getId());
                        
                        int quantity = dtoShoesSizes.getQuantity() - item.getQuantity();
                        
                        boolean rs2 = daoShoesSizes.updateWithShoesIDSizesID(item.getShoes().getShoesID(), item.getSizes().getId(), 
                                    item.getSizes().getPrice(), quantity);
                        
                        if (rs && rs2) {
                            result++;
                        } // end if rs
                        
                    } // end if item null
                } // end for loop listItem.keySet
                
            } // end if listItem Empty
        } // end if listItem null
        return result;
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
