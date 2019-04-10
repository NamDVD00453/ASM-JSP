package com.assignmentjavaweb.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class User {
    private int id;
    private String username;
    private String password;
    private String salt;
    private int role;
    private int status;

    HashMap<String, ArrayList<String>> errors = new HashMap<>();

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, String salt, int role, int status) {
        this.username = username;
        this.password = password;
        this.salt = salt;
        this.role = role;
        this.status = status;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isValid() {
        validate();
        return this.errors.size() == 0;
    }


    private static boolean isNumber(String string) {
        return string.matches(".*[0-9].*");
    }

    private static boolean isString(String string) {
        return string.matches(".*[a-zA-Z].*");
    }


    private void validate() {
        if (this.errors == null) {
            this.errors = new HashMap<>();
        }
        ArrayList<String> userNameError = this.errors.get("username");
        ArrayList<String> passwordError = this.errors.get("password");

        if (userNameError == null) {
            userNameError = new ArrayList<>();
        }
        if (this.username == null || this.username.length() == 0) {
            userNameError.add("Username is required!!!");
        }
        if (this.username.length() < 2 || this.username.length() > 30) {
            userNameError.add("Username must be between 2 and 30 character");
        }
        if (userNameError.size() > 0) {
            this.errors.put("username", userNameError);
        }

        if (passwordError == null) {
            passwordError = new ArrayList<>();
        }
        if (this.password == null || this.password.length() == 0) {
            passwordError.add("Password is required!!!");

        }
        if (this.password.length() < 2 || this.password.length() > 30) {
            passwordError.add("Password must be between 6 and 8 character");
        }
        if (!isNumber(this.password) && !isString(this.password)) {
            passwordError.add("Password must have character and number");
        }

        if (passwordError.size() > 0) {
            this.errors.put("password", passwordError);
        }
    }

    public HashMap<String, ArrayList<String>> getErrors() {
        return this.errors;
    }
}
