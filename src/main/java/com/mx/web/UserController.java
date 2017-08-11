package com.mx.web;

import com.mx.domain.ReturnMessage;
import com.mx.domain.User;
import com.mx.exception.MxException;
import com.mx.exception.ParameterException;
import com.mx.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户接口
 * @aother zcl
 * @date 2017/8/3
 */
@RestController
@RequestMapping()
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/findAll")
    public ReturnMessage findAll(){

        return new ReturnMessage("0000",userService.findAll());
    }
    @PostMapping("/login")
    public ReturnMessage login(@RequestParam(name = "userCode",required = true)String userCode,
                               @RequestParam(name = "password",required = true) String password ){
        logger.info(userCode + "登录");
        try {
            User user = userService.loginCheck(userCode,password);

            return  new ReturnMessage("0000",user);
        }catch (MxException e){
            return new ReturnMessage("9999",e.getMessage());
        }
    }
}
