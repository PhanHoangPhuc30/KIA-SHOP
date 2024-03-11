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
        //Ngà
        try {
            List<Category> listC = dao.getAllCategory();
            request.setAttribute("listC", listC);
            String currentCID = request.getParameter("cID");
            String showAllProductsParam = request.getParameter("showAllProducts");
            boolean showAllProducts = Boolean.parseBoolean(showAllProductsParam);
            // Lưu giữ giá trị cID trước đó nếu showAllProducts là true
            currentCID = (currentCID == null || currentCID.equals("")) ? listC.get(0).getId() + "" : Integer.parseInt(currentCID) + "";
            if (showAllProducts) {
                String previousCID = (String) request.getSession().getAttribute("previousCID");
            }
            // Lưu giữ giá trị cID vào session
            request.getSession().setAttribute("previousCID", currentCID);

            request.setAttribute("showAllProducts", showAllProducts);
            request.setAttribute("cID", currentCID);

            int pageIndex = (request.getParameter("pageIndex") != null) ? Integer.parseInt(request.getParameter("pageIndex")) : 1;
            if (showAllProducts) {
                List<Product> allProducts = dao.getAllProduct();
                int numberProductPerPage = (request.getParameter("numberProductPerPage") != null) ? Integer.parseInt(request.getParameter("numberProductPerPage")) : 10;
                int pageSize = dao.getPageSize(numberProductPerPage, allProducts.size());
                List<Product> paginatedProducts = dao.paginateList(allProducts, pageIndex, numberProductPerPage);

                request.setAttribute("numberProductPerPage", numberProductPerPage);
                request.setAttribute("listP", paginatedProducts);
                request.setAttribute("pageIndex", pageIndex);
                request.setAttribute("totalPage", pageSize);

            } else {
                int numberProduct = (request.getParameter("numberProduct") != null) ? Integer.parseInt(request.getParameter("numberProductPerPage")) : 6;
                List<Product> ls = dao.getProductByCid(currentCID);

                int totalProducts = ls.size();
                int totalPage = (int) Math.ceil((double) totalProducts / numberProduct);
                int start = (pageIndex - 1) * numberProduct;
                int end = Math.min(start + numberProduct, totalProducts);
                List<Product> paginatedProducts = ls.subList(start, end);

                request.setAttribute("listP", paginatedProducts);
                request.setAttribute("pageIndex", pageIndex);
                request.setAttribute("totalPage", totalPage);
                request.setAttribute("numberProductPerPage", numberProduct);
                request.setAttribute("tag", currentCID);
            }
            request.getRequestDispatcher("Shop.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.getRequestDispatcher("Shop.jsp").forward(request, response);
        }

//        response.setContentType("text/html;charset=UTF-8");
//        try ( PrintWriter out = response.getWriter()) {
//            List<Category> lsNewsType = new DAO().getAllCategory();
//            String txtSearch = request.getParameter("txtSearch");
//            String cID = request.getParameter("cID");
//            String sort = request.getParameter("sort");
//            sort = (sort == null || sort.equals("")) ? "asc" : sort;
//            cID = (cID == null || cID.equals("")) ? lsNewsType.get(0).getId() + "" : Integer.parseInt(cID) + "";
//            int pageSize = getPageSize(6, new DAO().search(txtSearch, cID).size());
//            String index = request.getParameter("pageIndex");
//            int pageIndex = 0;
//            if (index == null) {
//                pageIndex = 1;
//            } else {
//                pageIndex = Integer.parseInt(index);
//            }
//
//            List<Product> ls = new DAO().searchWithPaging(txtSearch, pageIndex, 6, cID, sort);
//
//            request.setAttribute("totalPage", pageSize);
//            request.setAttribute("numberProduct", 6);
//            request.setAttribute("pageIndex", pageIndex);
//            request.setAttribute("searchValue", txtSearch);
//            request.setAttribute("cID", cID);
//            request.setAttribute("listP", ls);
//            request.setAttribute("sort", sort);
//
//            request.setAttribute("listC", lsNewsType);
//
//            request.setAttribute("tag", (cID == null || cID.equals("")) ? lsNewsType.get(0).getId() : Integer.parseInt(cID));
//
//            request.getRequestDispatcher("Shop.jsp").forward(request, response);
//        } catch (Exception e) {
//            request.getRequestDispatcher("Shop.jsp").forward(request, response);
//        }
//    }
//
//    public int getPageSize(int numberProduct, int allProduct) {
//        int pageSize = allProduct / numberProduct;
//        if (allProduct % numberProduct != 0) {
//            pageSize = (allProduct / numberProduct) + 1;
//        }
//        return pageSize;
//
//    }
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
