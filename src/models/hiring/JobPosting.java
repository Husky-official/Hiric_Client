package models.hiring;

import java.sql.*;

public class JobPosting {
    private Integer id;
    private Integer jobId;
    private Integer userId;
    private String jobTitle;
    private String jobDesc;
    private String jobRequirements;
    private String location;
    private Date startDate;
    private Time duration;
    private Integer salary;

    public JobPosting(Integer id,Integer jobId, Integer userId, String jobTitle, String jobDesc, String jobRequirements, String location, Date startDate, Time duration, Integer salary) {
        this.id = id;
        this.jobId = jobId;
        this.userId = userId;
        this.jobTitle = jobTitle;
        this.jobDesc = jobDesc;
        this.jobRequirements = jobRequirements;
        this.location = location;
        this.startDate = startDate;
        this.duration = duration;
        this.salary = salary;
    }

    public JobPosting() {

    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getJobDesc() {
        return jobDesc;
    }

    public void setJobDesc(String jobDesc) {
        this.jobDesc = jobDesc;
    }

    public String getJobRequirements() {
        return jobRequirements;
    }

    public void setJobRequirements(String jobRequirements) {
        this.jobRequirements = jobRequirements;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Time getDuration() {
        return duration;
    }

    public void setDuration(Time duration) {
        this.duration = duration;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }




    @Override
    public String toString() {
        return "JobPosting{" +
                "jobId=" + jobId +
                "userId=" + userId +
                ", jobTitle=" + jobTitle +
                ", jobDescription='" + jobDesc + '\'' +
                ", jobRequirements=" + jobRequirements +
                ", location=" + location +
                ", startDate=" + startDate +
                ", duration=" + duration +
                ", salary=" + salary +
                '}';
    }
}