package edu.hit.software.rc.server.controller;

import edu.hit.software.rc.server.entity.Account;

import javax.servlet.http.HttpSession;

public interface Controller {
    public static final String UID = "uid";

    default long getUid(HttpSession session){
        Long uid = (Long)session.getAttribute(UID);
        return uid == null ? -1 : uid;
    }
    default void setUid(long uid, HttpSession session){
        session.setAttribute(UID, uid == -1 ? null : uid);
    }
    default boolean requiresAuthenticated(HttpSession session){
        return getUid(session) != -1;
    }
    default boolean requiresAccount(long uid, HttpSession session){
        return getUid(session) == uid;
    }
}
