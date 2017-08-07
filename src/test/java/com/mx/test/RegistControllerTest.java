package com.mx.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mx.SpringRestDocApplicationTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.relaxedResponseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 注册相关接口测试
 * @aother zcl
 * @date 2017/8/4
 */
public class RegistControllerTest  extends SpringRestDocApplicationTest {

    @Autowired
    private ObjectMapper objectMapper;
    /**
     *  注册接口测试
     * @auther : beam
     * @date : 2017/8/4
     **/
    @Test
    public void testRegist()throws Exception{
        Map<String, Object> regist = new HashMap<>();
        regist.put("userCode","beam");
        regist.put("userName","大王");
        regist.put("password","asdkjhlkjioluiojhoiuiolasda");
        regist.put("verifyCode","789654");
        regist.put("telephone","17710026695");

        this.mockMvc.perform(post("/regist").contentType(MediaType.APPLICATION_JSON).
                content(this.objectMapper.writeValueAsString(regist)))
                .andDo(print()).andExpect(status().isOk())
                .andDo(document("user-regist",
                        relaxedResponseFields(
                                fieldWithPath("data.id").description("用户ID").type("Number"),
                                fieldWithPath("data.userCode").description("用户编码").type("String"),
                                fieldWithPath("data.userName").description("用户名").type("String"),
                                fieldWithPath("data.createDate").description("创建时间").type("date"),
                                fieldWithPath("data.telephone").description("电话号码").type("String"),
                                fieldWithPath("data.password").description("密码").type("String"),
                                fieldWithPath("data.openId").description("openId").type("String"))));

    }
}
