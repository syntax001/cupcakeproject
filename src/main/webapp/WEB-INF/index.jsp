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
            <!-- TODO: Pull toppings og bottoms fra databse -->
            <div style="margin-top: 3em;margin-bottom: 3em;">
                <c:if test="${sessionScope.user != null}">
                <form method="post" action="${pageContext.request.contextPath}/fc/cupcakeOrderCommand">

                    </c:if>

                    <c:if test="${sessionScope.user == null}">
                    <form method="post" action="${pageContext.request.contextPath}/fc/loginpage">

                        </c:if>
                        <div class="form-group">
                            <label for="topping">Toppings:</label>
                            <select name="topping" id="topping">
                                <option value="1">Chocolate</option>
                                <option value="2">Blueberry</option>
                                <option value="3">Rasberry</option>
                                <option value="4">Crispy</option>
                                <option value="5">Strawberry</option>
                                <option value="6">Rum/Raisin</option>
                                <option value="7">Orange</option>
                                <option value="8">Lemon</option>
                                <option value="9">Blue cheese</option>
                            </select>
                        </div>

                        <div class="form-group">
                            <label for="bottom">Bottoms:</label>
                            <select name="bottom" id="bottom">
                                <option value="1">Chocolate</option>
                                <option value="2">Vanilla</option>
                                <option value="3">Nutmeg</option>
                                <option value="4">Pistacio</option>
                                <option value="5">Almond</option>
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