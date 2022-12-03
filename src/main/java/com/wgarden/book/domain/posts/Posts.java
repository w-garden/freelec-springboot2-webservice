package com.wgarden.book.domain.posts;

import com.wgarden.book.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor; //기본생성자 추가

import javax.persistence.*;


/*
Entity 클래스에서는 절대 Setter 클래스를 만들지 않는다.
 */
@Getter
@NoArgsConstructor
@Entity //테이블과 링크될 클래스임을 나타냄. 기본값으로 카멜케이스의 이름을 언더스코어 네이밍으로 테이블 이름을 매핑한다
public class Posts extends BaseTimeEntity {
    @Id //PK필드 표시
    @GeneratedValue(strategy = GenerationType.IDENTITY) //PK 생성 규칙
    private Long id;
    @Column(length = 500, nullable = false)
    private String title;
    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;
    private String author;

    @Builder //해당 클래스의 빌더 패턴 클래스를 생성. 생성자 상단에 선언시 생성자에 포함된 필드만 빌더에 포함
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content){
        this.title=title;
        this.content=content;
    }
}
