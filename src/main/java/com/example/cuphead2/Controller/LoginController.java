package com.example.cuphead2.Controller;


import com.example.cuphead2.Models.User;
import com.example.cuphead2.Models.UserDatabase;

public class LoginController {

    public void register(String username, String password) {
        User newUser = new User(username, password);
        UserDatabase.addUser(newUser);
    }

    public void login(String username, String password) throws Exception {
        User user = new User(username, password);
        user = UserDatabase.getUserFromUsers(user);
        if (user == null)
            throw new Exception();
        UserDatabase.setCurrentUser(user);
    }

    public void logout() {
        UserDatabase.setCurrentUser(null);
    }

}