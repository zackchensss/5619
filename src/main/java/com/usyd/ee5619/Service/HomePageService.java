package com.usyd.ee5619.Service;

import com.usyd.ee5619.Entity.Completion;
import com.usyd.ee5619.Entity.CourseSchedule;
import com.usyd.ee5619.Entity.Schedule;
import com.usyd.ee5619.Entity.StudyTimeByMonth;
import com.usyd.ee5619.Mapper.HomePageMapper;
import com.usyd.ee5619.VO.HomePageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HomePageService {
    @Autowired
    private HomePageMapper homePageMapper;
    public HomePageVO getHomeData(int userId) {
        String studyTime = homePageMapper.getWeeklyStudyTime(userId);
        String recommendedCourse = homePageMapper.getRecommendCourse(userId);

        int mathCompletion = homePageMapper.getMathCompletion(userId);
        int readingCompletion = homePageMapper.getReadingCompletion(userId);
        int writingCompletion = homePageMapper.getWritingCompletion(userId);

        Completion completion = new Completion();
        completion.setMath(mathCompletion >= 3 ? 100 : mathCompletion * 33);
        completion.setReading(readingCompletion >= 3 ? 100 : readingCompletion * 33);
        completion.setWriting(writingCompletion == 1 ? 100 : 0);

        List<StudyTimeByMonth> studyTimeByMonth = homePageMapper.getStudyTimeByMonth(userId);

        List<CourseSchedule> entries = homePageMapper.getSchedule(userId);
        Map<String, List<String>> courseSchedule = new HashMap<>();
        for (CourseSchedule entry : entries) {
            courseSchedule.computeIfAbsent(entry.getCourse(), k -> new ArrayList<>()).add(entry.getCompletionDate());
        }
        Schedule schedule = new Schedule();
        schedule.setCourseSchedule(courseSchedule);
        //set the VO
        HomePageVO homePageVO = new HomePageVO();
        homePageVO.setStudyTime(studyTime);
        homePageVO.setRecommendCourse(recommendedCourse);
        homePageVO.setCompletion(completion);
        homePageVO.setStudyTimeByMonth(studyTimeByMonth);
        homePageVO.setSchedule(schedule);
        return homePageVO;
    }
}
