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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;

import static org.bouncycastle.cms.RecipientId.password;

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
                               @RequestParam(name = "password",required = true) String password ,
                               HttpServletRequest request){
        logger.info(userCode + "登录");
        try {
            User user = userService.loginCheck(userCode,password);
            HttpSession session = request.getSession();

            //将用户信息放到session中
            session.setAttribute("user",user);

            return  new ReturnMessage("0000",user);
        }catch (MxException e){
            return new ReturnMessage("9999",e.getMessage());
        }
    }

    @GetMapping("/checkUserCode/{userCode}")
    public ReturnMessage checkUserCode(@PathVariable(name = "userCode")String userCode){
        logger.info(userCode + "检查用户名");
        try {
            userService.checkUserCode(userCode);

            return  new ReturnMessage("0000","用户名可用");
        }catch (MxException e){
            return new ReturnMessage("9999",e.getMessage());
        }
    }

    @GetMapping("/uploadHeadImage")
    public ReturnMessage uploadHeadImage(HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null){
            return new ReturnMessage("9999","用户未登录");
        }

        //检查用户头像图片是否存在
        user.setHeadImageUrl("");
        return new ReturnMessage("0000","上传成功");
    }

}
