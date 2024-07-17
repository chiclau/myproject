package com.example.demo.demos.web.service.impl;

import com.example.demo.demos.web.User;
import com.example.demo.demos.web.mapper.UserMapper;
import com.example.demo.demos.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> list() {
        return userMapper.list();
    }

    @Override
    public List<User> selectByUser(User user) {
        return userMapper.selectByUser(user);
    }

    @Override
    public void delete(String id) {
        userMapper.deleteById(id);
    }

    @Override
    public void add(User user) {
        userMapper.insert(user);
    }

    @Override
    public int update(User user) {
        return 0;
    }
}
