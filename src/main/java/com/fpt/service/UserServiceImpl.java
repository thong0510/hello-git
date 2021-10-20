package com.fpt.service;

import com.fpt.dao.UserDao;
import com.fpt.entities.User;
import com.fpt.model.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    @Override
    public boolean create(UserDTO userDTO) {
        try {
            User user = convertUserDTOToUser(userDTO);
            userDao.create(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(UserDTO userDTO) {
        User user = convertUserDTOToUser(userDTO);
        return userDao.update(user);
    }

    @Override
    public boolean delete(Long id) {
        return userDao.delete(id);
    }

    @Override
    public List<UserDTO> getAll() {
        List<User> users = userDao.getAll();
        List<UserDTO> userDTOS = new ArrayList<UserDTO>();
        for (User user:users) {
            UserDTO userDTO = convertUserToUserDTO(user);
            userDTOS.add(userDTO);
        }
        return userDTOS;
    }

    @Override
    public UserDTO getById(Long id) {
        User user = userDao.getById(id);
        return convertUserToUserDTO(user);
    }

    private User convertUserDTOToUser(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setAddress(userDTO.getAddress());
        user.setAge(userDTO.getAge());
        user.setEmail(userDTO.getEmail());
        user.setEnabled(userDTO.getEnabled());
        user.setGender(userDTO.getGender());
        user.setName(userDTO.getName());
        user.setPhone(userDTO.getPhone());
        user.setUsername(userDTO.getUsername());
        user.setRole(userDTO.getRole());

        return user;
    }

    private UserDTO convertUserToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setAddress(user.getAddress());
        userDTO.setAge(user.getAge());
        userDTO.setEmail(user.getEmail());
        userDTO.setEnabled(user.getEnabled());
        userDTO.setGender(user.getGender());
        userDTO.setName(user.getName());
        userDTO.setPhone(user.getPhone());
        userDTO.setUsername(user.getUsername());
        userDTO.setRole(user.getRole());

        return userDTO;
    }

}
