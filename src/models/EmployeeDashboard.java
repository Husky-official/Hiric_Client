package models;

import interfaces.MessageTypes;
import views.BillingView;
import views.UserView;
import static utils.MessagePrinter.printConsoleMessage;

import java.io.IOException;
import java.util.Scanner;

public class EmployeeDashboard {
    private String employeeName;
    private String employeeEmail;
    private int jobsDone;
    private String scheduledInterviews;
    private String jobStatus;
    private String experience;
    private String rating;
    private String comments;
    private int amount;
    private String paymentMethod;
    private String messages;
    private String notifications;

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }

    public String getNotifications() {
        return notifications;
    }

    public void setNotifications(String notifications) {
        this.notifications = notifications;
    }

    public int getJobsDone() {
        return jobsDone;
    }

    public void setJobsDone(int jobsDone) {
        this.jobsDone = jobsDone;
    }

    public String getScheduledInterviews() {
        return scheduledInterviews;
    }

    public void setScheduledInterviews(String scheduledInterviews) {
        this.scheduledInterviews = scheduledInterviews;
    }

    public String getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(String jobStatus) {
        this.jobStatus = jobStatus;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public static void main(String[] args) throws IOException {
        printConsoleMessage(MessageTypes.NORMAL, false, "\t\t\t||------------------      EMPLOYEE DASHBOARD      -------------------||");
        printConsoleMessage(MessageTypes.NORMAL, false, "\t\t\t||-------------------------------------------------------------------||");

    }
}