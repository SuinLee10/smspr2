package com.example.smspr2.domain;

import com.example.smspr2.dto.TbpostDto;
import com.example.smspr2.dto.TbuserDto;
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
public class Tbuser extends  AuditingFields{
    //auditing fields를 extends
    /*@Id private String id;
    @Setter private String deleted;
    @Setter private String createdAt;
    @Setter private String modifiedAt;*/

    @Setter @Column(nullable = false, unique = true) private String username;
    @Setter @Column(nullable = false) private String password;
    @Setter @Column(nullable = true) private String name;
    @Setter @Column(nullable = true) private String nick;
    @Setter @Column(nullable = true) private String phone;
    @Setter @Column(nullable = true) private String gender;
    @Setter @Column(nullable = true, length = 10000) @Lob private String content;
    @Setter @Column(nullable = true) private String img;

    protected Tbuser(){}
    private Tbuser(String username, String password, String name, String nick, String phone, String gender , String content, String img){
        this.username = username;
        this.password = password;
        this.name = name;
        this.nick = nick;
        this.phone = phone;
        this.gender = gender;
        this.content = content;
        this.img = img;
    }
    public static Tbuser of(String username, String password, String name, String nick, String phone, String gender , String content, String img){
        return new Tbuser(username, password, name, nick, phone, gender, content, img);
    }
    public TbuserDto.CreateResDto toCreateResDto(){
        /*TbpostDto.CreateResDto createResDto1 = new TbpostDto.CreateResDto();
        createResDto1.setId(this.getId());*/
        return TbuserDto.CreateResDto.builder().id(this.getId()).build();
    }
}
