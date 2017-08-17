package com.mx.test;

import com.mx.SpringRestDocApplicationTest;
import org.junit.Test;

import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.relaxedPathParameters;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 用户接口测试
 * @aother zcl
 * @date 2017/8/3
 */
public class UserControllerTest extends SpringRestDocApplicationTest{


    @Test
    public void testFindAll() throws Exception{
        this.mockMvc.perform(get("/findAll")).andDo(print()).andExpect(status().isOk())
                .andDo(document("user-findall",
                        relaxedResponseFields(
                                fieldWithPath("data[0].id").description("用户ID").type("Number"),
                                fieldWithPath("data[0].userCode").description("用户名").type("String"),
                                fieldWithPath("data[0].userName").description("昵称").type("String"),
                                fieldWithPath("data[0].createDate").description("创建时间").type("date"),
                                fieldWithPath("data[0].telephone").description("电话号码").type("String"),
                                fieldWithPath("data[0].password").description("密码").type("String"),
                                fieldWithPath("data[0].openId").description("openId").type("String"))));
    }


    @Test
    public void testLogin()throws Exception{

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("userCode", "beam");
        params.add("password", "123456");

        this.mockMvc.perform(post("/login").params(params)).andDo(print()).andExpect(status().isOk())
                .andDo(document("user-login",
                        requestParameters(
                                parameterWithName("userCode").description("用户名"),
                                parameterWithName("password").description("密码")
                        ),
                        relaxedResponseFields(
                                fieldWithPath("data").description("用户信息").type("Object")
                        )
                ));
    }

    @Test
    public void testCheckUserCode() throws Exception{
        this.mockMvc.perform(RestDocumentationRequestBuilders.get("/checkUserCode/{userCode}","beam"))
                .andDo(print()).andExpect(status().isOk())
                .andDo(document("user-checkUserCode",
                        relaxedPathParameters(
                                parameterWithName("userCode").description("用户名")
                        )
//                        relaxedResponseFields(
//                                fieldWithPath("data").description("验证码").type("String")
//                        )
                ));
    }

    /**
     * 解密测试
     * @auther : beam
     * @date : 2017/8/15
     **/
    @Test
    public void testdecrypt() throws Exception{
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("content", "DFag0hto6AUjMY0Sr0JuSw==");
        params.add("key", "AJSTKSHTYSJKLDAP");

        this.mockMvc.perform(post("/decrypt").params(params)).andDo(print()).andExpect(status().isOk())
                .andDo(document("test-decrypt",
                        requestParameters(
                                parameterWithName("content").description("要解密的内容"),
                                parameterWithName("key").description("密钥")
                        ),
                        relaxedResponseFields(
                                fieldWithPath("data").description("解密结果").type("String")
                        )
                ));
    }

    /**
     *  加密测试
     * @auther : beam
     * @date : 2017/8/15
     **/
    @Test
    public void testencrypt() throws Exception{
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("content", "maoxiansoft");
        params.add("key", "AJSTKSHTYSJKLDAP");

        this.mockMvc.perform(post("/encrypt").params(params)).andDo(print()).andExpect(status().isOk())
                .andDo(document("test-encrypt",
                        requestParameters(
                                parameterWithName("content").description("要加密的内容"),
                                parameterWithName("key").description("密钥")
                        ),
                        relaxedResponseFields(
                                fieldWithPath("data").description("加密结果").type("String")
                        )
                ));
    }
}
