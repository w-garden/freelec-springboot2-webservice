package com.wgarden.book.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

//인터페이스 생성후 JpaRepository<엔티티 클래스, PK 타입> 을 상속하면 기본 적인 CRUD 메서드 자동으로 추가됨.
// @Repository를 추가할 필요도 없음
//주의할점: Entity 클래스와 기본 Entity Repository는 함께 위치해야함 -> Entity 클래스는 기본 Repository 없이는 제대로 역할을 할 수가 없음
public interface PostsRepository extends JpaRepository<Posts, Long> {
    @Query("SELECT p from Posts p order by p.id DESC")
    List<Posts> findAllDesc();
}
