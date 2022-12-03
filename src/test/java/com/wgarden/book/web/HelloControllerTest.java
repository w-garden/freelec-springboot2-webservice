package com.wgarden.book.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = HelloController.class)//여러스프링 어노테이션 중 스프링 MVC에 집중할 수있는 어노테이션
class HelloControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void hello_return() throws Exception {
        String hello ="hello";

        mvc.perform(get("/hello")) //Mockmvc를 통해 /hello 주소로  HTTP GET 요청을 합니다. 체이닝이 지원됨
                .andExpect(status().isOk()) //mvc.perform의 결과를 검증. 200,404,500 등의 상태를 점검
                .andExpect(content().string(hello)); //응답 본문의 내용을 검증
    }

    @Test
    public void helloDto_return() throws Exception {
        String name ="hello";
        int amount = 1000;

        mvc.perform(
                get("/hello/dto")
                        .param("name", name) //API 테스트 할 때 사용될 요청 파라미터 설정. 값은 String만 허용
                        .param("amount",String.valueOf(amount))
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name",is(name))) //jsonPath : JSON 응답값을 필드별로 검증 할 수 있는 메서드. $를 기준으로 필드명을 명시
                .andExpect(jsonPath("$.amount",is(amount)));
    }

}