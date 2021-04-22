<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>
    <jsp:attribute name="header">
         My orders
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>

    <jsp:body>
        <h1>Your orders:</h1>
        <table>
            <thead>
            <th>Order date</th>
            <th>Order number</th>
            <th>Cupcake</th>
            <th>Amount</th>
            <th>Per unit</th>
            <th>Total Price</th>
            </thead>
            <c:forEach var="orders" items="${requestScope.orders}">
                <tr>
                    <td>${orders.orderDate}</td>
                    <td>${orders.orderId}</td>
                    <td>(Topping: ${orders.cupcakeBotTopNames[0]}: $${orders.cupcakePrices[2]},
                        Bottom: ${orders.cupcakeBotTopNames[1]}: $${orders.cupcakePrices[3]})</td>
                    <td>${orders.amount}</td>
                    <td>${orders.cupcakePrices[1]}</td>
                    <td>${orders.cupcakePrices[0]}</td>
                </tr>
            </c:forEach>
        </table>
    </jsp:body>

</t:genericpage>

