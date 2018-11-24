package edu.hit.software.rc.server.mapper;

import edu.hit.software.rc.server.entity.ChatRecord;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
@Mapper
public interface ChatRecordMapper {

    @Select("SELECT * FROM REMOTE_CLASSROOM.CHAT_RECORD WHERE ID = #{id}")
    ChatRecord getChatRecordById(long id);

    @Select("SELECT ID FROM REMOTE_CLASSROOM.CHAT_RECORD WHERE PUBLISHER = #{publisher} AND TIME > #{timestamp}")
    List<Long> selectRecordsByPublisher(long publisher, Timestamp timestamp);

    @Select("SELECT ID FROM REMOTE_CLASSROOM.CHAT_RECORD WHERE TARGET = #{target} AND TIME > #{timestamp}")
    List<Long> selectRecordsByTarget(long target, Timestamp timestamp);

    @Select("SELECT ID FROM REMOTE_CLASSROOM.CHAT_RECORD WHERE TARGET_GROUP = #{targetGroup} AND TIME > #{timestamp}")
    List<Long> selectRecordsByTargetGroup(long targetGroup, Timestamp timestamp);

    @Insert("INSERT INTO REMOTE_CLASSROOM.CHAT_RECORD(PUBLISHER, TARGET_GROUP, TIME, CONTENT, TARGET) " +
            "VALUES(#{publisher}, #{targetGroup}, #{time}, #{content}, #{target})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertChatRecord(ChatRecord chatRecord);
}
