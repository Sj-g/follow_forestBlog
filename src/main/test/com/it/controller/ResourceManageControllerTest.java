package com.it.controller;

import com.it.base.baseTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.*;
@WebAppConfiguration
public class ResourceManageControllerTest extends baseTest {
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Before
    public void init(){
        this.mockMvc= MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
    @Test
    //是否回滚
    @Rollback(value = true)
    public void disable() throws Exception {
        String result=mockMvc.perform(MockMvcRequestBuilders.put("/disable/2"))
                .andExpect(MockMvcResultMatchers
                        .status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn().getResponse().getContentAsString();
        System.out.println(result.toString());
    }

    @Test
    public void enable() {
    }
    @Test
    public void resourceList() throws Exception {
        String result=mockMvc.perform(MockMvcRequestBuilders.put("/resourceList"))
                .andExpect(MockMvcResultMatchers
                        .status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn().getResponse().getContentAsString();
    }
}