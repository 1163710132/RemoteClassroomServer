package edu.hit.software.rc.server.controller;

import edu.hit.software.rc.server.entity.Account;
import edu.hit.software.rc.server.permission.Permissions;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

public interface Controller {
    default Subject getSubject(){
        return SecurityUtils.getSubject();
    }
    default Account getAccount(){
         return (Account) getSubject().getPrincipal();
    }
    default boolean requiresAuthenticated(){
        return getSubject().isAuthenticated();
    }
    default boolean requiresAccount(long accountId){
        return getSubject().isPermitted(Permissions.ofAccount(accountId));
    }
    default boolean requiresGroup(long groupId){
        return getSubject().isPermitted(Permissions.ofGroup(groupId));
    }
    default boolean requiresCourse(long courseId){
        return getSubject().isPermitted(Permissions.ofCourse(courseId));
    }
}
