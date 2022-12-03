package com.wgarden.book.web;

import com.wgarden.book.service.posts.PostsService;
import com.wgarden.book.web.dto.PostsResponseDto;
import com.wgarden.book.web.dto.PostsSaveReqeustDto;
import com.wgarden.book.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveReqeustDto requestDto) {
        return postsService.save(requestDto);
    }

    @PutMapping("/api/vi/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);

    }
    @GetMapping("api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id){
        return postsService.findById(id);
    }
}