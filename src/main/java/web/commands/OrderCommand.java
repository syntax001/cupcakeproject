package web.commands;

import business.exceptions.UserException;
import business.services.OrderFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OrderCommand extends CommandUnprotectedPage {
    private OrderFacade orderFacade;

    public OrderCommand(String pageToShow) {
        super(pageToShow);
        orderFacade = new OrderFacade();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {
        int top = Integer.parseInt(request.getParameter("top"));
        int bottom = Integer.parseInt(request.getParameter("bottom"));
        int amount = Integer.parseInt(request.getParameter("amount"));

        request.setAttribute("top", top);
        request.setAttribute("bottom", bottom);
        request.setAttribute("amount", amount);

        double[] prices = orderFacade.calcPrices(top, bottom, amount);
        request.setAttribute("totalPrice", prices[0]);
        request.setAttribute("perCupcakePrice", prices[1]);

        return null; // TODO: Tilføj side den skal retuneres til (Kvittering side)
    }
}
