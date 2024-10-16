package com.usyd.ee5619.Mapper;

import com.usyd.ee5619.Entity.StudyTime;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDate;
import java.time.LocalTime;

@Mapper
public interface StudyTimeMapper {
    @Insert("insert into study_progress (user_id, date, study_time)" +
            "values " +
            "(#{userId},#{date},#{studyTime})")
    void insertStudyProgress(StudyTime studyTime);
}
