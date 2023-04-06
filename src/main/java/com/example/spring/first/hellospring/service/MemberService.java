package com.example.spring.first.hellospring.service;

import com.example.spring.first.hellospring.domain.Member;
import com.example.spring.first.hellospring.repository.MemberRepository;
import com.example.spring.first.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//@Service
//  @Service 애노테이션으로 스프링이 시작될 때 스프링 컨테이너에 바로 등록
public class MemberService {

    private final MemberRepository memberRepository;

    //@Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     */
    public Long join(Member member) {
        ValidateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void ValidateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
        //  Optional 에서는 다양한 메소드를 제공
        //  ifPresent : 존재할 경우의 동작 지정
                .ifPresent(member1 -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다,");
                });
    }

    /**
     *전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

}
