package views.hiring;

import models.hiring.JobPosting;

import java.util.ArrayList;

public class ShortListingView {
    public static void mainMethod() {
        try{
            System.out.println("SELECT THE JOB POST YOU WANT TO SHOT LIST FOR");
            ArrayList<JobPosting> jobPostings = new ArrayList<JobPosting>();
            jobPostings = JobPostingView.getJobPosts();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
