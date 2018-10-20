/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonphse.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Huangshan
 */
@WebServlet(name = "OrderServlet", urlPatterns = {"/OrderServlet"})
public class OrderServlet extends HttpServlet {

    private final String LOGIN_PAGE = "login.jsp";
    private final String LOGIN_SERVLET = "LoginServlet";
    private final String LOGOUT_SERVLET = "LogoutServlet";
    private final String START_UP_SERVLET = "StartupServlet";
    private final String SEARCH_SERVLET = "SearchServlet";
    private final String DELETE_MOBILE_SERVLET = "DeleteMobileServlet";
    private final String UPDATE_MOBILE_SERVLET = "UpdateMobileServlet";
    private final String INSERT_MOBILE_SERVLET = "InsertMobileServlet";
    private final String INSERT_MOBILE_PAGE = "insertMobile.jsp";
    private final String ADD_TO_CART_SERVLET = "AddToCartServlet";
    private final String VIEW_CART = "viewCart.jsp";
    private final String REMOVE_FROM_CART_SERVLET = "RemoveFromCartServlet";
    private final String ORDER_PRODUCTS_SERVLET = "OrderProductsServlet";

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

        String button = request.getParameter("btnAction");
        String url = LOGIN_PAGE;

        try {
            if (null == button) {
                url = START_UP_SERVLET;
            } else {
                switch (button) {
                    case "Login":
                        url = LOGIN_SERVLET;
                        break;
                    case "Logout":
                        url = LOGOUT_SERVLET;
                        break;
                    case "Search":
                        url = SEARCH_SERVLET;
                        break;
                    case "Delete":
                        url = DELETE_MOBILE_SERVLET;
                        break;
                    case "Update":
                        url = UPDATE_MOBILE_SERVLET;
                        break;
                    case "Insert":
                        url = INSERT_MOBILE_SERVLET;
                        break;
                    case "InsertPage":
                        url = INSERT_MOBILE_PAGE;
                        break;
                    case "AddToCart":
                        url = ADD_TO_CART_SERVLET;
                        break;
                    case "viewCart":
                        url = VIEW_CART;
                        break;
                    case "Remove":
                        url = REMOVE_FROM_CART_SERVLET;
                        break;
                    case "OrderNow":
                        url = ORDER_PRODUCTS_SERVLET;
                        break;
                    default:
                        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                        url = ERROR_PAGE_NOT_FOUND;
                        break;
                }
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
