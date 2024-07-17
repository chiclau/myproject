package com.example.demo.demos.web.service;

import com.example.demo.demos.web.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<User> list();

    void delete(String id);

    void add(User user);

    int update(User user);
    public List<User> selectByUser(User user);
}
