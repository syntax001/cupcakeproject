package web.commands;

import business.entities.Order;
import business.entities.User;
import business.exceptions.UserException;
import business.services.OrderFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class MyOrdersCommand extends CommandProtectedPage {
    private OrderFacade orderFacade;

    public MyOrdersCommand(String pageToShow, String role) throws UserException {
        super(pageToShow, role);
        orderFacade = new OrderFacade(database);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        List<Order> orders = orderFacade.getOrders(user);

        request.setAttribute("orders", orders);

        return pageToShow;
    }
}
