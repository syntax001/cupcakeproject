package web.commands;

import business.entities.User;
import business.exceptions.UserException;
import business.services.OrderFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class OrderCommand extends CommandUnprotectedPage {
    private OrderFacade orderFacade;

    public OrderCommand(String pageToShow) {
        super(pageToShow);
        orderFacade = new OrderFacade(database);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {
        int top = Integer.parseInt(request.getParameter("topping"));
        int bottom = Integer.parseInt(request.getParameter("bottom"));
        int amount = Integer.parseInt(request.getParameter("amount"));

        request.setAttribute("top", top);
        request.setAttribute("bottom", bottom);
        request.setAttribute("amount", amount);

        double[] prices = orderFacade.calcPrices(top, bottom, amount);
        request.setAttribute("totalPrice", prices[0]);
        request.setAttribute("perCupcakePrice", prices[1]);
        request.setAttribute("toppingPrice", prices[2]);
        request.setAttribute("bottomPrice", prices[3]);

        String [] topBotNames = orderFacade.getTopBotNames(top, bottom);
        request.setAttribute("topping", topBotNames[0]);
        request.setAttribute("bottom", topBotNames[1]);

        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        if(user != null) {
            orderFacade.uploadOrder(user, top, bottom, amount);
        }

        return pageToShow;
    }
}
