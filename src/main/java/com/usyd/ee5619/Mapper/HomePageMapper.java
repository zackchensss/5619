package com.usyd.ee5619.Mapper;

import com.usyd.ee5619.Entity.CourseSchedule;
import com.usyd.ee5619.Entity.Schedule;
import com.usyd.ee5619.Entity.StudyTimeByMonth;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface HomePageMapper {
    @Select("SELECT SEC_TO_TIME(SUM(TIME_TO_SEC(STR_TO_DATE(study_time, '%i:%s')))) FROM study_progress WHERE user_id = #{userId} AND WEEK(date) = WEEK(NOW())")
    String getWeeklyStudyTime(int userId);
    @Select("SELECT COALESCE((SELECT course FROM course_status WHERE user_id = #{userId} AND complete_num = 0 LIMIT 1), 'math')")
    String getRecommendCourse(int userId);
    @Select("SELECT SUM(complete_num) FROM course_status WHERE user_id = #{userId} AND course = 'math'")
    int getMathCompletion(int userId);

    @Select("SELECT SUM(complete_num) FROM course_status WHERE user_id = #{userId} AND course = 'reading'")
    int getReadingCompletion(int userId);

    @Select("SELECT SUM(complete_num) FROM course_status WHERE user_id = #{userId} AND course = 'writing'")
    int getWritingCompletion(int userId);
    @Select("SELECT MONTH(date) AS month, SUM(study_time) AS time FROM study_progress WHERE user_id = #{userId} GROUP BY MONTH(date)")
    List<StudyTimeByMonth> getStudyTimeByMonth(int userId);
    @Select("SELECT course, completion_date FROM course_status WHERE user_id = #{userId}")
    List<CourseSchedule> getSchedule(int userId);
}
