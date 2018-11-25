package edu.hit.software.rc.server.controller;

import edu.hit.software.rc.server.entity.AccountInfo;
import edu.hit.software.rc.server.entity.LocalLoginFields;
import edu.hit.software.rc.server.service.AccountService;
import edu.hit.software.rc.server.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@EnableAutoConfiguration
@RequestMapping("/account")
public class AccountController implements Controller {
    private final PermissionService permissionService;
    private final AccountService accountService;

    @Autowired
    public AccountController(PermissionService permissionService, AccountService accountService) {
        this.permissionService = permissionService;
        this.accountService = accountService;
    }


    @RequestMapping("/login")
    public long login(String username, String password, HttpSession session){
        LocalLoginFields llf = accountService.getLLFByUsername(username);
        if(llf != null){
            if(llf.getPassword().equals(password)){
                return llf.getId();
            }
        }
        return -1;
    }
    @RequestMapping("/logout")
    public boolean logout(HttpSession session){
        setUid(-1, session);
        return true;
    }
    @RequestMapping("/current")
    public long current(HttpSession session){
        return getUid(session);
    }
    @RequestMapping("/info")
    public AccountInfo info(HttpSession session){
        return accountService.getInfo(getUid(session));
    }
}
