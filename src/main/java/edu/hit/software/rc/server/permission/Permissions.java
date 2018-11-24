package edu.hit.software.rc.server.permission;

public abstract class Permissions {
    public static final String ACCOUNT = "account:";
    public static final String MANAGER = "manager:";
    public static final String GROUP = "group:";
    public static final String COURSE = "course";

    public static String ofAccount(long accountId){
        return ACCOUNT + String.valueOf(accountId);
    }

    public static String ofGroup(long groupId){
        return GROUP + String.valueOf(groupId);
    }

    public static String ofCourse(long courseId){
        return COURSE + String.valueOf(courseId);
    }
}
