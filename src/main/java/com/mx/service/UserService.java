package com.mx.service;

import com.mx.domain.User;

import java.util.List;

public interface UserService {

    public List<User> findAll();

    public User regist(User user);

    public User loginCheck(String userCode,String password);

    public void checkUserCode(String userCode);
}
