package web.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyOrdersCommand extends CommandProtectedPage {
    public MyOrdersCommand(String pageToShow, String role) {
        super(pageToShow, role);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        if(!role.equals("customers") || !role.equals("employee")) {
            return "loginpage";
        }



        return pageToShow;
    }
}
