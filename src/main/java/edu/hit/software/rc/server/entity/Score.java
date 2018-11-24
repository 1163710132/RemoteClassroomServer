package edu.hit.software.rc.server.entity;

import java.math.BigDecimal;

public class Score {
    private BigDecimal value;
    private long user;
    private long memberGroup;
    private String name;

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getUser() {
        return user;
    }

    public void setUser(long user) {
        this.user = user;
    }

    public long getMemberGroup() {
        return memberGroup;
    }

    public void setMemberGroup(long memberGroup) {
        this.memberGroup = memberGroup;
    }
}
