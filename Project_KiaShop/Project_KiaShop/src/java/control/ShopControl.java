/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.DAO;
import entity.Category;
import entity.Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
@WebServlet(name = "ShopControl", urlPatterns = {"/ShopControl"})
public class ShopControl extends HttpServlet {

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
        DAO dao = new DAO();
        try {
            List<Category> listC = dao.getAllCategory();
            request.setAttribute("listC", listC);
            String currentCID = request.getParameter("cID");
            String showAllProductsParam = request.getParameter("showAllProducts");
            currentCID = (currentCID == null || currentCID.equals("")) ? String.valueOf(listC.get(0).getId()) : currentCID;
            boolean showAllProducts = Boolean.parseBoolean(showAllProductsParam);
            if (!showAllProducts) {
                request.setAttribute("cID", currentCID);
            }
            request.setAttribute("showAllProducts", showAllProducts);
            int pageIndex = (request.getParameter("pageIndex") != null) ? Integer.parseInt(request.getParameter("pageIndex")) : 1;
            if (showAllProducts) {
                List<Product> allProducts = dao.getAllProduct();
                int numberProductPerPage = 10;
                int pageSize = dao.getPageSize(numberProductPerPage, allProducts.size());
                List<Product> paginatedProducts = dao.paginateList(allProducts, pageIndex, numberProductPerPage);
                request.setAttribute("listP", paginatedProducts);
                request.setAttribute("pageIndex", pageIndex);
                request.setAttribute("totalPage", pageSize);
            } else {
                int numberProduct = (request.getParameter("numberProduct") != null) ? Integer.parseInt(request.getParameter("numberProduct")) : 6;
                List<Product> ls = dao.getProductByCid(currentCID);
                int pageSize = dao.getPageSize(numberProduct, dao.getProductByCid(currentCID).size());

                request.setAttribute("listP", ls);
                request.setAttribute("numberProduct", numberProduct);
                request.setAttribute("pageIndex", pageIndex);
                request.setAttribute("totalPage", pageSize);
                request.setAttribute("tag", currentCID);
            }

            request.getRequestDispatcher("Shop.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.getRequestDispatcher("Shop.jsp").forward(request, response);
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
