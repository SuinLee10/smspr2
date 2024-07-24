package com.example.smspr2.dto;

import com.example.smspr2.domain.Tbpost;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;


public class DefaultDto {
    //data 담기 위함
    //요청을 받았을 때
    @Builder
    @Schema
    @Getter
    @Setter
    //우리가 필요한 정보 하나씩만 받기 위해 DTO를 만듬
    public static class FileResDto{
        private String url;
    }
}
