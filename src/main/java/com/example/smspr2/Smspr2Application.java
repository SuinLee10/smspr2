package com.example.smspr2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
//JPAAuditing 가능하게 해줘 => entity class의 annotation에
@EnableJpaAuditing //entity에서 뭔가 일어날 때 값을 넣어줄 부탁
@SpringBootApplication
public class Smspr2Application {

    public static void main(String[] args) {
        SpringApplication.run(Smspr2Application.class, args);
    }

}
