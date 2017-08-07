package com.mx.service;

/**
 * @aother zcl
 * @date 2017/8/4
 */
public interface VerifyService {

    /**
     * 获取手机验证码
     * @auther : beam
     * @date : 2017/8/4
     **/
    public void getVerifyCode(String telephone);

    /**
     *  校验手机验证码合法性
     * @auther : beam
     * @date : 2017/8/4
     **/
    public boolean checkVerifyCode(String telephone,String verifyCode);

}
