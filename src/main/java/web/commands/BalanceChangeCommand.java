package web.commands;

import business.entities.User;
import business.exceptions.UserException;
import business.services.AdminFacade;
import business.services.UserFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BalanceChangeCommand extends CommandProtectedPage {
    private AdminFacade adminFacade;
    private UserFacade userFacade;

    public BalanceChangeCommand(String pageToShow, String role) {
        super(pageToShow, role);
        adminFacade = new AdminFacade(database);
        userFacade = new UserFacade(database);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        int balance = Integer.parseInt(request.getParameter("balance"));
        User user = adminFacade.getUser(userId);

        userFacade.updateUserBalance(user, balance);

        return "adminpage";
    }
}
