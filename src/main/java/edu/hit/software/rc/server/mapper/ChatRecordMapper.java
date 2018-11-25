package edu.hit.software.rc.server.mapper;

import edu.hit.software.rc.server.entity.ChatRecord;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Repository
@Mapper
public interface ChatRecordMapper {

    @Select("SELECT * FROM REMOTE_CLASSROOM.CHAT_RECORD WHERE ID = #{id}")
    ChatRecord getChatRecordById(long id);

    @Select("SELECT * FROM REMOTE_CLASSROOM.CHAT_RECORD" +
            " WHERE PUBLISHER = #{publisher} AND TIME > #{timestamp} AND TIME < #{end}")
    List<ChatRecord> getRecordsByPublisher(long publisher, Instant begin, Instant end);

    @Select("SELECT * FROM REMOTE_CLASSROOM.CHAT_RECORD" +
            " WHERE TARGET = #{target} AND TIME > #{timestamp} AND TIME < #{end}")
    List<ChatRecord> getRecordsByTarget(long target, Instant begin, Instant end);

    @Select("SELECT * FROM REMOTE_CLASSROOM.CHAT_RECORD" +
            " WHERE TARGET_GROUP = #{targetGroup} AND TIME > #{timestamp} AND TIME < #{end}")
    List<ChatRecord> getRecordsByTargetGroup(long targetGroup, Instant begin, Instant end);

    @Insert("INSERT INTO REMOTE_CLASSROOM.CHAT_RECORD(PUBLISHER, TARGET_GROUP, TIME, CONTENT, TARGET) " +
            "VALUES(#{publisher}, #{targetGroup}, #{time}, #{content}, #{target})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertChatRecord(ChatRecord chatRecord);
}
