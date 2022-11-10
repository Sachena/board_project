package com.example.board_project.service;

import com.example.board_project.domain.Member;
import com.example.board_project.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

//    @Autowired
//    EntityManager em;

    @Test
    // 테스트 내용 DB에 반영 원할시
    // @Rollback(false)
    public void 회원가입() throws Exception{
        //given
        Member member = new Member();
        member.setName("kim");

        //when
        Long memberId = memberService.join(member);

        // 테스트 내용 DB에 반영 원할시
//        em.flush();

        //then
        assertEquals(member, memberRepository.findOne(memberId));

    }

    @Test
    public void 중복회원() throws Exception{
        //given
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");

        //when
        memberService.join(member1);
        IllegalStateException thrown = assertThrows(IllegalStateException.class,
                () -> memberService.join(member2));

        //then

        assertEquals("이미 존재하는 회원입니다.", thrown.getMessage());

    }

}