package com.example.cuphead2.Models;

public class User {
    private String username;
    private String password;
    private int highScore;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }


    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.highScore = 0;
    }

    @Override
    public String toString() {
        return "username : " + username + "\n" +
                "password : " + password + "\n" +
                "highscore : " + highScore;
    }

}