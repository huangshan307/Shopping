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
@WebServlet(name = "SearchServlet", urlPatterns = {"/SearchServlet"})
public class SearchServlet extends HttpServlet {

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
        String searchValue = request.getParameter("searchValue");
        String url = SEARCH_PAGE;
        Map<ShoesDTO, List> productList = new LinkedHashMap<>();
        try {
            if (searchValue != null) {
                if (searchValue.length() > 0) {
                    ShoesDAO daoShoes = new ShoesDAO();
                    List<ShoesDTO> listShoes = daoShoes.search(searchValue);

                    if (listShoes != null) {
                        for (ShoesDTO e : listShoes) {

                            ShoesSizesDetailDAO daoShoesSizes = new ShoesSizesDetailDAO();
                            daoShoesSizes.searchWithShoesID(e.getShoesID());
                            List<ShoesSizesDetailDTO> listShoesSizes = daoShoesSizes.getList();

                            List<SizesDTO> listSizes = new ArrayList<>();

                            if (listShoesSizes != null) {
                                for (ShoesSizesDetailDTO el : listShoesSizes) {

                                    SizesDAO daoSizes = new SizesDAO();
                                    SizesDTO sizesDto = daoSizes.searchSizes(el);
                                    if (sizesDto != null) {
                                        listSizes.add(sizesDto);
                                    }

                                }

                            }

                            if (!listSizes.isEmpty()) {
                                productList.put(e, listSizes);
                            }
                        }
                    }
                    if (productList.size() >= 0) {
                        request.setAttribute("LIST", productList);
                    }
                }

            }
        } catch (SQLException e) {
            log("SearchServlet_SQLException: " + e.getMessage());
        } catch (NamingException e) {
            log("SearchServlet_NamingException: " + e.getMessage());
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
