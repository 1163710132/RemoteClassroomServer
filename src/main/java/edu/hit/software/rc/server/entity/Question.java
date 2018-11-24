package edu.hit.software.rc.server.entity;

public class Question {
    private long id;
    private long publisher;
    private long standard;
    private String content;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPublisher() {
        return publisher;
    }

    public void setPublisher(long publisher) {
        this.publisher = publisher;
    }

    public long getStandard() {
        return standard;
    }

    public void setStandard(long standard) {
        this.standard = standard;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
