/*
* @Author: MPANO Christian
* */
package views.hiring;

import models.hiring.JobApplication;
import models.hiring.JobPosting;

import java.util.ArrayList;
import java.util.Scanner;
import static utils.MessagePrinter.printConsoleMessage;

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
                return;
            }
            JobApplication[] jobApplications = jobApplicationView.getApplicationsForJob(jobPostId);
            System.out.println("first name lastName email payment method resume referenceName certificate");
            for(int i = 0; i < jobApplications.length; i++) {

            }
            String leftAlignFormat = "| %-4d | %-13s | %-13s | %-23s | %18s | %17s |%n";
            System.out.format(" Number| FirstName     | LastName      | Email                   | Payment method     | referenceName     |%n");
            System.out.println("+---------------------------------------------------------------------------------------------------------+");
            for (int i = 0; i < jobApplications.length; i++) {
                System.out.format(leftAlignFormat, 1,jobApplications[i].firstName, jobApplications[i].lastName, jobApplications[i].email, jobApplications[i].paymentMethod, jobApplications[i].referenceName);
            }
            System.out.format("+-----------------+-------------+%n");
            System.out.println("How many job applications do you want to shortlist?");
            int shortLists = scanner.nextInt();

        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
