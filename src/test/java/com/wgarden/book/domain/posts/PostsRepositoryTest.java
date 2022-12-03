package com.wgarden.book.domain.posts;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest //SpringBootTest 어노테이션을 사용하면 DB를 자동으로 실행시켜줌
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @AfterEach
    public void cleanup(){
        postsRepository.deleteAll();
    }
    @Test
    public void post_save_load(){
        //given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        // postsRepository.save -> id 값이 있다면 update가, 없다면 insert 쿼리가 실행
        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("shc729@naver.com")
                .build());
        //when
        //postsRepository.findAll() -> 테이블 posts에 있는 모든 데이터를 조회해오는 메서드
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);

    }

    @Test
    public void BaseTimeEntity_enroll(){
        //given
        LocalDateTime now = LocalDateTime.of(2022,12,03,0,0,0);
        postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());
        //when
        List<Posts> postsList = postsRepository.findAll();
        //then
        Posts posts = postsList.get(0);

        System.out.println(">>>>>>>>>>>>>>>>>>>>> createDate =" + posts.getCreateDate()+", modifiedDate="+posts.getModifiedDate());

        assertThat(posts.getCreateDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);
    }
}
