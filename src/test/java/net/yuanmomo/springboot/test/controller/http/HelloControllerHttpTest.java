package net.yuanmomo.springboot.test.controller.http;

import org.junit.Test;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by Hongbin.Yuan on 2017-04-03 21:34.
 */

public class HelloControllerHttpTest extends BaseHttpTest{

    @Test
    public void getHello() throws Exception {
        ResponseEntity<String> response = template.getForEntity(base.toString(),
                String.class);
        assertThat(response.getBody(), equalTo("Greetings from Spring Boot!"));
    }
}

