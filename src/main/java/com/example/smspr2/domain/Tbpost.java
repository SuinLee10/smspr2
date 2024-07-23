package com.example.smspr2.domain;

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
        //index가 많으면 많을 수록 create할 때 시간이 많이 걸림
        //db에서 찾을 때 빨리 찾을 수 있음
        })
@Entity //field 중에 id가 꼭 필요하다
public class Tbpost extends  AuditingFields{
    //auditing fields를 extends
    /*@Id private String id;
    @Setter private String deleted;
    @Setter private String createdAt;
    @Setter private String modifiedAt;*/

    @Setter @Column(nullable = false, length = 400) private String title;
    @Setter @Column(nullable = false, length = 400) private String author;
    @Setter @Column(nullable = true, length = 10000) @Lob private String content;

    protected Tbpost(){}

    private Tbpost(String title, String author, String content){
        this.title = title;
        this.author = author;
        this.content = content;
    }
    public static Tbpost of(String title, String author, String content){

        return new Tbpost(title, author, content);
    }
    public TbpostDto.CreateResDto toCreateResDto(){
        /*TbpostDto.CreateResDto createResDto1 = new TbpostDto.CreateResDto();
        createResDto1.setId(this.getId());*/
        return TbpostDto.CreateResDto.builder().id(this.getId()).build();
    }
}
