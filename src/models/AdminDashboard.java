package models;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

/**
 * @author : BWIZA Cyndy Nina - Admin dashboard
 * @description: : format of what will be displayed to the dashboard
 */

public class AdminDashboard {

    private Date dateToday;
    private int messages;
    private int notifications;
    private String employerReviews;
    private String employeeReviews;
    private Date publishedDate;
    private int employeesRecentlyAdded;
    private String employeeEmail;
    private String employeePost;
    private String desiredSchedule;
    private int employersRecentlyAdded;
    private String employerEmail;
    private String employerDesiredPost;

    public AdminDashboard() {
    }

    public Date getDateToday() {
        return dateToday;
    }

    public void setDateToday(Date dateToday) {
        this.dateToday = dateToday;
    }

    public int getMessages() {
        return messages;
    }

    public void setMessages(int messages) {
        this.messages = messages;
    }

    public int getNotifications() {
        return notifications;
    }

    public void setNotifications(int notifications) {
        this.notifications = notifications;
    }

    public String getEmployerReviews() {
        return employerReviews;
    }

    public void setEmployerReviews(String employerReviews) {
        this.employerReviews = employerReviews;
    }

    public String getEmployeeReviews() {
        return employeeReviews;
    }

    public void setEmployeeReviews(String employeeReviews) {
        this.employeeReviews = employeeReviews;
    }

    public Date getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
    }

    public int getEmployeesRecentlyAdded() {
        return employeesRecentlyAdded;
    }

    public void setEmployeesRecentlyAdded(int employeesRecentlyAdded) {
        this.employeesRecentlyAdded = employeesRecentlyAdded;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public String getEmployeePost() {
        return employeePost;
    }

    public void setEmployeePost(String employeePost) {
        this.employeePost = employeePost;
    }

    public String getDesiredSchedule() {
        return desiredSchedule;
    }

    public void setDesiredSchedule(String desiredSchedule) {
        this.desiredSchedule = desiredSchedule;
    }

    public int getEmployersRecentlyAdded() {
        return employersRecentlyAdded;
    }

    public void setEmployersRecentlyAdded(int employersRecentlyAdded) {
        this.employersRecentlyAdded = employersRecentlyAdded;
    }

    public String getEmployerEmail() {
        return employerEmail;
    }

    public void setEmployerEmail(String employerEmail) {
        this.employerEmail = employerEmail;
    }

    public String getEmployerDesiredPost() {
        return employerDesiredPost;
    }

    public void setEmployerDesiredPost(String employerDesiredPost) {
        this.employerDesiredPost = employerDesiredPost;
    }


    public AdminDashboard(Date dateToday, int messages, int notifications, String employerReviews,
                          String employeeReviews, Date publishedDate, int employeesRecentlyAdded, int employersRecentlyAdded,
                          String employeeEmail, String employeePost, String desiredSchedule, String employerEmail,
                          String employerDesiredPost) {
        this.dateToday = dateToday;
        this.messages = messages;
        this.notifications = notifications;
        this.employerReviews = employerReviews;
        this.employeeReviews = employeeReviews;
        this.publishedDate = publishedDate;
        this.employeesRecentlyAdded = employeesRecentlyAdded;
        this.employersRecentlyAdded = employersRecentlyAdded;
        this.desiredSchedule = desiredSchedule;
        this.employeePost = employeePost;
        this.employeeEmail = employeeEmail;
        this.employerEmail = employerEmail;
        this.employerDesiredPost = employerDesiredPost;
    }

    @Override
    public String toString() {
        return "AdminDashboard{" +
                ", dateToday=" + dateToday +
                ", messages='" + messages + '\'' +
                ", notifications='" + notifications + '\'' +
                ", employerReviews='" + employerReviews + '\'' +
                ", employeeReviews='" + employeeReviews + '\'' +
                ", publishedDate=" + publishedDate +
                ", employeesRecentlyAdded=" + employeesRecentlyAdded +
                ", employeeEmail='" + employeeEmail + '\'' +
                ", employeePost='" + employeePost + '\'' +
                ", desiredSchedule='" + desiredSchedule + '\'' +
                ", employersRecentlyAdded=" + employersRecentlyAdded +
                ", employerEmail='" + employerEmail + '\'' +
                ", employerDesiredPost='" + employerDesiredPost + '\'' +
                '}';
    }

}
