package com.usyd.ee5619.Controller;
import com.usyd.ee5619.Common.Result;
import com.usyd.ee5619.DTO.CourseStatusDTO;
import com.usyd.ee5619.Service.CourseStatusService;
import com.usyd.ee5619.Service.UserService;
import com.usyd.ee5619.controller.ClassStatusController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
public class ClassStatusControllerTest {
    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @Mock
    private CourseStatusService courseStatusService;

    @InjectMocks
    private ClassStatusController classStatusController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(classStatusController).build();
    }

    @Test
    public void testSetCourseStatus() throws Exception {
        // Data
        CourseStatusDTO courseStatusDTO = new CourseStatusDTO();
        courseStatusDTO.setUserName("testUser");
        courseStatusDTO.setCourse("Math");
        courseStatusDTO.setCompleteNum(2);

        when(userService.findUserIdByUsername("testUser")).thenReturn(1);

        // Json
        String requestBody = """
                {
                    "userName": "testUser",
                    "course": "Math",
                    "completeNum": 2
                }
                """;

        // Test
        mockMvc.perform(post("/api/setCourseStatus")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                    {
                        "code": 1,
                        "data": "record success"
                    }
                """));

        // Res
        verify(userService).findUserIdByUsername("testUser");
        verify(courseStatusService).saveCourseStatus(eq(1), any(CourseStatusDTO.class));
    }
}
