package com.example.spring.first.hellospring.repository;

import com.example.spring.first.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;


public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    //  @AfterEach 애노테이션으로 하나의 테스트가 종료된 후의 액션을 지정해 줄 수 있다.
    //  한 번 테스트 이후 메모리 DB에 직전 테스트 결과가 남아 있을 수 있으므로 repository clear 호출
    //  @BeforeEach : 한 번의 테스트 실행 직전의 액션 지정
    @AfterEach
    public void afterEach() {

        repository.clearStore();
    }

    @Test
    public void save() {

        //given     --> 주어진 테스트 내용
        Member member = new Member();
        member.setName("spring");

        //when      --> 무엇을 검증할 것인가?
        repository.save(member);
        //then      --> 검증하는 내용
        Member result = repository.findById(member.getId()).get();

        //  Assertions 기능을 이용하여 매번 디버깅 로그를 작성하여 확인하는 것이 아닌
        //  기능적으로 Test 케이스의 유효성을 확인할 수 있음
        //  import static org.assertj.core.api.Assertions.*;
        assertThat(result).isEqualTo(member);
    }
    @Test
    public void findByName() {
        //given
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        //when
        Member result = repository.findByName("spring1").get();
        //then
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {

        //given
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        //when
        List<Member> result = repository.findAll();
        //then
        assertThat(result.size()).isEqualTo(2);

    }

}
