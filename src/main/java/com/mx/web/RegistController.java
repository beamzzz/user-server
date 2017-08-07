package com.mx.web;

import com.mx.domain.ReturnMessage;
import com.mx.domain.User;
import com.mx.exception.MxException;
import com.mx.exception.ParameterException;
import com.mx.service.UserService;
import com.netflix.discovery.converters.Auto;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @aother zcl
 * @date 2017/8/4
 */
@RestController
@RequestMapping("")
public class RegistController {

    @Autowired
    private UserService userServicel;

    @PostMapping("/regist")
    public ReturnMessage regist(@RequestBody User user){
        try{
            if(StringUtils.isBlank(user.getUserCode())){
                throw new ParameterException("用户名不能为空");
            }
            if(StringUtils.isBlank(user.getPassword())){
                throw  new ParameterException("用户密码不能为空");
            }
            if(StringUtils.isBlank(user.getVerifyCode())){
                throw new ParameterException("手机验证码不能为空");
            }
            User resullt = userServicel.save(user);
            return new ReturnMessage("0000",resullt);
        }catch (MxException e){
            return new ReturnMessage("9999",e.getMessage());
        }


    }

    @GetMapping("getVerifyCode/{telephone}")
    public ReturnMessage getVerifyCode(@PathVariable("telephone") String telephone){
        return  null;
    }
}
