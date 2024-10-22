package com.usyd.ee5619.Service;

import com.usyd.ee5619.DTO.UserDTO;
import com.usyd.ee5619.DTO.UserLoginDTO;
import com.usyd.ee5619.Entity.User;
import com.usyd.ee5619.Mapper.UserMapper;
import com.usyd.ee5619.Service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {
    @Mock
    private UserMapper userMapper;
    @InjectMocks
    private UserService userService;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testSave() {
        // Given
        UserDTO userDTO = new UserDTO();
        userDTO.setUserName("john");
        userDTO.setPassword("password123");

        // When
        userService.save(userDTO);

        // Then
        verify(userMapper, times(1)).insert(any(User.class));
    }
    @Test
    void testSignin() {
        // Given
        UserLoginDTO userLoginDTO = new UserLoginDTO();
        userLoginDTO.setUserName("john");
        userLoginDTO.setPassword("password123");

        User user = new User();
        user.setUserName("john");
        user.setPassword(DigestUtils.md5DigestAsHex("password123".getBytes()));

        when(userMapper.getByUsername("john")).thenReturn(user);

        // When
        User result = userService.signin(userLoginDTO);

        // Then
        assertNotNull(result);
        assertEquals("john", result.getUserName());
        verify(userMapper, times(1)).getByUsername("john");
    }
    @Test
    void testFindUserIdByUsername() {
        // Given
        String username = "john";
        User user = new User();
        user.setUserId(1);
        user.setUserName("john");

        when(userMapper.getByUsername(username)).thenReturn(user);

        // When
        int userId = userService.findUserIdByUsername(username);

        // Then
        assertEquals(1, userId);
        verify(userMapper, times(1)).getByUsername(username);
    }
}
