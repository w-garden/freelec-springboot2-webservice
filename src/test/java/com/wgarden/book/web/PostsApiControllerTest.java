package com.wgarden.book.web;

import com.wgarden.book.domain.posts.Posts;
import com.wgarden.book.domain.posts.PostsRepository;
import com.wgarden.book.web.dto.PostsSaveReqeustDto;
import com.wgarden.book.web.dto.PostsUpdateRequestDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
//@WebMvcTest의 경우 JPA 기능이 작동하지 않기 때문에 JPA기능 테스트 할때는
//@SpringBootTest, @TestRestTemplate을 사용하면됨
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostsApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostsRepository postsRepository;

    @AfterEach
    public void tearDown()  {
        postsRepository.deleteAll();
    }

    @Test
    public void Posts_enroll()  {
        //given
        String title = "title";
        String content = "content";
        PostsSaveReqeustDto reqeustDto = PostsSaveReqeustDto.builder()
                .title(title)
                .content(content)
                .author("author")
                .build();

        String url ="http://localhost:" + port +"/api/v1/posts";
        //when
        ResponseEntity<Long> responseEntity = restTemplate.
                postForEntity(url, reqeustDto, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getContent()).isEqualTo(content);
    }


    @Test
    public void Posts_update() {
        //given
        Posts savePosts = postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build()
        );

        Long updateId = savePosts.getId();
        String expectedTitle = "title2";
        String expectedContent = "content2";

        PostsUpdateRequestDto requestDto =
                PostsUpdateRequestDto.builder()
                        .title(expectedTitle)
                        .content(expectedContent)
                        .build();
        String url = "http://localhost:" + port +"/api/v1/posts/" + updateId;
        HttpEntity<PostsUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

        //when
        ResponseEntity<Long> responseEntity=restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(expectedTitle);
        assertThat(all.get(0).getContent()).isEqualTo(expectedContent);
    }
}
