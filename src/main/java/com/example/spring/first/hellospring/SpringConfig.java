package com.example.spring.first.hellospring;

import com.example.spring.first.hellospring.repository.JdbcMemberRepository;
import com.example.spring.first.hellospring.repository.MemberRepository;
import com.example.spring.first.hellospring.repository.MemoryMemberRepository;
import com.example.spring.first.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;


//  자바 코드로 직접 Bean 등록하기
@Configuration
public class SpringConfig {

    private final DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {

        //return new MemoryMemberRepository();
        //return new JdbcMemberRepository(dataSource);
        return new JdbcMemberRepository(dataSource);
    }
}
