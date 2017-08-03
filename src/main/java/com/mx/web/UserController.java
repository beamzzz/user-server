package com.mx.web;

import com.mx.domain.ReturnMessage;
import com.mx.domain.User;
import com.mx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 用户接口
 * @aother zcl
 * @date 2017/8/3
 */
@RestController
@RequestMapping()
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/findAll")
    public ReturnMessage findAll(){

        return new ReturnMessage("0000",userService.findAll());
    }
}
