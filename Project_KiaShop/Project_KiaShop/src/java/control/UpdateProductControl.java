/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.DAO;
import entity.Category;
import entity.Product;
import entity.SubImage;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "UpdateProductControl", urlPatterns = {"/UpdateProductControl"})
public class UpdateProductControl extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");

        DAO dao = new DAO();
        Product product = dao.getProductByID(id);
        List<Category> listC = dao.getAllCategory();

        String select = "";

        for (Category category : listC) {
            if (product.getCateID() != category.getId()) {
                select += "<option value=\"" + category.getId() + "\">" + category.getName() + "</option>\n";
            } else {
                select += "<option value=\"" + category.getId() + "\"selected>" + category.getName() + "</option>\n";
            }
        }

        PrintWriter out = response.getWriter();
        out.println("<div class=\"modal-dialog\">\n"
                + "                    <div class=\"modal-content\">\n"
                + "                        <form action=\"UpdateProductControl\" method=\"post\">\n"
                + "                            <div class=\"modal-header\">\n"
                + "                                <h4 class=\"modal-title\">Edit Product</h4>\n"
                + "                                <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-hidden=\"true\">&times;</button>\n"
                + "                            </div>\n"
                + "                            <div class=\"modal-body\">\n"
                + "                                \n"
                + "                                    <input name=\"pID\" type=\"hidden\" class=\"form-control\" value=\"" + product.getId() + "\">\n"
                + "                                <div class=\"form-group\">\n"
                + "                                    <label>Tên</label>\n"
                + "                                    <input name=\"name\" type=\"text\" class=\"form-control\" required value=\"" + product.getName() + "\">\n"
                + "                                </div>\n"
                + "                                <div class=\"form-group\">\n"
                + "                                    <label>Ảnh</label>\n"
                + "                                    <input name=\"image\" type=\"text\" class=\"form-control\" required value=\"" + product.getImage() + "\">\n"
                + "<img src=\"" + product.getImage() + "\"/>"
                + "                                </div>\n"
                + "                                <div class=\"form-group\">\n"
                + "                                    <label>Ảnh phụ 1</label>\n"
                + "                                    <input name=\"subImage1\" type=\"text\" class=\"form-control\" required value=\"" + product.getSubImage().get(0).getImage() + "\">\n"
                + "<img src=\"" + product.getSubImage().get(0).getImage() + "\"/>"
                + "                                </div>\n"
                + "                                <div class=\"form-group\">\n"
                + "                                    <label>Ảnh phụ 2</label>\n"
                + "                                    <input name=\"subImage2\" type=\"text\" class=\"form-control\" required value=\"" + product.getSubImage().get(1).getImage() + "\">\n"
                + "<img src=\"" + product.getSubImage().get(1).getImage() + "\"/>"
                + "                                </div>\n"
                + "                                <div class=\"form-group\">\n"
                + "                                    <label>Ảnh phụ 3</label>\n"
                + "                                    <input name=\"subImage3\" type=\"text\" class=\"form-control\" required value=\"" + product.getSubImage().get(2).getImage() + "\">\n"
                + "<img src=\"" + product.getSubImage().get(2).getImage() + "\"/>"
                + "                                </div>\n"
                + "                                <div class=\"form-group\">\n"
                + "                                    <label>Ảnh phụ 4</label>\n"
                + "                                    <input name=\"subImage4\" type=\"text\" class=\"form-control\" required value=\"" + product.getSubImage().get(3).getImage() + "\">\n"
                + "<img src=\"" + product.getSubImage().get(3).getImage() + "\"/>"
                + "                                </div>\n"
                + "                                <div class=\"form-group\">\n"
                + "                                    <label>Giá</label>\n"
                + "                                    <input name=\"price\" type=\"text\" class=\"form-control\" required value=\"" + product.getPrice() + "\">\n"
                + "                                </div>\n"
                + "                                <div class=\"form-group\">\n"
                + "                                    <label>Tiêu đề</label>\n"
                + "                                    <textarea name=\"title\" class=\"form-control\" required>" + product.getTitle() + "</textarea>\n"
                + "                                </div>\n"
                + "                                <div class=\"form-group\">\n"
                + "                                    <label>Mô tả</label>\n"
                + "                                    <textarea name=\"description\" class=\"form-control\" required>" + product.getDescription() + "</textarea>\n"
                + "                                </div>\n"
                + "                                <div class=\"form-group\">\n"
                + "                                    <label>Số lượng</label>\n"
                + "                                    <input name=\"amount\" type=\"text\" class=\"form-control\" required value=\"" + product.getAmount() + "\">\n"
                + "                                </div>\n"
                + "                                <div class=\"form-group\">\n"
                + "                                    <label>Loại sản phẩm</label>\n"
                + "                                    <select name=\"category\" class=\"form-select\" aria-label=\"Default select example\">\n"
                + select
                + "                                    </select>"
                + "                                </div>\n"
                + "                                \n"
                + "                            </div>\n"
                + "                            <div class=\"modal-footer\">\n"
                + "                                <input type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\" value=\"Hủy\">\n"
                + "                                <input type=\"submit\" class=\"btn btn-info\" value=\"Lưu\">\n"
                + "                            </div>\n"
                + "                        </form>\n"
                + "                    </div>\n"
                + "                </div>");
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
        request.setCharacterEncoding("UTF-8");
        //processRequest(request, response);
        String name = request.getParameter("name");
        String pID = request.getParameter("pID");
        String image = request.getParameter("image");
        String subImage1 = request.getParameter("subImage1");
        String subImage2 = request.getParameter("subImage2");
        String subImage3 = request.getParameter("subImage3");
        String subImage4 = request.getParameter("subImage4");
        String price = request.getParameter("price");
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String amount = request.getParameter("amount");
        String category = request.getParameter("category");

        DAO dao = new DAO();
        Product oldProduct = dao.getProductByID(pID);

        List<SubImage> listSubImg = dao.getAllSubImageByPID(pID);
        SubImage s = listSubImg.get(0);
        SubImage s1 = listSubImg.get(1);
        SubImage s2 = listSubImg.get(2);
        SubImage s3 = listSubImg.get(3);
        s.setImage(subImage1);
        s1.setImage(subImage2);
        s2.setImage(subImage3);
        s3.setImage(subImage4);

        dao.updateSubImage(s.getpID() + "", s.getImage() + "", s.getSubImageID() + "");
        dao.updateSubImage(s1.getpID() + "", s1.getImage() + "", s1.getSubImageID() + "");
        dao.updateSubImage(s2.getpID() + "", s2.getImage() + "", s2.getSubImageID() + "");
        dao.updateSubImage(s3.getpID() + "", s3.getImage() + "", s3.getSubImageID() + "");
//            String oldImg = oldProduct.getSubImage().
//            dao.updateSubImage(pID + "",subImage1,pID, oldProduct.getSubImage().get(0).getImage());
//            dao.updateSubImage(pID + "", subImage2,pID,oldProduct.getSubImage().get(1).getImage());
//            dao.updateSubImage(pID + "", subImage3,pID,oldProduct.getSubImage().get(2).getImage());
//            dao.updateSubImage(pID + "", subImage4,pID,oldProduct.getSubImage().get(3).getImage());
        dao.updateProduct(name, image, price, title, description, category, Integer.parseInt(amount), Integer.parseInt(pID));
//            int pID = dao.getProductIDToAdd();

        request.getRequestDispatcher("ManagerControl").forward(request, response);
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
