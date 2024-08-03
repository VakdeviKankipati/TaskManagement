package com.vakya.taskmanagement;

import com.vakya.taskmanagement.controllers.UserController;
import com.vakya.taskmanagement.dtos.ResponseStatus;
import com.vakya.taskmanagement.dtos.UserRequestDto;
import com.vakya.taskmanagement.dtos.UserResponseDto;
import com.vakya.taskmanagement.models.User;
import com.vakya.taskmanagement.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserControllerTest {
    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegisterUser_Success() {
        UserRequestDto requestDto = new UserRequestDto();
        requestDto.setUsername("testuser");
        requestDto.setPassword("password");

        User user = new User();
        when(userService.registerUser(anyString(), anyString())).thenReturn(user);

        UserResponseDto responseDto = userController.registerUser(requestDto);
        assertNotNull(responseDto);
        assertEquals(ResponseStatus.SUCCESS, responseDto.getResponseStatus());
        assertEquals(user, responseDto.getUser());
    }

    @Test
    public void testRegisterUser_Failure() {
        UserRequestDto requestDto = new UserRequestDto();
        requestDto.setUsername("testuser");
        requestDto.setPassword("password");

        when(userService.registerUser(anyString(), anyString())).thenThrow(new RuntimeException());

        UserResponseDto responseDto = userController.registerUser(requestDto);
        assertNotNull(responseDto);
        assertEquals(ResponseStatus.FAILURE, responseDto.getResponseStatus());
    }

    @Test
    public void testLoginUser_Success() throws Exception {
        UserRequestDto requestDto = new UserRequestDto();
        requestDto.setUsername("testuser");
        requestDto.setPassword("password");

        String token = "jwt-token";
        User user = new User();
        when(userService.loginUser(anyString(), anyString())).thenReturn(token);
        when(userService.findByUsername(anyString())).thenReturn(user);

        UserResponseDto responseDto = userController.loginUser(requestDto);
        assertNotNull(responseDto);
        assertEquals(ResponseStatus.SUCCESS, responseDto.getResponseStatus());
        assertEquals(user, responseDto.getUser());
        assertEquals(token, responseDto.getToken());
    }

    @Test
    public void testLoginUser_Failure() throws Exception {
        UserRequestDto requestDto = new UserRequestDto();
        requestDto.setUsername("testuser");
        requestDto.setPassword("password");

        when(userService.loginUser(anyString(), anyString())).thenThrow(new RuntimeException());

        UserResponseDto responseDto = userController.loginUser(requestDto);
        assertNotNull(responseDto);
        assertEquals(ResponseStatus.FAILURE, responseDto.getResponseStatus());
    }
}
