package edu.hit.software.rc.server.mapper;

import edu.hit.software.rc.server.entity.Account;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface AccountMapper {
    @Insert("INSERT INTO REMOTE_CLASSROOM.ACCOUNT() VALUES()")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertAccount(Account account);

    @Delete("DELETE FROM REMOTE_CLASSROOM.ACCOUNT WHERE ID = #{id}")
    int deleteAccount(@Param("id") long id);

    @Select("SELECT COUNT(ACCOUNT.ID) FROM REMOTE_CLASSROOM.ACCOUNT WHERE ID = #{id}")
    int countAccounts(@Param("id") long id);
}
