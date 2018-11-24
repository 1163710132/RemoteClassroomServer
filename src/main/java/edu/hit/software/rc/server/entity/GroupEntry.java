package edu.hit.software.rc.server.entity;

public class GroupEntry {
    private long member;
    private long memberGroup;
    private long course;

    public long getMember() {
        return member;
    }

    public void setMember(long member) {
        this.member = member;
    }

    public long getMemberGroup() {
        return memberGroup;
    }

    public void setMemberGroup(long memberGroup) {
        this.memberGroup = memberGroup;
    }

    public long getCourse() {
        return course;
    }

    public void setCourse(long course) {
        this.course = course;
    }
}
