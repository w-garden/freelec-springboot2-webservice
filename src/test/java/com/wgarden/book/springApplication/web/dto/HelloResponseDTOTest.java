package com.wgarden.book.springApplication.web.dto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class HelloResponseDTOTest {

    @Test
    public void lombok_test(){
        //given
        String name="test";
        int amount =1000;
        //when
        HelloResponseDTO dto = new HelloResponseDTO(name, amount);
        //then
        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAmount()).isEqualTo(amount);
    }

}