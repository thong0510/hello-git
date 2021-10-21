package com.fpt.dao;

import com.fpt.entities.User;

import java.util.List;

public interface UserDao {

    boolean create(User user);

    boolean update(User user);

    boolean delete(Long id);

    List<User> getAll();

    User getById(Long id);

    User getUserByUsername(String username);
}
