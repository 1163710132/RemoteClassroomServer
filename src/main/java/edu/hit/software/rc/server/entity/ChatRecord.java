package edu.hit.software.rc.server.entity;

import java.time.Instant;

public class ChatRecord {
    private long id;
    private long publisher;
    private long target;
    private long targetGroup;
    private Instant time;
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

    public long getTarget() {
        return target;
    }

    public void setTarget(long target) {
        this.target = target;
    }

    public Instant getTime() {
        return time;
    }

    public void setTime(Instant time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getTargetGroup() {
        return targetGroup;
    }

    public void setTargetGroup(long targetGroup) {
        this.targetGroup = targetGroup;
    }
}
