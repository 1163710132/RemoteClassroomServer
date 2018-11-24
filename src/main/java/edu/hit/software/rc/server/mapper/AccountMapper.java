package edu.hit.software.rc.server.mapper;

import edu.hit.software.rc.server.entity.Account;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface AccountMapper {

    @Select("SELECT ACCOUNT.ID FROM REMOTE_CLASSROOM.ACCOUNT WHERE STR_ID = #{strId}")
    Long getAccountIdByStrId(String strId);

    @Insert("INSERT INTO REMOTE_CLASSROOM.ACCOUNT(STR_ID) VALUES(#{strId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertAccount(Account account);

    @Select("SELECT COUNT(ACCOUNT.ID) FROM REMOTE_CLASSROOM.ACCOUNT WHERE STR_ID = #{strId}")
    int countAccountsById(String strId);
}
