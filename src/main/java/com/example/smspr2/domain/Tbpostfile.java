package com.example.smspr2.domain;

import com.example.smspr2.dto.TbpostDto;
import com.example.smspr2.dto.TbpostfileDto;
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
public class Tbpostfile extends  AuditingFields{
    //auditing fields를 extends
    /*@Id private String id;
    @Setter private String deleted;
    @Setter private String createdAt;
    @Setter private String modifiedAt;*/

    @Setter @Column(nullable = false) private String tbpostId;
    @Setter @Column(nullable = false) private String type;
    @Setter @Column(nullable = false, length = 400) private String url;

    protected Tbpostfile(){}

    private Tbpostfile(String tbpostId, String type, String url){
        this.tbpostId = tbpostId;
        this.type = type;
        this.url = url;
    }
    public static Tbpostfile of(String tbpostId, String type, String url){

        return new Tbpostfile(tbpostId, type, url);
    }
    public TbpostfileDto.CreateResDto toCreateResDto(){
        /*TbpostDto.CreateResDto createResDto1 = new TbpostDto.CreateResDto();
        createResDto1.setId(this.getId());*/
        return TbpostfileDto.CreateResDto.builder().id(this.getId()).build();
    }
}
