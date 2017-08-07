package com.mx.service.impl;

import com.mx.service.VerifyService;

import java.util.HashMap;
import java.util.Map;

/**
 * @aother zcl
 * @date 2017/8/4
 */
public class VerifyServiceImpl implements VerifyService {

    private Map<String,String> verifyMap = new HashMap<String,String>();

    /**
     * 获取手机验证码
     * @param telephone
     * @auther : beam
     * @date : 2017/8/4
     */
    @Override
    public void getVerifyCode(String telephone) {

    }

    /**
     * 校验手机验证码合法性
     *
     * @param telephone
     * @param verifyCode
     * @auther : beam
     * @date : 2017/8/4
     */
    @Override
    public boolean checkVerifyCode(String telephone, String verifyCode) {
        return true;
    }
}
