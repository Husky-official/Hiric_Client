package models.hiring;

public class ShortlistedEmployees {
    private int eventId;
    private int userId;
    private String referenceName;

    public ShortlistedEmployees() {};

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getReferenceName() {
        return referenceName;
    }

    public void setReferenceName(String referenceName) {
        this.referenceName = referenceName;
    }

    public ShortlistedEmployees(int userId, String referenceName){
        this.userId = userId;
        this.referenceName = referenceName;
    }
}

