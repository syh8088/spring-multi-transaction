package com.transaction.domain;

import com.transaction.domain.member.MemberService;
import com.transaction.domain.role.RoleService;
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
//@Transactional(transactionManager = "mainTxManager")
@Transactional(transactionManager = "subTxManager")
public class TransactionService {

    private final MemberService memberService;
    private final RoleService roleService;

    public void required_required() {
        System.out.println("LogicService --> currentTransactionName : {}  = " +  TransactionSynchronizationManager.getCurrentTransactionName());
        Member member = memberService.saveWithRequired();
        roleService.saveWithRequired(member.getId());
    }

    public void jpa_required_required() {
        System.out.println("LogicService --> currentTransactionName : {}  = " +  TransactionSynchronizationManager.getCurrentTransactionName());
        Member member = memberService.jpaSaveWithRequired();
        roleService.jpaSaveWithRequired(member);
    }

    public void mybatis_jpa_required_required() {
        System.out.println("LogicService --> currentTransactionName : {}  = " +  TransactionSynchronizationManager.getCurrentTransactionName());
        Member member = memberService.saveWithRequired();
        roleService.jpaSaveWithRequired(member);
    }

    public void inner_mybatis_jpa_required_required() {
        System.out.println("LogicService --> currentTransactionName : {}  = " +  TransactionSynchronizationManager.getCurrentTransactionName());
        Member member = memberService.jpaAndMybatisSaveWithRequired();
        roleService.jpaAndMybatisSaveWithRequired(member);
    }

    public void required_requiresNew() {
        Member member = memberService.saveWithRequired();
        roleService.saveWithRequiresNew(member.getId());
    }

    public void required_nested() {
        Member member = memberService.saveWithRequired();
        roleService.saveWithNested(member.getId());
    }

    public void requiresNew_required() {
        Member member = memberService.saveWithRequiresNew();
        roleService.saveWithRequired(member.getId());
    }

    public void requiresNew_requiresNew() {
        Member member = memberService.saveWithRequiresNew();
        roleService.saveWithRequiresNew(member.getId());
    }

    public void requiresNew_nested() {
        Member member = memberService.saveWithRequiresNew();
        roleService.saveWithNested(member.getId());
    }

    public void nested_required() {
        Member member = memberService.saveWithNested();
        roleService.saveWithRequired(member.getId());
    }

    public void nested_requiresNew() {
        Member member = memberService.saveWithNested();
        roleService.saveWithRequiresNew(member.getId());
    }

    public void nested_nested() {
        Member member = memberService.saveWithNested();
        roleService.saveWithNested(member.getId());
    }

    public void notSupported_required() {
        Member member = memberService.saveWithNotSupported();
        roleService.saveWithRequired(member.getId());
    }

    public void notSupported_requiresNew() {
        Member member = memberService.saveWithNotSupported();
        roleService.saveWithRequiresNew(member.getId());
    }

    public void notSupported_nested() {
        Member member = memberService.saveWithNotSupported();
        roleService.saveWithNested(member.getId());
    }

    public void notSupported_notSupported() {
        Member member = memberService.saveWithNotSupported();
        roleService.saveWithNotSupported(member.getId());
    }

    public void jpa_notSupported_notSupported() {
        Member member = memberService.jpaSaveWithNotSupported();
        roleService.jpaSaveWithNotSupported(member);
    }
}
