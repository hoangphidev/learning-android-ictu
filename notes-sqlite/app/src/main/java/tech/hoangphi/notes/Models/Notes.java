package tech.hoangphi.notes.Models;

public class Notes {
    private int id;
    private String body;

    public Notes(String body) {
        this.body = body;
    }

    public Notes(int id, String body) {
        this.id = id;
        this.body = body;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
