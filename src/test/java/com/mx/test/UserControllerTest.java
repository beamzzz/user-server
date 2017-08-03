package com.mx.test;

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
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 用户接口测试
 * @aother zcl
 * @date 2017/8/3
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = UserServerApplication.class)
@WebAppConfiguration
@AutoConfigureRestDocs(outputDir = "target/snippets")
public class UserControllerTest {

    @Autowired
    private UserController userController;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void testFindAll() throws Exception{
        this.mockMvc.perform(get("/findAll")).andDo(print()).andExpect(status().isOk())
                .andDo(document("home"));
    }
}
