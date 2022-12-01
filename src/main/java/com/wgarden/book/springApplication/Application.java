package com.wgarden.book.springApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication //스프링 부트의 자동설정, 스프링 bean 읽기와 생성을 모두 자동으로 설정. 프로젝트 최상단에 위치
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
