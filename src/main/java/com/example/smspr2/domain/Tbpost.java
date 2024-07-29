package com.example.smspr2.domain;

import com.example.smspr2.domain.AuditingFields;
import com.example.smspr2.dto.TbpostDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Table(indexes = {
        @Index(columnList = "deleted")
        ,@Index(columnList = "process")
        ,@Index(columnList = "createdAt")
        ,@Index(columnList = "modifiedAt")
})
@Entity
public class Tbpost extends AuditingFields {

    @Setter @Column(nullable = false) private String tbuserId; //(fk이지만, 따로 설정은 안함.)
    @Setter @Column(nullable = false, length=400) private String title;
    @Setter @Column(nullable = true, length=10000) @Lob private String content; // 본문

    protected Tbpost(){}
    private Tbpost(String tbuserId, String title, String content) {
        this.tbuserId = tbuserId;
        this.title = title;
        this.content = content;
    }
    public static Tbpost of(String tbuserId, String title, String content) {
        return new Tbpost(tbuserId, title, content);
    }

    public TbpostDto.CreateResDto toCreateResDto() {
        return TbpostDto.CreateResDto.builder().id(this.getId()).build();
    }
}