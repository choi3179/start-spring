package com.example.spring.first.hellospring.repository;

import com.example.spring.first.hellospring.domain.Member;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.*;

//@Repository
//  @Repository 애노테이션으로 스프링이 시작될 때 스프링 컨테이너에 바로 등록
public class MemoryMemberRepository implements MemberRepository{

    // 실무에서는 동시성 문제를 고려한 ConcurrentHashMap, AtomicLong 사용 가능
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        // get 했을 경우 null이 반환될 수 있으므로 ofNullable로 감싸서 클라이언트에서 후처리 가능한 형태로 사용가능
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()          // stream : 결과값 loop
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        // 실무에서 List를 사용할 경우가 많음
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
