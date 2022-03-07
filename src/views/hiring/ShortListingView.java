package views.hiring;

import models.hiring.JobPosting;

import java.util.ArrayList;
import java.util.Scanner;

public class ShortListingView {
    public static void mainMethod() {
        try{
            System.out.println("SELECT THE JOB POST YOU WANT TO SHOT LIST FOR");
            JobPosting[] jobPostings;
            jobPostings = JobPostingView.getJobPosts();
            for(int i = 0; i < jobPostings.length; i++) {
                System.out.println(i + 1 + " : " + jobPostings[i].jobDesc);
            }
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            int jobPostId = 0;
            for(int i = 0; i < jobPostings.length; i++) {
                if(i + 1 == choice) {
                    jobPostId = jobPostings[i].id;
                }
            }
            if(jobPostId == 0) {
                System.out.println("invalid job selected");
            }else{

                jobApplicationView.getApplicationsForJob(jobPostId);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
