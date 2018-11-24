package edu.hit.software.rc.server.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
@RequestMapping("/account")
public class AccountController implements Controller {
    @RequestMapping("/login")
    public boolean login(String username){
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(new UsernamePasswordToken(username, "1"));
        }catch (AuthenticationException e){
            e.printStackTrace();
        }
        return subject.isAuthenticated();
    }
    @RequestMapping("logout")
    public boolean logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return true;
    }
    @RequestMapping("/status")
    public String status(){
        return "";
    }
    @RequestMapping("/getUid")
    public long getUid(){
        if(getSubject().isAuthenticated()){
            return getAccount().getId();
        }else{
            return -1;
        }
    }
}
