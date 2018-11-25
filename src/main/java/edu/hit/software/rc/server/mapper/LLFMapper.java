package edu.hit.software.rc.server.mapper;

import edu.hit.software.rc.server.entity.LocalLoginFields;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface LLFMapper {
    @Select("SELECT * FROM REMOTE_CLASSROOM.LOCAL_LOGIN_FIELDS WHERE ID = #{id}")
    LocalLoginFields getLLFById(@Param("id") long id);

    @Select("SELECT * FROM REMOTE_CLASSROOM.LOCAL_LOGIN_FIELDS WHERE USERNAME = #{username}")
    LocalLoginFields getLLFByUsername(@Param("username") String username);

    @Insert("INSERT INTO REMOTE_CLASSROOM.LOCAL_LOGIN_FIELDS(ID, USERNAME, PASSWORD) " +
            "VALUES (#{id}, #{username}, #{password})")
    int insertLLF(LocalLoginFields localLoginFields);

    @Update("UPDATE REMOTE_CLASSROOM.LOCAL_LOGIN_FIELDS " +
            "SET USERNAME = #{username}, PASSWORD = #{password} " +
            "WHERE ID = #{id}")
    int updateLLF(LocalLoginFields localLoginFields);
}
