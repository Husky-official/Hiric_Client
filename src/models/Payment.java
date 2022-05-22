package models;

import java.io.Serializable;

/**
 *@author : DUSHIME Bill Benon - Billing Model
 *@description: : payment model
 */

public class Payment implements Serializable {

    private Long id;
    private Long jobId;
    private Double originalAmount;
    private Integer paymentMethod;
    private Double reducedAmount;
    private String dateOfPayment;
    private Long employeeId;
    private Long employerId;

    public Payment() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public Double getOriginalAmount() {
        return originalAmount;
    }

    public void setOriginalAmount(Double originalAmount) {
        this.originalAmount = originalAmount;
    }

    public Integer getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(Integer paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Double getReducedAmount() {
        return reducedAmount;
    }

    public void setReducedAmount(Double reducedAmount) {
        this.reducedAmount = reducedAmount;
    }

    public String getDateOfPayment() {
        return dateOfPayment;
    }

    public void setDateOfPayment(String dateOfPayment) {
        this.dateOfPayment = dateOfPayment;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getEmployerId() {
        return employerId;
    }

    public void setEmployerId(Long employerId) {
        this.employerId = employerId;
    }

    public Payment(Long jobId, Double originalAmount, Integer paymentMethod, Double reducedAmount, String dateOfPayment, Long employeeId, Long employerId) {
        this.jobId = jobId;
        this.originalAmount = originalAmount;
        this.paymentMethod = paymentMethod;
        this.reducedAmount = reducedAmount;
        this.dateOfPayment = dateOfPayment;
        this.employeeId = employeeId;
        this.employerId = employerId;
    }

    public Payment(Long id, Long jobId, Double originalAmount, Integer paymentMethod, Double reducedAmount, String dateOfPayment, Long employeeId, Long employerId) {
        this.id = id;
        this.jobId = jobId;
        this.originalAmount = originalAmount;
        this.paymentMethod = paymentMethod;
        this.reducedAmount = reducedAmount;
        this.dateOfPayment = dateOfPayment;
        this.employeeId = employeeId;
        this.employerId = employerId;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", jobId=" + jobId +
                ", originalAmount=" + originalAmount +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", reducedAmount=" + reducedAmount +
                ", dateOfPayment=" + dateOfPayment +
                ", employeeId=" + employeeId +
                ", employerId=" + employerId +
                '}';
    }
}
