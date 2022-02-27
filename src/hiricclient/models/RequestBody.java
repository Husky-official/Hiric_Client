package hiricclient.models;

public class RequestBody {
    private String url;
    private String action;
    private Object object;

    public RequestBody(){};
    public RequestBody(String url, String action, Object object){
        this.url = url;
        this.action = action;
        this.object = object;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
