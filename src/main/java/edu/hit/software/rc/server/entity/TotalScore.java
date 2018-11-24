package edu.hit.software.rc.server.entity;

import java.math.BigDecimal;

public class TotalScore {
    private long id;
    private long course;
    private long student;
    private long method;
    private BigDecimal score;

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

    public long getStudent() {
        return student;
    }

    public void setStudent(long student) {
        this.student = student;
    }

    public long getMethod() {
        return method;
    }

    public void setMethod(long method) {
        this.method = method;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }
}
