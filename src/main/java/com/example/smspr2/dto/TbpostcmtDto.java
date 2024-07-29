package com.example.smspr2.dto;

import com.example.smspr2.domain.Tbpost;
import com.example.smspr2.domain.Tbpostcmt;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

public class TbpostcmtDto {

    @Builder
    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    //우리가 필요한 정보 하나씩만 받기 위해 DTO를 만듬
    public static class CreateReqDto{
        @Schema(description = "tbpostId", example = "")
        @NotNull
        @NotEmpty
        private String tbpostId;

        @Schema(description = "tbuserId", example = "")
        @NotNull
        @NotEmpty
        @Size(max=100)
        private String tbuserId;
        @Schema(description = "content", example = "")
        @Size(max=4000)
        private String content;

        public Tbpostcmt toEntity(){
            return Tbpostcmt.of(tbpostId, tbuserId, content);
        }
    }
    @Builder
    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreateResDto{
        private String id;
    }
    @SuperBuilder
    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UpdateReqDto extends DefaultDto.UpdateReqDto{
        @Schema(description = "tbpostId" , example="")
        private String tbpostId;

        @Schema(description = "tbuserId", example="")
        @Size(max=100)
        private String tbuserId;
        @Schema(description = "content", example="")
        @Size(max=4000)
        private String content;
    }

    @Schema
    @Getter
    @Setter
    public static class DetailResDto extends DefaultDto.DetailResDto{
        @Schema(description = "tbpostId" , example="")
        private String tbpostId;

        @Schema(description = "tbuserId" , example="")
        private String tbuserId;
        @Schema(description = "content", example="")
        private String content;
    }
    @SuperBuilder
    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ListReqDto extends DefaultDto.ListReqDto{
       @Schema(description = "tbpostId", example ="")
        private String tbPostId;
       @Schema(description = "tbuserId", example="")
        private String tbuserId;

    }
    @SuperBuilder
    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PagedListReqDto extends DefaultDto.PagedListReqDto{
        @Schema(description = "tbpostId", example="")
        private String tbpostId;
        @Schema(description = "tbuserId", example ="")
        private String tbuserId;
    }

    @SuperBuilder
    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ScrollListReqDto extends DefaultDto.ScrollListReqDto{
        @Schema(description = "tbpostId", example="")
        private String tbpostId;
        @Schema(description = "tbuserId", example ="")
        private String tbuserId;
    }
}