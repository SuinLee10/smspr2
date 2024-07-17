package com.example.smspr2.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@ToString
@EntityListeners(AuditingEntityListener.class) // 이게 있어야 자동으로 값 넣어줌
@MappedSuperclass
public abstract class AuditingFields {

    @Id
    private String id;

    @Column(nullable = false, unique = false) //false 기본 값 명시적으로 선언 //이거는 테이블 컬럼에 속성을 주기 위함입니다. not null
    @Setter
    protected String deleted;

    @Column(nullable = true)
    @Setter
    protected String process;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @CreatedDate
    @Column(nullable = false, updatable = false)
    protected LocalDateTime createdAt; // 생성일시

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @LastModifiedDate
    @Column(nullable = false)
    protected LocalDateTime modifiedAt; // 수정일시

    @PrePersist
    public void onPrePersist() {
        this.id = UUID.randomUUID().toString().replace("-", "");
        this.deleted = "N";
    }
}