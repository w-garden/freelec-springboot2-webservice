package com.wgarden.book.springApplication.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor //선언된 모든 final 필드가 포함된 생성자 생성해줌
public class HelloResponseDTO {
    private final String name;
    private final int amount;

}
