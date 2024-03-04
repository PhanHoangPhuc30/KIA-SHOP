/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package control;

import dao.DAO;
import entity.Category;
import entity.Product;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MSI GTX
 */
@WebServlet(name = "SearchControl", urlPatterns = {"/SearchControl"})
public class SearchControl extends HttpServlet {

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
        //        response.setContentType("text/html;charset=UTF-8");
//        String txtSearch = request.getParameter("txt");
//        String cID = request.getParameter("cID");
//        DAO dao = new DAO();
//        List<Category> listC = dao.getAllCategory();
//        request.setAttribute("listC", listC);
//        List<Product> list;
//        // Kiểm tra xem đã nhập từ khóa tìm kiếm chưa
//        if (txtSearch == null || txtSearch.isEmpty()) {
//            // Chuyển hướng người dùng trở lại trang hiện tại
//            request.setAttribute("listC", listC);
//            request.getRequestDispatcher("Shop.jsp").forward(request, response);
//            return;
//        }
//        if (cID != null && !cID.isEmpty()) {
//            list = dao.search(txtSearch, cID);
//        } else {
//            // Nếu không có danh mục, chỉ thực hiện tìm kiếm theo tên
//            list = dao.searchByName(txtSearch);
//        }
//
//        request.setAttribute("listP", list);
//        request.setAttribute("txtSearch", txtSearch);
//        request.setAttribute("cID", cID);
//
//        request.getRequestDispatcher("Shop.jsp").forward(request, response);
//Dang sua
//        String txtSearch = request.getParameter("txt");
//        String cID = request.getParameter("cID");
//        DAO dao = new DAO();
//        List<Category> listC = dao.getAllCategory();
//        request.setAttribute("listC", listC);
//
//        if (txtSearch != null) {
//            // Loại bỏ khoảng trắng không mong muốn
//            txtSearch = txtSearch.trim();
//            if (!txtSearch.isEmpty()) {
//                List<Product> list;
//                if (cID != null && !cID.isEmpty()) {
//                    list = dao.search(txtSearch, cID);
//                } else {
//                    // Nếu không có danh mục, chỉ thực hiện tìm kiếm theo tên
//                    list = dao.searchByName(txtSearch);
//                }
//                request.setAttribute("listP", list);
//                request.setAttribute("txtSearch", txtSearch);
//                request.setAttribute("cID", cID);
//                request.getRequestDispatcher("Shop.jsp").forward(request, response);
//                return;
//            }
//        }
//
//        // Nếu từ khóa tìm kiếm là rỗng, hiển thị thông báo và giữ lại trang hiện tại
//        String errorMessage = "Please enter a keyword for search";
//        request.setAttribute("errorMessage", errorMessage);
//        List<Product> listP = dao.getAllProduct();
//        request.setAttribute("listP", listP);
//        request.getRequestDispatcher("Shop.jsp").forward(request, response);
//
//    }
        String txtSearch = request.getParameter("txt");
        String cID = request.getParameter("cID");
        DAO dao = new DAO();
        List<Category> listC = dao.getAllCategory();
        request.setAttribute("listC", listC);
        List<Product> list;

        if (txtSearch != null && !txtSearch.trim().isEmpty()) {
            if (cID != null && !cID.isEmpty()) {
                list = dao.search(txtSearch, cID);
            } else {
                list = dao.searchByName(txtSearch);
            }
            request.setAttribute("listP", list);
            request.setAttribute("txtSearch", txtSearch);
            request.setAttribute("cID", cID);
            // Sau khi tìm kiếm xong và trước khi chuyển hướng tới trang JSP, tính số lượng sản phẩm đã tìm thấy và lưu vào biến resultCount
            int resultCount = list.size();
            request.setAttribute("resultCount", resultCount);
            if (resultCount == 0) {
                request.setAttribute("errorMessage", "No products found matching your search criteria.");
            }

            request.getRequestDispatcher("Shop.jsp").forward(request, response);
        } else {
            request.setAttribute("message", "Please enter a keyword.");
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
