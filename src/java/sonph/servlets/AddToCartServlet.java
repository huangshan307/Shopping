/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonph.servlets;

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
import sonph.cartObj.Cart;
import sonph.shoes.ShoesDAO;
import sonph.shoes.ShoesDTO;
import sonph.shoesSizesDetails.ShoesSizesDetailDAO;
import sonph.shoesSizesDetails.ShoesSizesDetailDTO;
import sonph.sizes.SizesDAO;
import sonph.sizes.SizesDTO;

/**
 *
 * @author Huangshan
 */
@WebServlet(name = "AddToCartServlet", urlPatterns = {"/AddToCartServlet"})
public class AddToCartServlet extends HttpServlet {

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

        String shoesID = request.getParameter("shoesID");
        String sizesID = request.getParameter("sizesID");
        String lastSearchValue = request.getParameter("lastSearchValue");
        Cart cartObj;

        String url = SEARCH_PAGE;
        try {
            HttpSession session = request.getSession();
            if (session != null) {
                cartObj = (Cart) session.getAttribute("CART");
                
                if (cartObj == null) {
                    cartObj = new Cart();
                }
                
                ShoesDAO daoShoes = new ShoesDAO();
                ShoesDTO dtoShoes = daoShoes.getShoes(shoesID);
                if (dtoShoes != null) {
                    
                    ShoesSizesDetailDAO daoShoesSizes = new ShoesSizesDetailDAO();
                    ShoesSizesDetailDTO dtoShoesSizes = daoShoesSizes.getWithShoesIDSizesId(shoesID, sizesID);
                    
                    if (dtoShoesSizes != null) {
                        SizesDAO daoSizes = new SizesDAO();
                        SizesDTO dtoSizes = daoSizes.searchSizes(dtoShoesSizes);
                        if (dtoSizes != null) {
                            cartObj.addToCart(dtoShoes, dtoSizes);
                            url = "SearchServlet?searchValue=" + lastSearchValue;
                            
                            session.setAttribute("CART", cartObj);
                        }
                    }

                }
            }
        } catch (SQLException e) {
            log("AddToCartServlet_SQLException: " + e.getMessage());
        } catch (NamingException e) {
            log("AddToCartServlet_NamingException: " + e.getMessage());
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
