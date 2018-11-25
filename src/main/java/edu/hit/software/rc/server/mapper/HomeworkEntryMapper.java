package edu.hit.software.rc.server.mapper;

import edu.hit.software.rc.server.entity.HomeworkEntry;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface HomeworkEntryMapper {
    @Select("SELECT * FROM REMOTE_CLASSROOM.HOMEWORK_ENTRY WHERE HOMEWORK = #{homework} AND Q_INDEX = #{qIndex}")
    HomeworkEntry getEntryByHomeworkAndQIndex(@Param("homework") long homework, @Param("qIndex") int qIndex);

    @Select("SELECT * FROM REMOTE_CLASSROOM.HOMEWORK_ENTRY WHERE HOMEWORK = #{homework}")
    List<HomeworkEntry> getEntriesByHomework(@Param("homework") long homework);

    @Insert("INSERT INTO REMOTE_CLASSROOM.HOMEWORK_ENTRY(HOMEWORK, Q_INDEX, QUESTION) " +
            "VALUES (#{homework}, #{qIndex}, #{question})")
    int insertEntry(HomeworkEntry homeworkEntry);
}
