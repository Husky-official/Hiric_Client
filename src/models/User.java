package models;

/**
 * @author : Shumbusho David
 * @description : User model for login
 */

import java.io.Serializable;

public class User implements Serializable {

    private String password;
    private String email;

    public User(){};
    public User(String password, String email){
        this.password = password;
        this.email = email;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "User{" +
                "password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}