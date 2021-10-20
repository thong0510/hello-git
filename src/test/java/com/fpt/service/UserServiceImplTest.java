package com.fpt.service;

import com.fpt.model.UserDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.junit.jupiter.api.Assertions.*;

@Component
class UserServiceImplTest {

    @Autowired
    UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserServiceImpl();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void createUser() {
        UserDTO userDTO = new UserDTO();
        userDTO.setName("Minh");
        userDTO.setEnabled(Boolean.TRUE);
        userDTO.setAddress("fff");
        userDTO.setUsername("aaa");
        userDTO.setAge("12");
        userDTO.setEmail("hh@gmail.com");
        userDTO.setGender("Male");
        userDTO.setPhone("09999");
        userDTO.setRole("ROLE_ADMIN");
        userDTO.setPassword("123");

        assertTrue(userService.create(userDTO));
    }


}