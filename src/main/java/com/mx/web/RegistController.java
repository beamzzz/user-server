package com.mx.web;

import com.mx.domain.ReturnMessage;
import com.mx.domain.User;
import com.mx.exception.MxException;
import com.mx.exception.ParameterException;
import com.mx.service.UserService;
import com.mx.util.StringUtil;
import com.netflix.discovery.converters.Auto;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @aother zcl
 * @date 2017/8/4
 */
@RestController
public class RegistController {

    private Logger logger = LoggerFactory.getLogger(RegistController.class);

    @Autowired
    private UserService userServicel;

    @PostMapping("/regist")
    public ReturnMessage regist(@RequestBody User user){
        try{
            if(!StringUtil.checkLength(user.getUserCode(),6,20)){
                throw new ParameterException("用户名长度为6到20个字符之间");
            }
            if(StringUtils.isBlank(user.getPassword())){
                throw  new ParameterException("用户密码不能为空");
            }
            if(StringUtils.isBlank(user.getVerifyCode())){
                throw new ParameterException("手机验证码不能为空");
            }

            logger.info(user.getUserCode() + "请求注册,手机号码 ：" + user.getTelephone() );
            User resullt = userServicel.regist(user);
            logger.info(user.getUserCode() + "注册成功");
            return new ReturnMessage("0000",resullt);
        }catch (MxException e){
            logger.error(user.getUserCode() + "注册失败" + e.getMessage());
            return new ReturnMessage("9999",e.getMessage());
        }

    }
}
