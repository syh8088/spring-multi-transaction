package com.transaction.domain.member;

import com.transaction.entities.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Service
@RequiredArgsConstructor
//@Transactional(transactionManager = "mainTransactionManager")
//@Transactional(transactionManager = "subTransactionManager")

//@Transactional
//@Transactional(propagation = Propagation.REQUIRES_NEW)
public class MemberService {

    private final MemberMapper memberMapper;
    private final MemberRepository memberRepository;

    //@Transactional(propagation = Propagation.REQUIRES_NEW)
    public Member saveWithRequired() {
        System.out.println("LogicService --> currentTransactionName : {}  = " +  TransactionSynchronizationManager.getCurrentTransactionName());

        Member member = Member.createMember("name-mybatis-test", 10);
        memberMapper.setMember(member);

        return member;
    }

    public Member jpaSaveWithRequired() {
        System.out.println("LogicService --> currentTransactionName : {}  = " +  TransactionSynchronizationManager.getCurrentTransactionName());
        Member one = memberRepository.getOne(142L);
        Member member = Member.createMember("name-jpa-test", 10);
        memberRepository.save(member);

        return member;
    }

    public Member jpaAndMybatisSaveWithRequired() {
        System.out.println("LogicService --> currentTransactionName : {}  = " +  TransactionSynchronizationManager.getCurrentTransactionName());
        Member mybatisMember = Member.createMember("name-jpaAndMybatis-test", 40);
        memberMapper.setMember(mybatisMember);
        Member jpaMember = Member.createMember("name-jpaAndMybatis-test", 30);
        memberRepository.save(jpaMember);

        return jpaMember;
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Member saveWithNotSupported() {
        System.out.println("LogicService --> currentTransactionName : {}  = " +  TransactionSynchronizationManager.getCurrentTransactionName());
        Member mybatisMember = Member.createMember("name-NOT_SUPPORTED", 20);
        memberMapper.setMember(mybatisMember);

        return mybatisMember;
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Member jpaSaveWithNotSupported() {
        System.out.println("LogicService --> currentTransactionName : {}  = " +  TransactionSynchronizationManager.getCurrentTransactionName());
        Member member = Member.createMember("name-NOT_SUPPORTED", 20);
        memberRepository.save(member);

        return member;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Member saveWithRequiresNew() {
        System.out.println("LogicService --> currentTransactionName : {}  = " +  TransactionSynchronizationManager.getCurrentTransactionName());
        Member mybatisMember = Member.createMember("name-REQUIRES_NEW", 30);
        memberMapper.setMember(mybatisMember);

        return mybatisMember;
    }

    @Transactional(propagation = Propagation.NESTED)
    public Member saveWithNested() {
        System.out.println("LogicService --> currentTransactionName : {}  = " +  TransactionSynchronizationManager.getCurrentTransactionName());
        Member mybatisMember = Member.createMember("name-NESTED", 40);
        memberMapper.setMember(mybatisMember);

        return mybatisMember;
    }


}
