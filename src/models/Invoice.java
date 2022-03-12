package models;


public class Invoice {
    private Long invoiceNumber;
    private int jobId;
    public Invoice(){}
    public Invoice(Long invoiceNumber, int jobId, int userId) {
        this.invoiceNumber = invoiceNumber;
        this.jobId = jobId;
        this.userId = userId;
    }

    private int userId;

    public Long getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(Long invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }


}
