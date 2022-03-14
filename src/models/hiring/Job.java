package models.hiring;

public class Job {
    public Integer id;
    public String jobTitle;

    public Job() {

    }

    public Job(Integer id, String jobTitle) {
        this.id = id;
        this.jobTitle = jobTitle;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
}
