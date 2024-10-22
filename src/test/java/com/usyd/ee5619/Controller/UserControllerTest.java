package com.usyd.ee5619.Controller;
import com.usyd.ee5619.DTO.UserDTO;
import com.usyd.ee5619.DTO.UserLoginDTO;
import com.usyd.ee5619.Entity.User;
import com.usyd.ee5619.Service.UserService;
import com.usyd.ee5619.controller.UserController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.any;
public class UserControllerTest {
    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void testSignUp() throws Exception {
        // Data
        UserDTO userDTO = new UserDTO();
        userDTO.setUserName("testUser");
        userDTO.setPassword("testPassword");

        //Json
        String requestBody = """
                {
                    "userName": "testUser",
                    "password": "testPassword",
                    "email": "test@test.com"
                }
                """;

        mockMvc.perform(post("/api/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                    {
                        "code": 1
                    }
                """));
    }

    @Test
    public void testSignIn_Success() throws Exception {
        // Data
        UserLoginDTO userLoginDTO = new UserLoginDTO();
        userLoginDTO.setUserName("testUser");
        userLoginDTO.setPassword("testPassword");

        User user = new User();
        user.setUserName("testUser");
        user.setPassword("098f6bcd4621d373cade4e832627b4f6");

        when(userService.signin(any(UserLoginDTO.class))).thenReturn(user);

        String requestBody = """
                {
                    "userName": "testUser",
                    "password": "testPassword"
                }
                """;

        mockMvc.perform(post("/api/signin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                    {
                        "code": 0
                    }
                """));
    }

    @Test
    public void testSignIn_Failure_UserNotFound() throws Exception {

        when(userService.signin(any(UserLoginDTO.class))).thenReturn(null);

        String requestBody = """
                {
                    "userName": "nonExistentUser",
                    "password": "wrongPassword"
                }
                """;

        mockMvc.perform(post("/api/signin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                    {
                        "code": 0
                    }
                """));
    }

    @Test
    public void testSignIn_Failure_WrongPassword() throws Exception {

        User user = new User();
        user.setUserName("testUser");
        user.setPassword("098f6bcd4621d373cade4e832627b4f6");

        when(userService.signin(any(UserLoginDTO.class))).thenReturn(user);

        String requestBody = """
                {
                    "userName": "testUser",
                    "password": "wrongPassword"
                }
                """;

        mockMvc.perform(post("/api/signin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                    {
                        "code": 0
                    }
                """));
    }
}
