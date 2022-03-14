package models;

import java.util.Date;

public class EmployeeDashboard {
    private Date dateToday;
    private int messages;
    private int notifications;
    private String employeeName;
    private String employeeEmail;
    private String employeePost;
    private int jobsDone;
    private String scheduledInterviews;
    private String jobStatus;
    private String experience;
    private String rating;
    private String comments;
    private int amount;
    private String paymentMethod;

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

    public String getEmployeePost() {
        return employeePost;
    }

    public void setEmployeePost(String employeePost) {
        this.employeePost = employeePost;
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

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
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

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public EmployeeDashboard(Date dateToday, int messages, int notifications, String employeeName, String employeeEmail, String employeePost, int jobsDone, String scheduledInterviews, String jobStatus, String experience, String rating, String comments, int amount, String paymentMethod) {
        this.dateToday = dateToday;
        this.messages = messages;
        this.notifications = notifications;
        this.employeeName = employeeName;
        this.employeeEmail = employeeEmail;
        this.employeePost = employeePost;
        this.jobsDone = jobsDone;
        this.scheduledInterviews = scheduledInterviews;
        this.jobStatus = jobStatus;
        this.experience = experience;
        this.rating = rating;
        this.comments = comments;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
    }

    @Override
    public String toString() {
        return "EmployeeDashboard{" +
                "dateToday=" + dateToday +
                ", messages=" + messages +
                ", notifications=" + notifications +
                ", employeeName='" + employeeName + '\'' +
                ", employeeEmail='" + employeeEmail + '\'' +
                ", employeePost='" + employeePost + '\'' +
                ", jobsDone=" + jobsDone +
                ", scheduledInterviews='" + scheduledInterviews + '\'' +
                ", jobStatus='" + jobStatus + '\'' +
                ", experience='" + experience + '\'' +
                ", rating='" + rating + '\'' +
                ", comments='" + comments + '\'' +
                ", amount=" + amount +
                ", paymentMethod='" + paymentMethod + '\'' +
                '}';
    }
}