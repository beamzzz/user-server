package com.mx.service.impl;

import com.mx.domain.User;
import com.mx.exception.ServiceException;
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
    public User regist(User user) {
        //检查手机号，用户名是否重复
        if(userRepository.findByUserCode(user.getUserCode()) != null){
            throw new ServiceException(user.getUserCode() + "用户名重复");
        }
        if(userRepository.findByTelephone(user.getTelephone()) != null){
            throw new ServiceException(user.getUserCode() + "该手机号已被注册");
        }

        user.setCreateDate(new Timestamp(new Date().getTime()));

       return userRepository.save(user);
    }

    @Override
    public User loginCheck(String userCode, String password) {
        User user = userRepository.findByUserCode(userCode);
        if(user == null){
            throw  new ServiceException("用户名" + userCode + "不存在");
        }
        if(!password.equals(user.getPassword())){
            throw  new ServiceException("密码错误");
        }
        return user;
    }
}
