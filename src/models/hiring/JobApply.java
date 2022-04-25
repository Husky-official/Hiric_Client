package models.hiring;

public class JobApply {
    public  Integer id;
    public  Integer userId;
    public  Integer jobPostId;
    public  Integer locationId;
    public  String  paymentMethod;
    public  String referenceName;
    public  String referencePhone;
    public  String resume;
    public  String certificate;
    public  String status;

    public JobApply(){};
    public JobApply(int id, int jobPostId, int userId, int locationId, String paymentMethod, String referenceName, String referencePhone, String resume, String certificate,String status){
        this.id=id;
        this.jobPostId =jobPostId;
        this.userId=userId;
        this.locationId =locationId;
        this.paymentMethod=paymentMethod;
        this.referenceName=referenceName;
        this.referencePhone=referencePhone;
        this.resume=resume;
        this.certificate=certificate;
        this.status=status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getJobPostId() {
        return jobPostId;
    }

    public void setJobPostId(Integer jobPostId) {
        this.jobPostId = jobPostId;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public String getReferenceName() {
        return referenceName;
    }

    public void setReferenceName(String referenceName) {
        this.referenceName = referenceName;
    }

    public String getReferencePhone() {
        return referencePhone;
    }

    public void setReferencePhone(String referencePhone) {
        this.referencePhone = referencePhone;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
