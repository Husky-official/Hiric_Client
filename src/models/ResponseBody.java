package models;

/**
 * @author : Abijuru Seth
 * @description : Parse a response from the server from a byte-stream;
 */

import java.io.Serializable;
import java.util.List;

public class ResponseBody implements Serializable {
    private List<Object> response;

    public ResponseBody(List<Object> response) {
        this.response = response;
    }

    public List<Object> getResponse() {
        return response;
    }

    public void setResponse(List<Object> response) {
        this.response = response;
    }
}
