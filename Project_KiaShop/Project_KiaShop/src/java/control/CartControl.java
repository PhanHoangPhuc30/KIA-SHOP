/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.DAO;
import entity.Cart;
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
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
@WebServlet(name = "CartControl", urlPatterns = {"/cart"})
public class CartControl extends HttpServlet {

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
        HttpSession session = request.getSession(true);
        String id = request.getParameter("id");
        String action = request.getParameter("action");

        if (!(id == null && action == null)) {
            if (action != null && action.equalsIgnoreCase("add")) {
                if (session.getAttribute("cart") == null) {
                    List<Product> lst = new ArrayList<>();
                    session.setAttribute("cart", new Cart(lst));
                }
                Product p = new DAO().getProductByID(id);
                Cart c = (Cart) session.getAttribute("cart");
                c.add(new Product(p.getId(), p.getName(), p.getImage(), p.getPrice(),
                        p.getTitle(), p.getDescription(), p.getCateID(), p.getSubImage(),
                        p.getAmount(), 1, p.getIsDeleted()));
                session.setAttribute("cart", c);
            }
            if (action != null && action.equalsIgnoreCase("minus")) {
                Product p = new DAO().getProductByID(id);
                Cart c = (Cart) session.getAttribute("cart");
                c.minus(new Product(p.getId(), p.getName(), p.getImage(), p.getPrice(),
                        p.getTitle(), p.getDescription(), p.getCateID(), p.getSubImage(),
                        p.getAmount(), p.getIsDeleted()));
                session.setAttribute("cart", c);
            } else if (action != null && action.equalsIgnoreCase("delete")) {
                Cart c = (Cart) session.getAttribute("cart");
                c.remove(Integer.parseInt(id));
                session.setAttribute("cart", c);
            }
        }
        request.getRequestDispatcher("Cart.jsp").forward(request, response);
//        response.sendRedirect("order");
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
