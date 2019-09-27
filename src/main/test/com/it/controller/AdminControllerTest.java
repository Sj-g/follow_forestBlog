package com.it.controller;

import com.it.base.BaseTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@WebAppConfiguration
public class AdminControllerTest extends BaseTest {
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Before
    public void init(){
        this.mockMvc=MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
    @Test
    public void verification() throws Exception {
        String s=mockMvc.perform(MockMvcRequestBuilders.post("/verification").param("email","2330715018@qq.com").param("password","123").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                .status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn().getResponse().getContentAsString();
        System.out.println(s);
    }

    @Test
    public void getHomePage() throws Exception {
        String s=mockMvc.perform(MockMvcRequestBuilders.post("/homepage"))
                .andExpect(MockMvcResultMatchers
                        .status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn().getResponse().getContentAsString();
        System.out.println(s);
    }
}