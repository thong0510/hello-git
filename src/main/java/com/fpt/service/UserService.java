package com.fpt.service;

import com.fpt.entities.User;
import com.fpt.model.UserDTO;

import java.util.List;

public interface UserService {

    boolean create(UserDTO userDTO);

    boolean update(UserDTO userDTO);

    boolean delete(Long id);

    List<UserDTO> getAll();

    UserDTO getById(Long id);
}
