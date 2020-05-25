package com.zziri.contact.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HelloWorldControllerTest {
    @Autowired
    private HelloWorldController helloWorldController;

    private MockMvc mockMvc;

    @Test
    void helloWorld() {
        assertThat(helloWorldController.helloWorld()).isEqualTo("HelloWorld");
    }

    @Test
    void mockMvcTest() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(helloWorldController).build();                            // 뭔지 모르겠다
        mockMvc.perform(                                                                                    // mvc 패턴으로 실행
                MockMvcRequestBuilders.get("/api/helloWorld"))                                   // /api/helloWorld url로 get 요청
                .andDo(MockMvcResultHandlers.print())                                                       // 결과 출력(GET 요청, 헤더, 상태, 내용 등등)
                .andExpect(MockMvcResultMatchers.status().isOk())                                           // 상태가 200인지 확인
                .andExpect(MockMvcResultMatchers.content().string("HelloWorld"));             // 결과의 내용(Body) "HelloWorld"인가
    }

}