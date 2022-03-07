package models;

import java.io.Serializable;

//logout
public class UserLogout implements Serializable {
    private String email;

    public UserLogout(){};
    public UserLogout(String email){
        this.email = email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }
}