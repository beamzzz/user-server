package com.mx.service.impl;

import com.mx.domain.User;
import com.mx.repository.UserRepository;
import com.mx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User save(User user) {
        user.setCreateDate(new Timestamp(new Date().getTime()));
       return userRepository.save(user);
    }
}
