package models;

import java.io.Serializable;
import java.util.Date;

public class RegisterUser implements Serializable {
    private int userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private UserUtils.UserGender gender;
    private UserUtils.UserRoles role;
    private Date dob;
    public RegisterUser(){}
    public RegisterUser(int userId, String firstName, String lastName, String email, String password, UserUtils.UserGender gender, UserUtils.UserRoles role, Date dob) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.role = role;
    }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public UserUtils.UserGender getGender() {
            return gender;
        }

        public void setGender(UserUtils.UserGender gender) {
            this.gender = gender;
        }

        public UserUtils.UserRoles getRole() {
            return role;
        }

        public void setRole(UserUtils.UserRoles role) {
            this.role = role;
        }

        public Date getDob() {
            return dob;
        }

        public void setDob(Date dob) {
            this.dob = dob;
        }
}
