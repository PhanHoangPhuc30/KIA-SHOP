<%-- 
    Document   : ManageOrder
    Created on : Feb 29, 2024, 10:09:26 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <table>
            <thead>
                <tr>
                        <th>1</th>
                        <th>2</th>
                        <th>3</th>
                        <th>4</th>
                        <th>5</th>
                        <th>6</th>
                        <th>7</th>
                    </tr>
            </thead>
            
            <tbody>
                <c:forEach var="order" items="${listO}">
                        <tr>
                            <td>${order.id}</td>
                            <td>${order.date}</td>
                            <td>${order.accountID}</td>
                            <td>${order.address}</td>
                            <td>${order.sdt}</td>
                            <td>${order.name}</td>
                            <td>${order.totalPrice}</td>
                        </tr>
                    </c:forEach>
            </tbody>
        </table>
    </body>
</html>
