package hello.hellospring.service;

import hello.hellospring.Repository.MemoryMemberRepository;
import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void BeforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void join() {
        //given
        Member member = new Member();
        member.setName("hello");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertEquals(member.getName(),findMember.getName());
    }

    @Test
    public void duplicateMemberTest() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        //given
        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        Assertions.assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        /*
        try {
            memberService.join(member2);
            Assertions.fail();
        } catch (IllegalStateException e) {
            Assertions.assertEquals(e.getMessage(), "이미 존재하는 회원입니다.");
        }
         */
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}