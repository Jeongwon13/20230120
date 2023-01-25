package hello.hispring.service;

import hello.hispring.domain.Member;
import hello.hispring.repository.MemberRepository;
import hello.hispring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    }
    /**
     * 회원 가입
     */
    public Long join(Member member){
        //같은 이름이 있는 중복 확인X
        vaildateDuplicateMember(member);
        memberRepository.save(member); //중복 회원 검증
        return member.getId();
    }

    private void vaildateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
             .ifPresent(member1  ->{
                  throw new IllegalStateException("이미 존재하는 회원입니다.");
         });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers(){
     return  memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }

}
