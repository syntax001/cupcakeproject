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
            <h2>Olsker's Cupcake Factory</h2>

            <div style="margin-top: 3em;margin-bottom: 3em;">
                <form method="post" action="${pageContext.request.contextPath}/fc/cupcakeOrderCommand">
                    <div class="form-group">
                        <label for="topping">Toppings:</label>
                        <select name="topping" id="topping">
                            <option value="0">Chocolate</option>
                            <option value="1">Blueberry</option>
                            <option value="2">Rasberry</option>
                            <option value="3">Crispy</option>
                            <option value="4">Strawberry</option>
                            <option value="5">Rum/Raisin</option>
                            <option value="6">Orange</option>
                            <option value="7">Lemon</option>
                            <option value="8">Blue cheese</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="bottom">Bottoms:</label>
                        <select name="bottom" id="bottom">
                            <option value="0">Chocolate</option>
                            <option value="1">Vanilla</option>
                            <option value="2">Nutmeg</option>
                            <option value="3">Pistacio</option>
                            <option value="4">Almond</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="amount">Amount:</label>
                        <select name="amount" id="amount">
                            <c:forEach begin="1" end="100" varStatus="loop">
                                <option value="${loop.index}">${loop.index}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <input type="submit" value="Bestil Cupcake">
                </form>
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