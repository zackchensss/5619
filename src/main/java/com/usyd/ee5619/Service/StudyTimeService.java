package com.usyd.ee5619.Service;

import com.usyd.ee5619.Entity.StudyTime;
import com.usyd.ee5619.Mapper.StudyTimeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class StudyTimeService {
    @Autowired
    private StudyTimeMapper studyTimeMapper;

    public void saveStudyTime(int userId, String time) {
        StudyTime studyTime = new StudyTime();
        studyTime.setUserId(userId);
        studyTime.setStudyTime(time);
        studyTime.setDate(LocalDate.now());
        studyTimeMapper.insertStudyProgress(studyTime);
    }
}

