package com.wgarden.book.web;

import com.wgarden.book.config.auth.LoginUser;
import com.wgarden.book.config.auth.dto.SessionUser;
import com.wgarden.book.service.posts.PostsService;
import com.wgarden.book.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) { 
        // @LoginUser SessionUser user : 기존 세션불러오는 코드를 간단하게 수정

        model.addAttribute("posts",postsService.findAllDesc());


        if(user != null){
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }

    @GetMapping("posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post",dto);

        return "posts-update";
    }
}
