package com.usyd.ee5619.Service;
import com.usyd.ee5619.Entity.StudyTime;
import com.usyd.ee5619.Mapper.StudyTimeMapper;
import com.usyd.ee5619.Service.StudyTimeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.mockito.Mockito.*;
public class StudyTimeServiceTest {
    @Mock
    private StudyTimeMapper studyTimeMapper;

    @InjectMocks
    private StudyTimeService studyTimeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveStudyTime() {
        // Given
        int userId = 1;
        String time = "10:30";
        StudyTime studyTime = new StudyTime();
        studyTime.setUserId(userId);
        studyTime.setStudyTime(time);
        studyTime.setDate(LocalDate.now());

        // When
        studyTimeService.saveStudyTime(userId, time);

        // Then
        verify(studyTimeMapper, times(1)).insertStudyProgress(any(StudyTime.class));
    }
}
