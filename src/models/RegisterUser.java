/**
 * @author UWENAYO Alain Pacifique
 * @description  This class is used to register a new user
 * @date  3/6/2022
 */
package models;

import java.io.Serializable;
import java.util.Date;

/**
 * The type Register user.
 */
public class RegisterUser implements Serializable {
    /**
     * The User id.
     */
    private int userId;
    /**
     * The First name.
     */
    private String firstName;
    /**
     * The Last name.
     */
    private String lastName;
    /**
     * The Email.
     */
    private String email;
    /**
     * The Password.
     */
    private String password;
    /**
     * The Gender.
     */
    private UserUtils.UserGender gender;
    /**
     * The Role.
     */
    private UserUtils.UserRoles role;
    /**
     * The Dob.
     */
    private Date dob;

    /**
     * Instantiates a new Register user.
     */
    public RegisterUser(){}

    /**
     * Instantiates a new Register user.
     *
     * @param userId    the user id
     * @param firstName the first name
     * @param lastName  the last name
     * @param email     the email
     * @param password  the password
     * @param gender    the gender
     * @param role      the role
     * @param dob       the dob
     */
    public RegisterUser(int userId, String firstName, String lastName, String email, String password, UserUtils.UserGender gender, UserUtils.UserRoles role, Date dob) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.role = role;
        this.dob = dob;
    }

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public int getUserId() {
            return userId;
        }

    /**
     * Sets user id.
     *
     * @param userId the user id
     */
    public void setUserId(int userId) {
            this.userId = userId;
        }

    /**
     * Gets first name.
     *
     * @return the first name
     */
    public String getFirstName() {
            return firstName;
        }

    /**
     * Sets first name.
     *
     * @param firstName the first name
     */
    public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

    /**
     * Gets last name.
     *
     * @return the last name
     */
    public String getLastName() {
            return lastName;
        }

    /**
     * Sets last name.
     *
     * @param lastName the last name
     */
    public void setLastName(String lastName) {
            this.lastName = lastName;
        }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
            return email;
        }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
            this.email = email;
        }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
            return password;
        }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
            this.password = password;
        }

    /**
     * Gets gender.
     *
     * @return the gender
     */
    public UserUtils.UserGender getGender() {
            return gender;
        }

    /**
     * Sets gender.
     *
     * @param gender the gender
     */
    public void setGender(UserUtils.UserGender gender) {
            this.gender = gender;
        }

    /**
     * Gets role.
     *
     * @return the role
     */
    public UserUtils.UserRoles getRole() {
            return role;
        }

    /**
     * Sets role.
     *
     * @param role the role
     */
    public void setRole(UserUtils.UserRoles role) {
            this.role = role;
        }

    /**
     * Gets dob.
     *
     * @return the dob
     */
    public Date getDob() {
            return dob;
        }

    /**
     * Sets dob.
     *
     * @param dob the dob
     */
    public void setDob(Date dob) {
            this.dob = dob;
        }
}
