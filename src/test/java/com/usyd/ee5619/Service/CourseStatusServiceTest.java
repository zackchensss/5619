package com.usyd.ee5619.Service;

import com.usyd.ee5619.DTO.CourseStatusDTO;
import com.usyd.ee5619.Entity.CourseStatus;
import com.usyd.ee5619.Mapper.CourseStatusMapper;
import com.usyd.ee5619.Service.CourseStatusService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

public class CourseStatusServiceTest {

    @Mock
    private CourseStatusMapper courseStatusMapper;

    @InjectMocks
    private CourseStatusService courseStatusService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSaveCourseStatus() {
        int userId = 1;
        CourseStatusDTO courseStatusDTO = new CourseStatusDTO();
        courseStatusDTO.setCourse("Math");
        courseStatusDTO.setCompleteNum(3);

        courseStatusService.saveCourseStatus(userId, courseStatusDTO);

        ArgumentCaptor<CourseStatus> captor = ArgumentCaptor.forClass(CourseStatus.class);
        verify(courseStatusMapper).insertCourseStatus(captor.capture());

        CourseStatus capturedCourseStatus = captor.getValue();
        assertEquals("Math", capturedCourseStatus.getCourse());
        assertEquals(3, capturedCourseStatus.getCompleteNum());
        assertEquals(userId, capturedCourseStatus.getUserId());
        assertEquals(LocalDate.now(), capturedCourseStatus.getCompletionDate());
    }
}
