package edu.hit.software.rc.server.mapper;

import edu.hit.software.rc.server.entity.Announcement;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
@Mapper
public interface AnnouncementMapper {
    @Select("SELECT * FROM ANNOUNCEMENT WHERE ID = #{id}")
    Announcement getAnnouncementById(long id);

    @Insert("INSERT INTO REMOTE_CLASSROOM.ANNOUNCEMENT(TITLE, CONTENT, PUBLISHER, TARGET_GROUP, TIME) " +
            "VALUES(#{title}, #{content}, #{publisher}, #{targetGroup}, #{time})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertAnnouncement(Announcement announcement);

    @Select("SELECT ID FROM ANNOUNCEMENT WHERE TARGET_GROUP = #{targetGroup} AND TIME > #{since}")
    List<Long> selectAnnouncementsByTargetGroup(long targetGroup, Timestamp since);
}
