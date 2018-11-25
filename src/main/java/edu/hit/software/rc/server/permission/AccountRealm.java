/*package edu.hit.software.rc.server.permission;

import edu.hit.software.rc.server.entity.Account;
import edu.hit.software.rc.server.entity.GroupEntry;
import edu.hit.software.rc.server.entity.LocalLoginFields;
import edu.hit.software.rc.server.mapper.AccountMapper;
import edu.hit.software.rc.server.mapper.CourseMapper;
import edu.hit.software.rc.server.mapper.GroupEntryMapper;
import edu.hit.software.rc.server.mapper.GroupMapper;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("accountRealm")
public class AccountRealm extends AuthorizingRealm {
    private final AccountMapper accountMapper;
    private final GroupEntryMapper groupEntryMapper;
    private final CourseMapper courseMapper;
    private final GroupMapper groupMapper;

    @Autowired
    public AccountRealm(AccountMapper accountMapper,
                        GroupEntryMapper groupEntryMapper,
                        CourseMapper courseMapper,
                        GroupMapper groupMapper){
        setName("AccountRealm");
        this.groupMapper = groupMapper;
        this.accountMapper = accountMapper;
        this.groupEntryMapper = groupEntryMapper;
        this.courseMapper = courseMapper;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        for(Account account : principals.byType(Account.class)){
            simpleAuthorizationInfo.addStringPermission(Permissions.ACCOUNT + String.valueOf(account.getId()));
            for(GroupEntry entry : groupEntryMapper.getEntriesByMember(account.getId())){
                simpleAuthorizationInfo.addStringPermission(Permissions.GROUP + String.valueOf(entry.getMemberGroup()));
                simpleAuthorizationInfo.addStringPermission(Permissions.COURSE + String.valueOf(entry.getCourse()));
            }
        }
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        Account account = new Account();
        account.setStrId(strId);
        Long accountId = accountMapper.getAccountIdByStrId(strId);
        account.setId(accountId);
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(account, String.valueOf(accountMapper.countAccountsById(strId)), getName());
        return simpleAuthenticationInfo;
    }
}*/
