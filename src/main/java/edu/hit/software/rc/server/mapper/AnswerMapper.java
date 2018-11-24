package edu.hit.software.rc.server.mapper;

import edu.hit.software.rc.server.entity.Answer;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.Instant;

@Repository
@Mapper
public interface AnswerMapper {
    @Select("SELECT * FROM REMOTE_CLASSROOM.ANSWER WHERE ID = #{id}")
    Answer getAnswerById(@Param("id") long id);

    @Insert("INSERT INTO REMOTE_CLASSROOM.ANSWER(PUBLISHER, CONTENT, QUESTION, PUBLISHED) " +
            "VALUES (#{publisher}, #{content}, #{question}, #{published})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertAnswer(Answer answer);

    @Select("SELECT ID FROM REMOTE_CLASSROOM.ANSWER " +
            "WHERE PUBLISHER = #{publisher} " +
            "AND QUESTION = #{question} " +
            "AND PUBLISHED < #{until}")
    long selectLastAnswer(@Param("publisher") long publisher, @Param("question") long question, @Param("until") Instant until);
}
