package edu.hit.software.rc.server.mapper;

import edu.hit.software.rc.server.entity.Question;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface QuestionMapper {
    @Select("SELECT * FROM REMOTE_CLASSROOM.QUESTION WHERE ID = #{id}")
    Question getQuestionById(@Param("id") long id);

    @Insert("INSERT INTO REMOTE_CLASSROOM.QUESTION(PUBLISHER, STANDARD, CONTENT) " +
            "VALUES (#{publisher}, #{standard}, #{content})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertQuestion(Question question);

    @Update("UPDATE REMOTE_CLASSROOM.QUESTION SET STANDARD = #{standard} WHERE ID = #{id}")
    int updateStandardAnswer(@Param("id") long id, @Param("answer") long answer);
}
