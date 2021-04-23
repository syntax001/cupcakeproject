package web.commands;

import business.entities.User;
import business.exceptions.UserException;
import business.services.AdminFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AdminCommand extends CommandProtectedPage {
    private AdminFacade adminFacade;

    public AdminCommand(String pageToShow, String role) {
        super(pageToShow, role);
        adminFacade = new AdminFacade(database);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {
        List<User> users = adminFacade.getUsers();
        request.setAttribute("users", users);

        return pageToShow;
    }
}
