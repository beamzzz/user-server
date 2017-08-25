package com.mx.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 *
 */
@Entity(name = "mx_user")
public class User implements Serializable{

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long userId;

    private String userCode;

    private String userName;

    private Timestamp createDate;

    private String telephone;

    private String password;
    
    private String openId;

    /**
     * 手机验证码（用于注册）
     */
    @Transient
    private String verifyCode;

    private String headImageUrl;

    public void setId(Long userId) {
        this.userId = userId;
    }

    public Long getId() {
        return userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public String getHeadImageUrl() {
        return headImageUrl;
    }

    public void setHeadImageUrl(String headImageUrl) {
        this.headImageUrl = headImageUrl;
    }
}
