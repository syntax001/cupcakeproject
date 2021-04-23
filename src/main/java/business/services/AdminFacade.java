package business.services;

import business.entities.User;
import business.exceptions.UserException;
import business.persistence.AdminMapper;
import business.persistence.Database;

import java.util.List;

public class AdminFacade {
    private AdminMapper adminMapper;

    public AdminFacade(Database database) {
        adminMapper = new AdminMapper(database);
    }

    public List<User> getUsers() throws UserException {
        List<User> users = adminMapper.getAllUsers();

        return users;
    }

    public User getUser(int userId) throws UserException {
        User user = adminMapper.getUser(userId);
        return user;
    }
}
