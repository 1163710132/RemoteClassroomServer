package edu.hit.software.rc.server.mapper;

import edu.hit.software.rc.server.entity.Course;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface CourseMapper {

    @Select("SELECT * FROM REMOTE_CLASSROOM.COURSE WHERE ID = #{id}")
    Course getCourseById(long id);

    @Insert("INSERT INTO REMOTE_CLASSROOM.COURSE(NAME, MANAGER, CANCELED, CREATOR) " +
            "VALUES(#{name}, #{manager}, #{canceled}, #{creator})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertCourse(Course course);

    @Update("UPDATE REMOTE_CLASSROOM.COURSE SET MANAGER = #{manager} WHERE ID = #{course}")
    void updateManager(@Param("manager") long course, @Param("course") long manager);

    @Update("UPDATE REMOTE_CLASSROOM.COURSE SET CANCELED = TRUE WHERE ID = #{course} AND CANCELED = FALSE ")
    int cancelCourse(@Param("course") long course);
}
