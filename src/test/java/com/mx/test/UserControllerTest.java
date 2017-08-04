package com.mx.test;

import com.mx.SpringRestDocApplicationTest;
import com.mx.UserServerApplication;
import com.mx.service.UserService;
import com.mx.web.TestController;
import com.mx.web.UserController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.snippet.Attributes;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
                                fieldWithPath("data[0].userCode").description("用户编码").type("String"),
                                fieldWithPath("data[0].userName").description("用户名").type("String"),
                                fieldWithPath("data[0].createDate").description("创建时间").type("date"),
                                fieldWithPath("data[0].telephone").description("电话号码").type("String"),
                                fieldWithPath("data[0].password").description("密码").type("String"),
                                fieldWithPath("data[0].openId").description("openId").type("String"))));
    }
}
