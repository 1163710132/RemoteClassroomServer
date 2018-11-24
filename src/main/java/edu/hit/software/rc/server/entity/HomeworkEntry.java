package edu.hit.software.rc.server.entity;

public class HomeworkEntry {
    private long id;
    private long homework;
    private int qIndex;
    private long question;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getHomework() {
        return homework;
    }

    public void setHomework(long homework) {
        this.homework = homework;
    }

    public int getQIndex() {
        return qIndex;
    }

    public void setQIndex(int qIndex) {
        this.qIndex = qIndex;
    }

    public long getQuestion() {
        return question;
    }

    public void setQuestion(long question) {
        this.question = question;
    }
}
