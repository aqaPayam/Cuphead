package com.example.cuphead2.Models;

import java.util.ArrayList;
import java.util.Objects;

public class UserDatabase {
    static private final ArrayList<User> users = new ArrayList<>();
    static private User currentUser;


    static public boolean isUsernameDuplicate(String username) {
        for (User user : users) {
            if (Objects.equals(user.getUsername(), username))
                return true;
        }
        return false;
    }

    static public User getUserFromUsers(User user) {
        for (User user1 : users) {
            if (Objects.equals(user1.getUsername(), user.getUsername()) &&
                    Objects.equals(user1.getPassword(), user.getPassword()))
                return user1;
        }
        return null;
    }

    static public User findUserByUsername(String username) {
        for (User user : users) {
            if (Objects.equals(user.getUsername(), username))
                return user;
        }
        return null;
    }

    static public User findUserByPassword(String password) {
        for (User user : users) {
            if (Objects.equals(user.getPassword(), password))
                return user;
        }
        return null;
    }

    static public boolean isUsernameAndPasswordTrue(User user) {
        for (User user1 : users) {
            if (Objects.equals(user1.getUsername(), user.getUsername()) &&
                    Objects.equals(user1.getPassword(), user.getPassword()))
                return true;
        }
        return false;
    }

    static public User getCurrentUser() {
        return currentUser;
    }

    static public void setCurrentUser(User currentUser) {
        UserDatabase.currentUser = currentUser;
    }

    static public ArrayList<User> getUsers() {
        return users;
    }

    static public void addUser(User user) {
        users.add(user);
    }

}