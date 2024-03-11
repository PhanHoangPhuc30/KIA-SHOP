/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import context.DBContext;
import entity.Account;
import entity.Category;
import entity.Order;
import entity.OrderDetails;
import entity.Product;
import entity.Size;
import entity.SizeDetail;
import entity.SubImage;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Admin
 */
public class DAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    // lấy hình ảnh bằng ID sản phẩm
    public List<SubImage> getAllSubImageByPID(String cid) {
        List<SubImage> list = new ArrayList<>();
        String query = "select S.*\n"
                + "from Product P, SubImage S\n"
                + "where P.pID = S.pID and S.pID = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, cid);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new SubImage(rs.getInt(1), rs.getInt(2), rs.getString(3)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    // hiện tất cả sản phẩm có isdelete bằng 0
    public List<Product> getAllProduct() {
        List<Product> list = new ArrayList<>();
        String query = "select * from Product where isDeleted != 1";
        DAO dao = new DAO();
        try {
            conn = new DBContext().getConnection(); //mo ket noi toi sql
            ps = conn.prepareStatement(query);//nem cau lenh query sang sql
            rs = ps.executeQuery();//chay cau lenh query, nhan ket qua tra ve
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7), dao.getAllSubImageByPID(rs.getInt(1) + ""), dao.getAllSizeByID(rs.getInt(1) + ""), rs.getInt(8), rs.getInt(9)));
            }

        } catch (Exception e) {
        }
        return list;
    }

    // hiện tất cả sản phẩm theo danh mục
    public List<Category> getAllCategory() {
        List<Category> list = new ArrayList<>();
        String query = "select * from Category";
        try {
            conn = new DBContext().getConnection(); //mo ket noi toi sql
            ps = conn.prepareStatement(query);//nem cau lenh query sang sql
            rs = ps.executeQuery();//chay cau lenh query, nhan ket qua tra ve
            while (rs.next()) {
                list.add(new Category(rs.getInt(1), rs.getString(2)));
            }

        } catch (Exception e) {
        }
        return list;
    }

    // tổng số lượng đơn hàng
    public int getNumberItemsSolid() {
        int n = 0;
        String query = "select SUM(Quantity) from OrderDetails";
        try {
            conn = new DBContext().getConnection(); //mo ket noi toi sql
            ps = conn.prepareStatement(query);//nem cau lenh query sang sql
            rs = ps.executeQuery();//chay cau lenh query, nhan ket qua tra ve
            if (rs.next()) {
                n = rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return n;
    }

    // tính tổng đơn hàng
    public double getTotalEarnings() {
        double total = 0;
        List<OrderDetails> list = new ArrayList<>();
        String query = "select * from OrderDetails";
        try {
            conn = new DBContext().getConnection(); //mo ket noi toi sql
            ps = conn.prepareStatement(query);//nem cau lenh query sang sql
            rs = ps.executeQuery();//chay cau lenh query, nhan ket qua tra ve
            while (rs.next()) {
                list.add(new OrderDetails(rs.getInt(1), rs.getInt(2), rs.getDouble(3), rs.getInt(4)));
            }

        } catch (Exception e) {
        }
        for (OrderDetails o : list) {
            total += o.getPrice() * o.getQuantity();
        }
        return total;
    }
    //-----------------Login-------------------

    public String encryptToMD5(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(password.getBytes());

            StringBuilder hexString = new StringBuilder();
            for (byte b : messageDigest) {
                String hex = Integer.toHexString(0xFF & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;

        }
    }

    // login 
    public Account login(String email, String password) {
        String query = "select * from Account\n"
                + "where email = ? and password = ?";
        try {
            conn = new DBContext().getConnection(); //mo ket noi toi sql
            ps = conn.prepareStatement(query);//nem cau lenh query sang sql
            String enterdPassword = encryptToMD5(password);
            ps.setString(1, email);
            ps.setString(2, enterdPassword);
            rs = ps.executeQuery();//chay cau lenh query, nhan ket qua tra ve
            while (rs.next()) {
                return new Account(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7),
                        rs.getInt(8),
                        rs.getDate(9));
            }
        } catch (Exception e) {
        }
        return null;
    }

    //check email exits
    public Account checkExist(String email) {
        String query = "select * from Account\n"
                + "where email = ?";
        try {
            conn = new DBContext().getConnection(); //mo ket noi toi sql
            ps = conn.prepareStatement(query);//nem cau lenh query sang sql
            ps.setString(1, email);
            rs = ps.executeQuery();//chay cau lenh query, nhan ket qua tra ve
            while (rs.next()) {
                return new Account(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7),
                        rs.getInt(8),
                        rs.getDate(9));
            }
        } catch (Exception e) {
        }
        return null;
    }

    // update password
    public Account Updatepass(String password, int pin, String email) {
        String query = "UPDATE Account\n"
                + "SET password = ?, pin = ?\n"
                + "WHERE email = ?";
        try {
            conn = new DBContext().getConnection(); //mo ket noi toi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, password);
            ps.setInt(2, pin);
            ps.setString(3, email);
            ps.executeUpdate();

        } catch (Exception ex) {
        }
        return null;
    }

    // check pin 
    public Account checkpin(int pin) {
        String query = "select * from Account\n"
                + "WHERE pin=?";
        try {
            conn = new DBContext().getConnection(); //mo ket noi toi sql
            ps = conn.prepareStatement(query);//nem cau lenh query sang sql
            ps.setInt(1, pin);
            rs = ps.executeQuery();//chay cau lenh query, nhan ket qua tra ve
            while (rs.next()) {
                return new Account(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7),
                        rs.getInt(8),
                        rs.getDate(9));
            }
        } catch (Exception ex) {
        }
        return null;
    }

    // update mã pin
    public Account UpdatePin(int pin, String email) {
        String query = "UPDATE Account\n"
                + "SET pin = ?\n"
                + "WHERE email = ?";
        try {
            conn = new DBContext().getConnection(); //mo ket noi toi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, pin);
            ps.setString(2, email);
            ps.executeUpdate();

        } catch (Exception ex) {
        }
        return null;
    }

    // xóa mã pin
    public Account DeletePin(String emails) {
        String query = "UPDATE Account \n"
                + "SET pin = 0\n"
                + "WHERE email=?";
        try {
            conn = new DBContext().getConnection(); //mo ket noi toi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, emails);
            ps.executeUpdate();
        } catch (Exception ex) {
        }
        return null;
    }

    // register Account
    public void signUp(String username, String email, String password, int pin, Date date) {
        String query = "insert into Account\n"
                + "values(?,?,?, null, null,0,?,CURRENT_TIMESTAMP)";
        try {
            conn = new DBContext().getConnection(); //mo ket noi toi sql
            ps = conn.prepareStatement(query);//nem cau lenh query sang sql
            ps.setString(1, username);
            ps.setString(2, email);
            ps.setString(3, password);
            ps.setInt(4, pin);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    // xóa tài khoản khỏi databases
    public int DeleteAccount(String email) throws Exception {
        String query = "delete from Account where email=?";
        int result = 0;
        try {
            conn = new DBContext().getConnection(); //mo ket noi toi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, email);
            result = ps.executeUpdate();
        } catch (SQLException ex) {
        }
        return result;
    }

    // comment
    public boolean checkorder(String email) throws Exception {
        String query = "SELECT COUNT(*) AS PurchaseCount FROM Account AS A\n"
                + " LEFT JOIN Order AS O ON A.uID = O.accountID \" +\n"
                + " WHERE A.email = ?";
        try {
            conn = new DBContext().getConnection(); //mo ket noi toi sql
            ps = conn.prepareStatement(query);
            // Set the parameter value
            ps.setString(1, email);
            // Execute the query
            try ( ResultSet resultSet = ps.executeQuery()) {
                if (resultSet.next()) {
                    int purchaseCount = resultSet.getInt("PurchaseCount");
                    return purchaseCount > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception according to your application's requirements
        }
        return false;
    }

    public void checkaccountcomment() {
        String query = "SELECT DISTINCT Account.uID FROM Account \n"
                + "INNER JOIN OrderTable ON Account.uID = OrderTable.AccountID";
        try {
            conn = new DBContext().getConnection(); //mo ket noi toi sql
            ps = conn.prepareStatement(query);//nem cau lenh query sang sql

        } catch (Exception e) {
        }

    }

    public List<Product> getProductByCid(String cid) {
        List<Product> list = new ArrayList<>();
        String query = "select * from Product where cID = ? and isDeleted != 1";
        DAO dao = new DAO();
        try {
            conn = new DBContext().getConnection(); //mo ket noi toi sql
            ps = conn.prepareStatement(query);//nem cau lenh query sang sql
            ps.setString(1, cid);
            rs = ps.executeQuery();//chay cau lenh query, nhan ket qua tra ve
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7), dao.getAllSubImageByPID(rs.getInt(1) + ""), dao.getAllSizeByID(rs.getInt(1) + ""), rs.getInt(8), rs.getInt(9)));
            }

        } catch (Exception e) {
        }
        return list;
    }

    public List<Product> getProductByPrice(double minprice, double maxprice) {
        List<Product> list = new ArrayList<>();
        String query = "select *\n"
                + "from Product\n"
                + "where price >= ? AND price <= ? AND isDeleted != 1";
        DAO dao = new DAO();
        try {
            conn = new DBContext().getConnection(); //mo ket noi toi sql
            ps = conn.prepareStatement(query);//nem cau lenh query sang sql
            ps.setDouble(1, minprice);
            ps.setDouble(2, maxprice);
            rs = ps.executeQuery();//chay cau lenh query, nhan ket qua tra ve
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7), dao.getAllSubImageByPID(rs.getInt(1) + ""), dao.getAllSizeByID(rs.getInt(1) + ""), rs.getInt(8), rs.getInt(9)));
            }

        } catch (Exception e) {
        }
        return list;
    }

    public List<Product> getProductByPricecatogy(double minprice, double maxprice, String cID) {
        List<Product> list = new ArrayList<>();
        String query = "select *\n"
                + "from Product\n"
                + "where price >= ? AND price <= ? AND cID=? AND isDeleted != 1";
        DAO dao = new DAO();
        try {
            conn = new DBContext().getConnection(); //mo ket noi toi sql
            ps = conn.prepareStatement(query);//nem cau lenh query sang sql
            ps.setDouble(1, minprice);
            ps.setDouble(2, maxprice);
            ps.setString(3, cID);
            rs = ps.executeQuery();//chay cau lenh query, nhan ket qua tra ve
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7), dao.getAllSubImageByPID(rs.getInt(1) + ""), dao.getAllSizeByID(rs.getInt(1) + ""), rs.getInt(8), rs.getInt(9)));
            }

        } catch (Exception e) {
        }
        return list;
    }

    public String getCnameByCID(String cid) {
        String query = "select distinct cName from Product P, Category C\n"
                + "where P.cID = C.cID and P.cID = ?";
        try {
            conn = new DBContext().getConnection(); //mo ket noi toi sql
            ps = conn.prepareStatement(query);//nem cau lenh query sang sql
            ps.setString(1, cid);
            rs = ps.executeQuery();//chay cau lenh query, nhan ket qua tra ve
            while (rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception e) {
        }
        return null;
    }

    public List<Product> searchByName(String txtSearch) {
        List<Product> list = new ArrayList<>();
        String query = "select * from Product where pName like ?";
        try {
            conn = new DBContext().getConnection(); //mo ket noi toi sql
            ps = conn.prepareStatement(query);//nem cau lenh query sang sql
            ps.setString(1, "%" + txtSearch + "%");
            rs = ps.executeQuery();//chay cau lenh query, nhan ket qua tra ve
            DAO dao = new DAO();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7), dao.getAllSubImageByPID(rs.getInt(1) + ""), dao.getAllSizeByID(rs.getInt(1) + ""), rs.getInt(8), rs.getInt(9)));
            }

        } catch (Exception e) {
        }
        return list;
    }

    public Product getNewestProduct() {
        String query = "select top 1 *\n"
                + "from Product where isDeleted != 1\n"
                + "order by pID desc";
        try {
            conn = new DBContext().getConnection(); //mo ket noi toi sql
            ps = conn.prepareStatement(query);//nem cau lenh query sang sql
            rs = ps.executeQuery();//chay cau lenh query, nhan ket qua tra ve
            DAO dao = new DAO();
            while (rs.next()) {
                return new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7), dao.getAllSubImageByPID(rs.getInt(1) + ""), dao.getAllSizeByID(rs.getInt(1) + ""), rs.getInt(8), rs.getInt(9));
            }
        } catch (Exception e) {
        }
        return null;
    }

    public int getBestSeller() {
        String query = "with R as(\n"
                + "select ProductID,SUM(Quantity) SL\n"
                + "from Product P, OrderDetails O\n"
                + "where P.pID = O.ProductID\n"
                + "group by ProductID\n"
                + ")\n"
                + "select top 1 R.ProductID from R where SL = (select MAX(SL) from R)\n"
                + "order by R.ProductID desc";
        try {
            conn = new DBContext().getConnection(); //mo ket noi toi sql
            ps = conn.prepareStatement(query);//nem cau lenh query sang sql
            rs = ps.executeQuery();//chay cau lenh query, nhan ket qua tra ve
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public List<Integer> getBestSeller1() {
        List<Integer> list = new ArrayList<>();
        String query = "with R as(\n"
                + "select ProductID,SUM(Quantity) SL\n"
                + "from Product P, OrderDetails O\n"
                + "where P.pID = O.ProductID\n"
                + "group by ProductID\n"
                + ")\n"
                + "select R.ProductID from R where SL = (select MAX(SL) from R)";
        try {
            conn = new DBContext().getConnection(); //mo ket noi toi sql
            ps = conn.prepareStatement(query);//nem cau lenh query sang sql
            rs = ps.executeQuery();//chay cau lenh query, nhan ket qua tra ve
            while (rs.next()) {
                list.add(rs.getInt(1));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public Product getProductbyID(String id) {
        String query = "select * from Product where pID = ?";
        try {
            conn = new DBContext().getConnection(); //mo ket noi toi sql
            ps = conn.prepareStatement(query);//nem cau lenh query sang sql
            ps.setString(1, id);
            rs = ps.executeQuery();//chay cau lenh query, nhan ket qua tra ve
            DAO dao = new DAO();
            while (rs.next()) {
                return new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7), dao.getAllSubImageByPID(rs.getInt(1) + ""), dao.getAllSizeByID(rs.getInt(1) + ""), rs.getInt(8), rs.getInt(9));
            }
        } catch (Exception e) {
        }
        return null;
    }

    public List<Product> getAllPagging(int pageIndex, int pageSize) {
        List<Product> list = new ArrayList<>();
        try {
            String query = "SELECT * FROM Product where isDeleted != 1 ORDER BY pID OFFSET\n"
                    + "                    (?*?-?) ROWS FETCH NEXT ? ROWS ONLY";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, pageIndex);
            ps.setInt(2, pageSize);
            ps.setInt(3, pageSize);
            ps.setInt(4, pageSize);
            ResultSet rs = ps.executeQuery();
            DAO dao = new DAO();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7), dao.getAllSubImageByPID(rs.getInt(1) + ""), dao.getAllSizeByID(rs.getInt(1) + ""), rs.getInt(8), rs.getInt(9)));
            }
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
        return list;
    }

    public List<Product> getAllPaggingByCategory(int pageIndex, int pageSize, String id) {
        List<Product> list = new ArrayList<>();
        try {
            String query = "SELECT * FROM Product where cID = ? and isDeleted !=1 ORDER BY pID OFFSET (?*?-?) ROWS FETCH NEXT ? ROWS ONLY";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, id);
            ps.setInt(2, pageIndex);
            ps.setInt(3, pageSize);
            ps.setInt(4, pageSize);
            ps.setInt(5, pageSize);
            ResultSet rs = ps.executeQuery();
            DAO dao = new DAO();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7), dao.getAllSubImageByPID(rs.getInt(1) + ""), dao.getAllSizeByID(rs.getInt(1) + ""), rs.getInt(8), rs.getInt(9)));
            }
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
        return list;
    }

    public void insertOrder(String date, int accountID, String address, String sdt, String name, double total, String status) {
        String query = "INSERT INTO [Order](orderDate, accountID,addressReceive,sdt,name,totalPrice,status)\n"
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, date);
            //ps.setDate(1, (java.sql.Date) date);
            ps.setInt(2, accountID);
            ps.setString(3, address);
            ps.setString(4, sdt);
            ps.setString(5, name);
            ps.setDouble(6, total);
            ps.setString(7, status);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public int getOrderID() {
        String query = "SELECT TOP(1) id from [Order] order BY id DESC";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public void insertOrderDetails(int orderID, int productID, double price, int amount, int sizeId) {
        String query = "INSERT INTO OrderDetails VALUES (?, ?, ?, ?, ?)";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, orderID);
            ps.setInt(2, productID);
            ps.setDouble(3, price);
            ps.setInt(4, amount);
            ps.setInt(5, sizeId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateAmounProduct(int amount, int productID) {
        String query = "UPDATE Product SET pAmount = ? WHERE pID = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, amount);
            ps.setInt(2, productID);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    
    public void updateQuantitySize(int sizeId, int productID, int quantity) {
        String query = "UPDATE SizeDetail SET quantity = ? WHERE pID = ? and sizeID = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, quantity);
            ps.setInt(2, productID);
            ps.setInt(3, sizeId);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void addNewProduct(String name, String image, String price,
            String title, String description, String cid, int amount) {
        String query = "INSERT into Product (pName, [image], price, title, [description], cID, pAmount, isDeleted)\n"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, 0)";
        try {
            conn = new DBContext().getConnection(); //mo ket noi toi sql
            ps = conn.prepareStatement(query);//nem cau lenh query sang sql
            ps.setString(1, name);
            ps.setString(2, image);
            ps.setString(3, price);
            ps.setString(4, title);
            ps.setString(5, description);
            ps.setString(6, cid);
            ps.setInt(7, amount);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void addNewSubImage(String pID, String Simage) {
        String query = "INSERT into SubImage (pID, SImage)\n"
                + "VALUES (?, ?)";
        try {
            conn = new DBContext().getConnection(); //mo ket noi toi sql
            ps = conn.prepareStatement(query);//nem cau lenh query sang sql
            ps.setString(1, pID);
            ps.setString(2, Simage);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public int getProductIDToAdd() {
        String query = "SELECT TOP(1) pID from Product order BY pID DESC";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public Product checkExistProduct(String name, String image, String price,
            String title, String description, String cid, int amount) {
        String query = "select * from Product\n"
                + "where pName = ? and [image] = ? and price = ? and cID = ? and title = ? and [description] = ? and pAmount = ? and isDeleted != 1";
        try {
            conn = new DBContext().getConnection(); //mo ket noi toi sql
            ps = conn.prepareStatement(query);//nem cau lenh query sang sql
            ps.setString(1, name);
            ps.setString(2, image);
            ps.setString(3, price);
            ps.setString(4, cid);
            ps.setString(5, title);
            ps.setString(6, description);
            ps.setInt(7, amount);
            rs = ps.executeQuery();//chay cau lenh query, nhan ket qua tra ve
            DAO dao = new DAO();
            while (rs.next()) {
                return new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7), dao.getAllSubImageByPID(rs.getInt(1) + ""), dao.getAllSizeByID(rs.getInt(1) + ""), rs.getInt(8), rs.getInt(9));
            }
        } catch (Exception e) {
        }
        return null;
    }

    public List<Product> paginateList(List<Product> productList, int pageIndex, int numberProductPerPage) {
        int start = (pageIndex - 1) * numberProductPerPage;
        int end = Math.min(start + numberProductPerPage, productList.size());
        return productList.subList(start, end);

    }

    public int getPageSize(int numberProduct, int allProduct) {
        int pageSize = allProduct / numberProduct;
        if (allProduct % numberProduct != 0) {
            pageSize = (allProduct / numberProduct) + 1;
        }
        return pageSize;
    }

    public int getViewed() {
        int n = 0;
        String query = "select * from [View]";
        try {
            conn = new DBContext().getConnection(); //mo ket noi toi sql
            ps = conn.prepareStatement(query);//nem cau lenh query sang sql
            rs = ps.executeQuery();//chay cau lenh query, nhan ket qua tra ve
            if (rs.next()) {
                n = rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return n;
    }

    public void updateViewed() {//edit param
        //edit query (my_table), number of param
        String query = "update [View]\n"
                + "set viewed = viewed + 1";
        try {
            conn = new DBContext().getConnection(); //mo ket noi toi sql
            ps = conn.prepareStatement(query);//nem cau lenh query sang sql
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void deleteSubImage(String pid) throws Exception {//edit param
        //edit query (my_table), number of param
        String query = "delete from SubImage\n"
                + "where pID = ?";
        try {
            conn = new DBContext().getConnection(); //mo ket noi toi sql
            ps = conn.prepareStatement(query);//nem cau lenh query sang sql
            ps.setString(1, pid);
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        }
    }

    public void deleteProduct(String pid) throws Exception {//edit param
        //edit query (my_table), number of param
        String query = "Update Product\n"
                + "set isDeleted = 1 WHERE pID = ?";
        try {
            conn = new DBContext().getConnection(); //mo ket noi toi sql
            ps = conn.prepareStatement(query);//nem cau lenh query sang sql
            ps.setString(1, pid);
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        }
    }

    public void updateProduct(String name, String image, String price,
            String title, String description, String cid, int amount, int pID) {
        String query = "UPDATE Product set pName = ?, image = ?, price = ?, title = ?, description = ?, cID = ?, pAmount = ? where pID = ?";
        try {
            conn = new DBContext().getConnection(); //mo ket noi toi sql
            ps = conn.prepareStatement(query);//nem cau lenh query sang sql
            ps.setString(1, name);
            ps.setString(2, image);
            ps.setString(3, price);
            ps.setString(4, title);
            ps.setString(5, description);
            ps.setString(6, cid);
            ps.setInt(7, amount);
            ps.setInt(8, pID);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void updateSubImage(String pID, String sImage, String sId) {
        String query = "UPDATE SubImage set pID = ?, SImage = ? where subImageID = ?";
        try {
            conn = new DBContext().getConnection(); //mo ket noi toi sql
            ps = conn.prepareStatement(query);//nem cau lenh query sang sql
            ps.setString(1, pID);
            ps.setString(2, sImage);
            ps.setString(3, sId);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public List<Product> search(String txtSearch, String cID) {
        List<Product> list = new ArrayList<>();
        String query = "SELECT * FROM Product WHERE isDeleted != 1";

        if (cID != null && !cID.trim().equals("")) {
            query += " AND cID = ?";
        }

        if (txtSearch != null && !txtSearch.trim().equals("")) {
            query += " AND pName LIKE ?";
        }

        try {
            DAO dao = new DAO();
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);

            int paramIndex = 1;

            if (cID != null && !cID.trim().equals("")) {
                ps.setInt(paramIndex++, Integer.parseInt(cID));
            }

            if (txtSearch != null && !txtSearch.trim().equals("")) {
                ps.setString(paramIndex++, "%" + txtSearch + "%");
            }

            rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7), dao.getAllSubImageByPID(rs.getInt(1) + ""), dao.getAllSizeByID(rs.getInt(1) + ""), rs.getInt(8), rs.getInt(9)));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Product> searchWithPaging(String txtSearch, int pageIndex, int pageSize, String cID) {
        List<Product> list = new ArrayList<>();
        String query = "SELECT * FROM Product WHERE isDeleted != 1";

        if (cID != null && !cID.trim().equals("")) {
            query += " AND cID = ?";
        }

        if (txtSearch != null && !txtSearch.trim().equals("")) {
            query += " AND pName LIKE ?";
        }

        query += " ORDER BY pID OFFSET (? * ? - ?) ROWS FETCH NEXT ? ROWS ONLY";

        try {
            DAO dao = new DAO();
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);

            int paramIndex = 1;

            if (cID != null && !cID.trim().equals("")) {
                ps.setInt(paramIndex++, Integer.parseInt(cID));
            }

            if (txtSearch != null && !txtSearch.trim().equals("")) {
                ps.setString(paramIndex++, "%" + txtSearch + "%");
            }

            ps.setInt(paramIndex++, pageIndex);
            ps.setInt(paramIndex++, pageSize);
            ps.setInt(paramIndex++, pageSize);
            ps.setInt(paramIndex, pageSize);

            rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7), dao.getAllSubImageByPID(rs.getInt(1) + ""), dao.getAllSizeByID(rs.getInt(1) + ""), rs.getInt(8), rs.getInt(9)));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    //-----------------------Ngà---------------------
    // Lay toan bo khach hang
    public List<Account> getAllCustomer() {
        List<Account> list = new ArrayList<>();
        String query = "select * from Account where role = 0";

        try ( Connection conn = new DBContext().getConnection();  PreparedStatement ps = conn.prepareStatement(query);  ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Account account = new Account();
                account.setId(rs.getInt("uID"));
                account.setFullname(rs.getString("fullname"));
                account.setEmail(rs.getString("email"));
                account.setPhone(rs.getString("phone"));
                account.setAddress(rs.getString("address"));
                list.add(account);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Xử lý ngoại lệ ở đây
        }

        return list;
    }

    //Tong so khach hang
    public int getTotalCustomerCount() {
        int count = 0;
        String query = "SELECT COUNT(*) FROM Account WHERE role = 0";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    //Tim khach hang bang so dien thoai
    public List<Account> searchCustomersByPhoneNumber(String txtSearch) {
        List<Account> customers = new ArrayList<>();
        String query = "SELECT * FROM Account WHERE phone LIKE ? and role = 0";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, "%" + txtSearch + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Account customer = new Account();
                customer.setId(rs.getInt("uID"));
                customer.setFullname(rs.getString("fullname"));
                customer.setEmail(rs.getString("email"));
                customer.setPhone(rs.getString("phone"));
                customer.setAddress(rs.getString("address"));
                customers.add(customer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customers;
    }

    //Sắp xếp khách hàng A-Z
    public List<Account> getCustomersSortedByNameAZ() {
        List<Account> customers = getAllCustomer();
        Collections.sort(customers, Comparator.comparing(Account::getFullname));
        return customers;
    }

    //Sắp xếp khách hàng Z-A
    public List<Account> getCustomersSortedByNameZA() {
        List<Account> customers = getAllCustomer();
        Collections.sort(customers, Comparator.comparing(Account::getFullname).reversed());
        return customers;
    }

    //nhập size và quantity dựa vào pID
    public void addSizeAndQuantity(int productId, int sizeValue, int quantity) {
        try {
            // Tìm sizeID dựa trên sizevalue
            int sizeId = getSizeIdBySizeValue(sizeValue);

            if (sizeId != -1) {
                // Nếu tìm thấy sizeID, thêm dữ liệu vào bảng SizeDetail
                String sizeDetailQuery = "INSERT INTO SizeDetail (pID, sizeID, quantity) VALUES (?, ?, ?)";
                PreparedStatement sizeDetailPs = conn.prepareStatement(sizeDetailQuery);
                sizeDetailPs.setInt(1, productId);
                sizeDetailPs.setInt(2, sizeId);
                sizeDetailPs.setInt(3, quantity);
                sizeDetailPs.executeUpdate();

                // Đóng kết nối và tài nguyên
                sizeDetailPs.close();
            } else {
                System.out.println("Không thể tìm thấy sizeID cho sizevalue đã cho.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // tìm kiếm khách hàng bằng tên
    public List<Account> searchCustomersByName(String txtSearch) {
        List<Account> list = new ArrayList<>();
        String query = "SELECT * FROM Account WHERE LOWER(fullname) LIKE LOWER(?) AND role = 0";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, "%" + txtSearch.toLowerCase() + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Account customer = new Account();
                customer.setId(rs.getInt("uID"));
                customer.setFullname(rs.getString("fullname"));
                customer.setEmail(rs.getString("email"));
                customer.setPhone(rs.getString("phone"));
                customer.setAddress(rs.getString("address"));
                list.add(customer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // lất size value từ bảng size
    private int getSizeIdBySizeValue(int sizeValue) {
        String query = "SELECT sizeID FROM Size WHERE sizevalue = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, sizeValue);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("sizeID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Trả về -1 nếu không tìm thấy sizeID cho sizevalue đã cho
    }

    public List<SizeDetail> getProductSizesByProductID(int productID) {
        List<SizeDetail> sizes = new ArrayList<>();
        String query = "SELECT sd.*, s.sizevalue\n"
                + "FROM SizeDetail sd\n"
                + "JOIN Size s ON sd.sizeID = s.sizeID\n"
                + "WHERE sd.pID = ?";
        try ( Connection conn = new DBContext().getConnection();  PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, productID);
            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int pID = rs.getInt("pID");
                    int sizeID = rs.getInt("sizeID");
                    int quantity = rs.getInt("quantity");
                    int sizeValue = rs.getInt("sizevalue");
                    SizeDetail size = new SizeDetail(sizeID, pID, quantity, sizeValue);
                    sizes.add(size);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sizes;
    }
    
        public SizeDetail getProductSizesByProductIDAndSizeID(int productID, int sizeId) {

        String query = "SELECT sd.*, s.sizevalue\n"
                + "FROM SizeDetail sd\n"
                + "JOIN Size s ON sd.sizeID = s.sizeID\n"
                + "WHERE sd.pID = ? and s.sizeID = ?";
        try ( Connection conn = new DBContext().getConnection();  PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, productID);
             ps.setInt(2, sizeId);
            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int pID = rs.getInt("pID");
                    int sizeID = rs.getInt("sizeID");
                    int quantity = rs.getInt("quantity");
                    int sizeValue = rs.getInt("sizevalue");
                    SizeDetail size = new SizeDetail(sizeID, pID, quantity, sizeValue);
                    return size;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
        
                public Size getSizeById(int sizeId) {

        String query = "SELECT *"
                + "FROM Size s "
                + "WHERE s.sizeID = ?";
        try ( Connection conn = new DBContext().getConnection();  PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, sizeId);
            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                   
                    Size size = new Size(rs.getInt(1), rs.getInt(2));
                    return size;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //Sắp xếp khách hàng mới
    public List<Account> getNewCustomers() {
        List<Account> customers = getAllCustomer();
        Collections.sort(customers, Comparator.comparing(Account::getId).reversed());
        return customers;
    }
//Sắp xếp sản phẩm hàng A-Z

    public List<Product> getProductSortedByNameAZ() {
        List<Product> products = getAllProduct();
        Collections.sort(products, Comparator.comparing(Product::getName));
        return products;
    }

    //Sắp xếp sản phẩm hàng Z-A
    public List<Product> getProductSortedByNameZA() {
        List<Product> products = getAllProduct();
        Collections.sort(products, Comparator.comparing(Product::getName).reversed());
        return products;
    }

    // Phương thức để sắp xếp danh sách sản phẩm theo giá tăng dần
    public List<Product> getProductSortedByPriceAscending() {
        List<Product> products = getAllProduct();
        // Sử dụng phương thức sort của lớp Collections và truyền vào một Comparator
        Collections.sort(products, Comparator.comparing(Product::getPrice));
        return products;
    }

    // Phương thức để sắp xếp danh sách sản phẩm theo giá giảm dần
    public List<Product> getProductSortedByPriceDescending() {
        List<Product> products = getAllProduct();
        // Sử dụng phương thức sort của lớp Collections và truyền vào một Comparator
        Collections.sort(products, Comparator.comparing(Product::getPrice).reversed());
        return products;
    }

    // Phương thức để sắp xếp danh sách sản phẩm theo giá giảm dần
    public List<Product> getProductSortedByNewestProduct() {
        List<Product> products = getAllProduct();
        // Sử dụng phương thức sort của lớp Collections và truyền vào một Comparator
        Collections.sort(products, Comparator.comparing(Product::getId).reversed());
        return products;
    }

    //----------------huy---------
    public void updateProfile(Account a) {
        String query = "update Account\n"
                + "set fullname = ?, email = ?, phone = ?, address= ? where uID = ?";
        DAO dao = new DAO();
        try {
            conn = new DBContext().getConnection(); //mo ket noi toi sql
            ps = conn.prepareStatement(query);//nem cau lenh query sang sql
            ps.setString(1, a.getFullname());
            ps.setString(2, a.getEmail());
            ps.setString(3, a.getPhone());
            ps.setString(4, a.getAddress());
            ps.setInt(5, a.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Account getAccountById(int id) {
        String query = "SELECT * FROM Account WHERE uID = ?";
        try {
            ps = new DBContext().getConnection().prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Account(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7),
                        rs.getInt(8),
                        rs.getDate(9));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updatePassword(int accId, String newPassword) {
        String query = "update Account\n"
                + "set password = ? where uID = ?";
        try {
            conn = new DBContext().getConnection(); //mo ket noi toi sql
            ps = conn.prepareStatement(query);//nem cau lenh query sang sql
            ps.setString(1, newPassword);
            ps.setInt(2, accId);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    //------------Phúc-----------
    public Product getProductByID(String id) {
        String query = "select * from Product where pID = ?";
        DAO dao = new DAO();
        try {
            conn = new DBContext().getConnection(); //mo ket noi toi sql
            ps = conn.prepareStatement(query);//nem cau lenh query sang sql
            ps.setString(1, id);
            rs = ps.executeQuery();//chay cau lenh query, nhan ket qua tra ve
            while (rs.next()) {
                return new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7), dao.getAllSubImageByPID(rs.getInt(1) + ""), dao.getAllSizeByID(rs.getInt(1) + ""), rs.getInt(8), rs.getInt(9));
            }

        } catch (Exception e) {
        }
        return null;
    }

    public List<SizeDetail> getAllSizeByID(String id) {
        List<SizeDetail> list = new ArrayList<>();
        String query = "SELECT *"
                + "FROM SizeDetail SD\n"
                + "JOIN Size S ON SD.sizeID = S.sizeID\n"
                + "WHERE SD.pID = ?";
        try {
            conn = new DBContext().getConnection(); //mo ket noi toi sql
            ps = conn.prepareStatement(query);//nem cau lenh query sang sql
            ps.setString(1, id);
            rs = ps.executeQuery();//chay cau lenh query, nhan ket qua tra ve
            while (rs.next()) {
                list.add(new SizeDetail(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<SizeDetail> getAllSizevalueByPID(String cid) {
        List<SizeDetail> list = new ArrayList<>();
        String query = "SELECT size.sizeValue, sizeDetail.quantity\n"
                + "FROM sizeDetail\n"
                + "JOIN size ON sizeDetail.sizeID = size.sizeID\n"
                + "JOIN Product ON sizeDetail.pID = Product.pID\n"
                + "WHERE Product.pID = ? ";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, cid);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new SizeDetail(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public void updateSizeAndQuantity(int productId, int sizeValue, int quantity) {
        try {
            // Tìm sizeID dựa trên sizevalue
            int sizeId = getSizeIdBySizeValue(sizeValue);

            if (sizeId != -1) {
                // Nếu tìm thấy sizeID, cập nhật dữ liệu trong bảng SizeDetail
                String sizeDetailUpdateQuery = "UPDATE SizeDetail SET quantity = ? WHERE pID = ? AND sizeID = ?";
                PreparedStatement sizeDetailUpdatePs = conn.prepareStatement(sizeDetailUpdateQuery);
                sizeDetailUpdatePs.setInt(1, quantity);
                sizeDetailUpdatePs.setInt(2, productId);
                sizeDetailUpdatePs.setInt(3, sizeId);
                sizeDetailUpdatePs.executeUpdate();

                // Đóng kết nối và tài nguyên
                sizeDetailUpdatePs.close();
            } else {
                System.out.println("Không thể tìm thấy sizeID cho sizevalue đã cho.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //code giỏ hàng đang làm
    public void insertCart(String productID, String accountID, String sizeID) {
        String query = "INSERT INTO Cart(productID, accountID, sizeID)\n"
                + "VALUES (?, ?, ?)";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, productID);
            ps.setString(2, accountID);
            ps.setString(3, sizeID);
            ps.executeUpdate();
        } catch (Exception e) {
        }

    }

    // Duy
    // Get all orders
    public int getTotalOrderCount() {
        int count = 0;
        String query = "SELECT COUNT(*) FROM [Order]";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    // Get all orders
    public List<Order> getAllOrders() {
        List<Order> list = new ArrayList<>();
        String query = "select * from [Project_KiAShop].[dbo].[Order]";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Order(rs.getInt(1),
                        rs.getDate(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getDouble(7),
                        rs.getString(8)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    //get all waiting orders
    public List<Order> getAllWaitingOrders() {
        List<Order> list = new ArrayList<>();
        String query = "select * from [Project_KiAShop].[dbo].[Order] where status ='Waiting'";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Order(rs.getInt(1),
                        rs.getDate(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getDouble(7),
                        rs.getString(8)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    // get all accepted orders
    public List<Order> getAllAcceptedOrders() {
        List<Order> list = new ArrayList<>();
        String query = "select * from [Project_KiAShop].[dbo].[Order] where status ='Accepted'";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Order(rs.getInt(1),
                        rs.getDate(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getDouble(7),
                        rs.getString(8)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    // get total count order
    // get count waiting order
    public int getCountWaitingOrders() {
        int count = 0;
        String query = "SELECT count(*) FROM [Project_KiAShop].[dbo].[Order] where status ='Waiting'";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }
    // get count accepted order

    public int getCountAcceptedOrders() {
        int count = 0;
        String query = "SELECT count(*) FROM [Project_KiAShop].[dbo].[Order] where status ='Accepted'";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    // update status
    public void updateStatusOrder(String id) {
        String query = "Update [Order] set status = 'Accepted' WHERE id = ?";
        try {
            conn = new DBContext().getConnection(); //mo ket noi toi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (Exception ex) {
        }
    }

    //cancel order
    public void cancelOrder(String id) {
        String query = "Update [Order] set status = 'Canceled' WHERE id = ?";
        try {
            conn = new DBContext().getConnection(); //mo ket noi toi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (Exception ex) {
        }
    }

    // get order by id
    public Order getOrderByID(String id) {
        String query = "select * from [Project_KiAShop].[dbo].[Order] where id = ?";
        DAO dao = new DAO();
        try {
            conn = new DBContext().getConnection(); //mo ket noi toi sql
            ps = conn.prepareStatement(query);//nem cau lenh query sang sql
            ps.setString(1, id);
            rs = ps.executeQuery();//chay cau lenh query, nhan ket qua tra ve
            while (rs.next()) {
                return new Order(rs.getInt(1),
                        rs.getDate(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getDouble(7),
                        rs.getString(8));
            }

        } catch (Exception e) {
        }
        return null;
    }

    // search order by phone
    public List<Order> searchOrderByPhoneNumber(String txtSearch) {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM [Project_KiAShop].[dbo].[Order] WHERE sdt LIKE ? ";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, "%" + txtSearch + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Order order = new Order();

                order.setId(rs.getInt("id"));
                order.setDate(rs.getDate("orderDate"));
                order.setAccountID(rs.getInt("accountID"));
                order.setAddress(rs.getString("addressReceive"));
                order.setSdt(rs.getString("sdt"));
                order.setName(rs.getString("name"));
                order.setTotalPrice(rs.getInt("totalPrice"));
                order.setStatus(rs.getString("status"));

                orders.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orders;
    }

    // search order by name
    public List<Order> searchOrderByName(String txtSearch) {
        List<Order> list = new ArrayList<>();
        String query = "SELECT * FROM [Project_KiAShop].[dbo].[Order] WHERE LOWER(name) LIKE LOWER(?)";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, "%" + txtSearch.toLowerCase() + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Order order = new Order();

                order.setId(rs.getInt("id"));
                order.setDate(rs.getDate("orderDate"));
                order.setAccountID(rs.getInt("accountID"));
                order.setAddress(rs.getString("addressReceive"));
                order.setSdt(rs.getString("sdt"));
                order.setName(rs.getString("name"));
                order.setTotalPrice(rs.getInt("totalPrice"));
                order.setStatus(rs.getString("status"));

                list.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public SizeDetail getSizeDetail(int sizeId, int pID) {
        String query = "select * from SizeDetail where sizeID = ? and pID = ?";
        DAO dao = new DAO();
        try {
            conn = new DBContext().getConnection(); //mo ket noi toi sql
            ps = conn.prepareStatement(query);//nem cau lenh query sang sql
            ps.setInt(1, sizeId);
            ps.setInt(2, pID);
            rs = ps.executeQuery();//chay cau lenh query, nhan ket qua tra ve
            while (rs.next()) {
                return new SizeDetail(rs.getInt(1), rs.getInt(2), rs.getInt(3));
            }

        } catch (Exception e) {
        }
        return null;
    }
    
    public List<Order> getOrderHistoryByAccountID(int accID) {
        String query = "SELECT * from [Order] where accountID = ?";
        try {
            List<Order> ls = new ArrayList<>();
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, accID);
            rs = ps.executeQuery();//chay cau lenh query, nhan ket qua tra ve
            DAO dao = new DAO();
            while (rs.next()) {
                Order order = new Order();

                order.setId(rs.getInt("id"));
                order.setDate(rs.getDate("orderDate"));
                order.setAccountID(rs.getInt("accountID"));
                order.setAddress(rs.getString("addressReceive"));
                order.setSdt(rs.getString("sdt"));
                order.setName(rs.getString("name"));
                order.setTotalPrice(rs.getInt("totalPrice"));
                order.setStatus(rs.getString("status"));

                ls.add(order);
            }
            return ls;
        } catch (Exception e) {
        }
        return null;
    }
    
    public List<OrderDetails> getOrderDetailByOrderID(int orderId) {
        String query = "SELECT od.*, p.image, p.pName, c.cName, s.sizevalue from OrderDetails od\n"
                + "left join Product p on od.ProductID = p.pID\n"
                + "left join Category c on p.cID = c.cID\n"
                + "left join Size s on od.sizeID = s.sizeID "
                + "where OrderID = ?";
        try {
            List<OrderDetails> ls = new ArrayList<>();
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, orderId);
            rs = ps.executeQuery();//chay cau lenh query, nhan ket qua tra ve
            DAO dao = new DAO();
            while (rs.next()) {
                ls.add(new OrderDetails(rs.getInt(1),
                        rs.getInt(2),
                        rs.getDouble(3),
                        rs.getInt(4),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getInt(10)));
            }
            return ls;
        } catch (Exception e) {
        }
        return null;
    }

    public static void main(String[] args) {
        DAO dao = new DAO();
    }

}
