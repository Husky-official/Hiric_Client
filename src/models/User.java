package models;

/**
 * @author : Shumbusho David
 * @description : User model for login and logout
 */

import java.io.Serializable;

public class User implements Serializable {

    private String password;
    private String email;
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


    public User(){};
    public User(String password, String email,String token){
        this.password = password;
        this.email = email;
        this.token=token;
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
