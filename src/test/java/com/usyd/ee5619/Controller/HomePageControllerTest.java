package com.usyd.ee5619.Controller;
import com.usyd.ee5619.Common.Result;
import com.usyd.ee5619.Service.HomePageService;
import com.usyd.ee5619.Service.UserService;
import com.usyd.ee5619.VO.HomePageVO;
import com.usyd.ee5619.controller.HomePageController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
public class HomePageControllerTest {
    private MockMvc mockMvc;

    @Mock
    private HomePageService homePageService;

    @Mock
    private UserService userService;

    @InjectMocks
    private HomePageController homePageController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(homePageController).build();
    }

    @Test
    public void testGetHomeData() throws Exception {
        int userId = 1;
        String username = "testUser";

        when(userService.findUserIdByUsername(username)).thenReturn(userId);

        HomePageVO homePageVO = new HomePageVO();
        homePageVO.setStudyTime("10.5");
        homePageVO.setRecommendCourse("Math");



        when(homePageService.getHomeData(userId)).thenReturn(homePageVO);

        // 执行测试并验证响应
        mockMvc.perform(get("/api/getHomeData")
                        .param("username", username)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                    {
                        "code": 1,
                        "data": {
                            "studyTime": "10.5",
                            "recommendCourse": "Math"
                        }
                    }
                """));
    }
}
