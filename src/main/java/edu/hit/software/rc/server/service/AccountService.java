package edu.hit.software.rc.server.service;

import edu.hit.software.rc.server.entity.Account;
import edu.hit.software.rc.server.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    private final AccountMapper accountMapper;

    @Autowired
    public AccountService(AccountMapper accountMapper) {
        this.accountMapper = accountMapper;
    }

    public boolean strIdExists(String strId){
        return accountMapper.countAccountsById(strId) > 0;
    }

    public Account createAccount(String strId){
        Account account = new Account();
        account.setStrId(strId);
        if(accountMapper.insertAccount(account) > 0){
            return account;
        }else{
            return null;
        }
    }
}
