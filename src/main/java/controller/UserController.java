package controller;

import dao.UserDAO;
import factory.ConnectionFactory;
import model.User;
import java.util.List;

public class UserController {
    private UserDAO userDAO;

    public UserController(){
        var connectionFactory = new ConnectionFactory();
        this.userDAO = new UserDAO(connectionFactory.getConnection());
    }

    public void createUser(User newUser) {
        userDAO.createUser(newUser);
    }

    public List<User> getUsers() {
        return userDAO.getUsers();
    }

    public int updateUser(Integer userId,String firstName,String lastName,Boolean isActive) {
        return userDAO.updateUser(userId,firstName,lastName,isActive);
    }

    public int deleteUser(Integer userId) {
        return userDAO.deleteUser(userId);
    }
}

