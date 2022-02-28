package models.hiring;
/*
 * author: Gashugi Aderline
 * desc: This is a model for a  job post.
 *
 */
public class JobPosting {
    private String jobTitle;
    private String jobDescription;
    private String jobRequirements;
    private String location;
    private String startDate;
    private String duration;
    private int salary;
    public JobPosting(){}

    public JobPosting(String jobTitle, String jobDescription, String jobRequirements, String location, String startDate, String duration, int salary){
        this.jobTitle = jobTitle;
        this.jobDescription = jobDescription;
        this.jobRequirements = jobRequirements;
        this.location = location;
        this.startDate = startDate;
        this.duration = duration;
        this.salary = salary;
    }
    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "JobPosting{" +
                ", jobTitle=" + jobTitle +
                ", jobDescription='" + jobDescription + '\'' +
                ", jobRequirements=" + jobRequirements +
                ", location=" + location +
                ", startDate=" + startDate +
                ", duration=" + duration +
                ", salary=" + salary +
                '}';
    }
}
