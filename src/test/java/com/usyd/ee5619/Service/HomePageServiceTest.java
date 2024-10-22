package com.usyd.ee5619.Service;

import com.usyd.ee5619.Entity.Completion;
import com.usyd.ee5619.Entity.CourseSchedule;
import com.usyd.ee5619.Entity.Schedule;
import com.usyd.ee5619.Entity.StudyTimeByMonth;
import com.usyd.ee5619.Mapper.HomePageMapper;
import com.usyd.ee5619.VO.HomePageVO;
import com.usyd.ee5619.Service.HomePageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class HomePageServiceTest {

    @Mock
    private HomePageMapper homePageMapper;

    @InjectMocks
    private HomePageService homePageService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetHomeData() {
        // Mocking data
        int userId = 1;
        String studyTime = "10:30";
        String recommendedCourse = "Math";
        int mathCompletion = 2; // Completion status for Math
        int readingCompletion = 2; // Completion status for Reading
        int writingCompletion = 1; // Completion status for Writing

        List<StudyTimeByMonth> studyTimeByMonthList = new ArrayList<>();
        studyTimeByMonthList.add(new StudyTimeByMonth("10-16", 5));

        List<CourseSchedule> courseSchedules = new ArrayList<>();
        courseSchedules.add(new CourseSchedule("Math", "2024-09-19"));
        courseSchedules.add(new CourseSchedule("Reading", "2024-09-23"));

        // Defining behavior for mocks
        when(homePageMapper.getWeeklyStudyTime(userId)).thenReturn(studyTime);
        when(homePageMapper.getRecommendCourse(userId)).thenReturn(recommendedCourse);
        when(homePageMapper.getMathCompletion(userId)).thenReturn(mathCompletion);
        when(homePageMapper.getReadingCompletion(userId)).thenReturn(readingCompletion);
        when(homePageMapper.getWritingCompletion(userId)).thenReturn(writingCompletion);
        when(homePageMapper.getStudyTimeByMonth(userId)).thenReturn(studyTimeByMonthList);
        when(homePageMapper.getSchedule(userId)).thenReturn(courseSchedules);

        // Call the service method
        HomePageVO result = homePageService.getHomeData(userId);

        // Assertions
        assertNotNull(result);
        assertEquals(studyTime, result.getStudyTime());
        assertEquals(recommendedCourse, result.getRecommendCourse());

        // Check completion calculation
        assertEquals(66, result.getCompletion().getMath());  // 2 * 33
        assertEquals(66, result.getCompletion().getReading());  // 2 * 33
        assertEquals(100, result.getCompletion().getWriting());  // Writing complete

        // Check studyTimeByMonth
        assertNotNull(result.getStudyTimeByMonth());
        assertEquals(1, result.getStudyTimeByMonth().size());
        assertEquals("10-16", result.getStudyTimeByMonth().get(0).getMonth());
        assertEquals(5, result.getStudyTimeByMonth().get(0).getTime());

        // Check schedule
        assertNotNull(result.getSchedule().getCourseSchedule());
        assertEquals(2, result.getSchedule().getCourseSchedule().size());
        assertTrue(result.getSchedule().getCourseSchedule().containsKey("Math"));
        assertTrue(result.getSchedule().getCourseSchedule().containsKey("Reading"));
        assertEquals(1, result.getSchedule().getCourseSchedule().get("Math").size());
        assertEquals("2024-09-19", result.getSchedule().getCourseSchedule().get("Math").get(0));
    }
}
