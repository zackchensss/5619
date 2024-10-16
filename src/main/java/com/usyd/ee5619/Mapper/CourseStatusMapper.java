package com.usyd.ee5619.Mapper;

import com.usyd.ee5619.Entity.CourseStatus;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CourseStatusMapper {
    @Insert("INSERT INTO course_status (user_id, course, complete_num, completion_date) " +
            "values" +
            " (#{userId}, #{course}, #{completeNum}, #{completionDate})")
    void insertCourseStatus(CourseStatus courseStatus);
}
