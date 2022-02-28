package models;

/**
 * @author : DABAGIRE Valens
 * @description : User model
 */

import java.io.Serializable;

public class User implements Serializable {

    private String password;
    private String userName;

    public User(){};
    public User(String password, String name){
        this.password = password;
        this.userName = name;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }
}
