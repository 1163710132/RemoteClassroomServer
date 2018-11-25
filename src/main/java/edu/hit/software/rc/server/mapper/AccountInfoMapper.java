package edu.hit.software.rc.server.mapper;

import edu.hit.software.rc.server.entity.AccountInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface AccountInfoMapper {
    @Select("SELECT * FROM REMOTE_CLASSROOM.ACCOUNT_INFO WHERE ID = #{id}")
    AccountInfo getAccountInfoById(@Param("id") long id);

    @Insert("INSERT INTO REMOTE_CLASSROOM.ACCOUNT_INFO(ID, AVATAR, NICKNAME) VALUES(#{id}, #{avatar}, #{nickname})")
    int insertAccountInfo(AccountInfo accountInfo);

    @Update("UPDATE REMOTE_CLASSROOM.ACCOUNT_INFO SET AVATAR = #{avatar}, NICKNAME = #{nickname} WHERE ID = #{id}")
    int updateAccountInfo(AccountInfo accountInfo);
}
