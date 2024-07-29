package com.example.smspr2.domain;

import com.example.smspr2.dto.TbpostDto;
import com.example.smspr2.dto.TbpostcmtDto;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import jakarta.persistence.Index;
import lombok.Getter;
import lombok.Setter;

@Getter
@Table(indexes = {
        @Index(columnList = "deleted")
        ,@Index(columnList = "process")
        ,@Index(columnList = "createdAt")
        ,@Index(columnList = "modifiedAt")
        })
@Entity //field 중에 id가 꼭 필요하다
public class Tbpostcmt extends  AuditingFields{
    //auditing fields를 extends
    /*@Id private String id;
    @Setter private String deleted;
    @Setter private String createdAt;
    @Setter private String modifiedAt;*/

    @Setter @Column(nullable = false) private String tbpostId;
    @Setter @Column(nullable = false) private String tbuserId;
    @Setter @Column(nullable = false, length = 400) private String content;

    protected Tbpostcmt(){}

    private Tbpostcmt(String tbpostId, String tbuserId, String content){
        this.tbpostId = tbpostId;
        this.tbuserId = tbuserId;
        this.content = content;
    }
    public static Tbpostcmt of(String tbpostId, String tbuserId, String content){

        return new Tbpostcmt(tbpostId, tbuserId, content);
    }
    public TbpostcmtDto.CreateResDto toCreateResDto(){
        /*TbpostDto.CreateResDto createResDto1 = new TbpostDto.CreateResDto();
        createResDto1.setId(this.getId());*/
        return TbpostcmtDto.CreateResDto.builder().id(this.getId()).build();
    }
}
