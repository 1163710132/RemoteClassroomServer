package edu.hit.software.rc.server.entity;

public class MemberGroup {
    public static final String MANAGER = "manager";

    private long id;
    private long course;
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCourse() {
        return course;
    }

    public void setCourse(long course) {
        this.course = course;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
