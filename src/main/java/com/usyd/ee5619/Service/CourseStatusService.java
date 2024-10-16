package com.usyd.ee5619.Service;

import com.usyd.ee5619.DTO.CourseStatusDTO;
import com.usyd.ee5619.Entity.CourseStatus;
import com.usyd.ee5619.Mapper.CourseStatusMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
@Service
public class CourseStatusService {
    @Autowired
    private CourseStatusMapper courseStatusMapper;
    public void saveCourseStatus(int userId, CourseStatusDTO courseStatusDTO) {
        CourseStatus courseStatus = new CourseStatus();
        courseStatus.setUserId(userId);
        courseStatus.setCourse(courseStatusDTO.getCourse());
        courseStatus.setCompleteNum(courseStatusDTO.getCompleteNum());
        courseStatus.setCompletionDate(LocalDate.now());
        courseStatusMapper.insertCourseStatus(courseStatus);
    }
}
