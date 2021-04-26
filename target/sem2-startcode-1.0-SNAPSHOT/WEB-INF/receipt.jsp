<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>

    <jsp:attribute name="header">
         Home
    </jsp:attribute>

    <jsp:attribute name="footer">
        <c:set var="addHomeLink" value="${false}" scope="request"/>
    </jsp:attribute>

    <jsp:body>
        <div>
        <c:if test="${requestScope.newBalance != null}">
            <h2>Receipt for ordered cupcakes</h2>
            <p>Your new balance is $${requestScope.newBalance}</p>
        </c:if>

        <c:if test="${requestScope.moneyMissing != null}">
            <h2>Your balance is too low!</h2>
            <p>You are missing $${requestScope.moneyMissing}</p>
            <a href="${pageContext.request.contextPath}/fc/index">Tilbage til forsiden</a>
        </c:if>

            <div>
                <table>
                    <tr>
                        <th>Order</th>
                        <th>Accessories</th>
                        <th>Amount</th>
                        <th>Per Unit</th>
                        <th>Total Price</th>
                    </tr>
                    <tr>
                        <td>
                            <p>Cupcake</p>
                        </td>
                        <td>
                            <p>(Topping: ${requestScope.topping}: $${requestScope.toppingPrice},
                                Bottom: ${requestScope.bottom}: $${requestScope.bottomPrice})</p>
                        </td>
                        <td>
                            <p>${requestScope.amount}</p>
                        </td>
                        <td>
                            <p>$${requestScope.perCupcakePrice}</p>
                        </td>
                        <td>
                            <p>$${requestScope.totalPrice}</p>
                        </td>
                    </tr>
                </table>

                <p><b>Total price: $${requestScope.totalPrice}</b></p>
            </div>
           <%-- <c:if test="${sessionScope.role == 'employee' }">
                <p style="font-size: larger">This is what you can do,
                    since your are logged in as an employee</p>
                 <p><a href="fc/employeepage">Employee Page</a>
             </c:if>

             <c:if test="${sessionScope.role == 'customer' }">
                <p style="font-size: larger">This is what you can do, since your
                    are logged in as a customer</p>
                <p><a href="fc/customerpage">Customer Page</a>
            </c:if> --%>
        </div>

    </jsp:body>
</t:genericpage>