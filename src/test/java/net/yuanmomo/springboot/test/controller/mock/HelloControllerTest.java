package net.yuanmomo.springboot.test.controller.mock;

/**
 * Created by Hongbin.Yuan on 2017-04-03 21:34.
 */


import net.yuanmomo.springboot.Application;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockFilterConfig;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.servlet.ServletException;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
@AutoConfigureMockMvc
public class HelloControllerTest {

    @Autowired private FilterRegistrationBean filterRegistrationBean ;
    @Autowired private MockMvc mvc;


    /**
     * fix Druid WebAppStat NullPointerException.
     */
    @Before
    public void before(){
        try {
            MockFilterConfig mockFilterConfig = new MockFilterConfig();
            filterRegistrationBean.getFilter().init(mockFilterConfig);
        } catch (ServletException e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    public void getHello() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Greetings from Spring Boot!")));
    }
}

