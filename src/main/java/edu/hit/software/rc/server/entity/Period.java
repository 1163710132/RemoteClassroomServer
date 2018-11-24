package edu.hit.software.rc.server.entity;

import java.time.Instant;

public class Period {
    private long id;
    private Instant begin;
    private Instant end;
    private String pos;
    private long memberGroup;
    private String tag;
    private boolean canceled;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Instant getBegin() {
        return begin;
    }

    public void setBegin(Instant begin) {
        this.begin = begin;
    }

    public Instant getEnd() {
        return end;
    }

    public void setEnd(Instant end) {
        this.end = end;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public long getMemberGroup() {
        return memberGroup;
    }

    public void setMemberGroup(long memberGroup) {
        this.memberGroup = memberGroup;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public boolean isCanceled() {
        return canceled;
    }

    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }
}
