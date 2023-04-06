package com.example.spring.first.hellospring.repository;

import com.example.spring.first.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

// 아직 DB가 선정되지 않았음을 가정하고 인터페이스로 구현
// 개발 초기 단계에서는 가벼운 메모리 기반 저장 형태로 구현
public interface MemberRepository {

    Member save(Member member);
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
