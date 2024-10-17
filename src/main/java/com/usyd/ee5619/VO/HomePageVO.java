package com.usyd.ee5619.VO;

import com.usyd.ee5619.Entity.Completion;
import com.usyd.ee5619.Entity.Schedule;
import com.usyd.ee5619.Entity.StudyTimeByMonth;
import lombok.Data;

import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
@Data
public class HomePageVO {
    private String studyTime;
    private String recommendCourse;
    private Completion completion;
    private List<StudyTimeByMonth> studyTimeByMonth;
    private Schedule schedule;
}
