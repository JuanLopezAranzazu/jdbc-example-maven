package org.example;

import controller.UserController;
import controller.UserControllerBeta;
import model.User;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        /*try {
            Connection conn=new ConnectionFactory().getConnection();
            System.out.println("Programa terminado");
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }*/

        UserController userController=new UserController();
        List<User> data=userController.getUsers();
        for(User user: data){
            System.out.println(user.getInfo());
        }
    }
}