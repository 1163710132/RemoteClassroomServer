package edu.hit.software.rc.server.service;

import edu.hit.software.rc.server.entity.Account;
import edu.hit.software.rc.server.entity.AccountInfo;
import edu.hit.software.rc.server.entity.LocalLoginFields;
import edu.hit.software.rc.server.mapper.AccountInfoMapper;
import edu.hit.software.rc.server.mapper.AccountMapper;
import edu.hit.software.rc.server.mapper.LLFMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    private final AccountMapper accountMapper;
    private final AccountInfoMapper accountInfoMapper;
    private final LLFMapper llfMapper;

    @Autowired
    public AccountService(AccountMapper accountMapper, AccountInfoMapper accountInfoMapper, LLFMapper llfMapper) {
        this.accountMapper = accountMapper;
        this.accountInfoMapper = accountInfoMapper;
        this.llfMapper = llfMapper;
    }

    public LocalLoginFields getLLFByUsername(String username){
        return llfMapper.getLLFByUsername(username);
    }

    public long createAccount(LocalLoginFields localLoginFields){
        Account account = new Account();
        if(accountMapper.insertAccount(account) > 0){
            localLoginFields.setId(account.getId());
            if(llfMapper.insertLLF(localLoginFields) > 0){
                return localLoginFields.getId();
            }else{
                accountMapper.deleteAccount(account.getId());
            }
        }
        return -1;
    }

    public AccountInfo getInfo(long accountId){
        return accountInfoMapper.getAccountInfoById(accountId);
    }
}
