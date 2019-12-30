package com.azhuo.usercrud;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * 使用SpringBoot测试框架
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserApplication.class)
public class UserControllerTest {

    @Autowired
    private WebApplicationContext wca;


    private MockMvc mockMvc; // 定义一个mvc环境的伪造对象

    @Before
    public void before() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wca).build();
    }

    @Test
    public void test_findAll() throws Exception {
        String content = mockMvc.perform(MockMvcRequestBuilders.get("/user").
        contentType(MediaType.APPLICATION_JSON_UTF8)
                // .param("") // 设置参数
        ).andExpect(MockMvcResultMatchers.status().isOk())
        .andReturn().getResponse().getContentAsString();// 获取返回值
        System.out.println("content = " + content);
    }
}
