package com.usyd.ee5619.Controller;
import com.usyd.ee5619.DTO.StudyTimeDTO;
import com.usyd.ee5619.Service.StudyTimeService;
import com.usyd.ee5619.Service.UserService;
import com.usyd.ee5619.controller.StudyTimeController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class StudyTimeControllerTest {
    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @Mock
    private StudyTimeService studyTimeService;

    @InjectMocks
    private StudyTimeController studyTimeController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(studyTimeController).build();
    }

    @Test
    public void testStartStudy() throws Exception {
        String username = "testUser";
        int userId = 1;
        String time = "10:30";

        StudyTimeDTO studyTimeDTO = new StudyTimeDTO();
        studyTimeDTO.setUserName(username);
        studyTimeDTO.setTime(time);

        when(userService.findUserIdByUsername(username)).thenReturn(userId);

        doNothing().when(studyTimeService).saveStudyTime(userId, time);

        String requestBody = """
                {
                    "userName": "testUser",
                    "time": "10:30"
                }
                """;

        mockMvc.perform(post("/api/setStudyTime")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                    {
                        "code": 1,
                        "data": "record success"
                    }
                """));
    }
}
