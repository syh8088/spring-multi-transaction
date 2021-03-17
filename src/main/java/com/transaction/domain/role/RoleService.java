package com.transaction.domain.role;

import com.transaction.entities.member.Member;
import com.transaction.entities.role.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Service
@RequiredArgsConstructor
//@Transactional(transactionManager = "mainTransactionManager")
//@Transactional(transactionManager = "subTransactionManager")
public class RoleService {

    private final RoleMapper roleMapper;
    private final RoleRepository roleRepository;

    public void saveWithRequired(Long insertedMemberId) {
        System.out.println("LogicService --> currentTransactionName : {}  = " +  TransactionSynchronizationManager.getCurrentTransactionName());
        InsertRoleDto role = InsertRoleDto.createRole(insertedMemberId, "mybatis-name-test");
        roleMapper.setRole(role);
        throw new IllegalStateException("this method throw exception");
    }

    public void jpaSaveWithRequired(Member member) {
        System.out.println("LogicService --> currentTransactionName : {}  = " +  TransactionSynchronizationManager.getCurrentTransactionName());

        Role one = roleRepository.getOne(10L);
        Role role = Role.createRole(member, "jpa-name-test");
        roleRepository.save(role);
        throw new IllegalStateException("this method throw exception");
    }

    public void jpaAndMybatisSaveWithRequired(Member member) {
        System.out.println("LogicService --> currentTransactionName : {}  = " +  TransactionSynchronizationManager.getCurrentTransactionName());
        Role role = Role.createRole(member, "jpaAndMybatis-name1-test");
        roleRepository.save(role);

        InsertRoleDto MybatisRole = InsertRoleDto.createRole(member.getId(), "jpaAndMybatis-name2-test");
        roleMapper.setRole(MybatisRole);
        throw new IllegalStateException("this method throw exception");
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public int saveWithRequiresNew(Long insertedMemberId) {
        InsertRoleDto MybatisRole = InsertRoleDto.createRole(insertedMemberId, "mybatis-REQUIRES_NEW-name-test");
        roleMapper.setRole(MybatisRole);

        throw new IllegalStateException("this method throw exception");
    }

    @Transactional(propagation = Propagation.NESTED)
    public int saveWithNested(Long insertedMemberId) {
        InsertRoleDto MybatisRole = InsertRoleDto.createRole(insertedMemberId, "mybatis-NESTED-name-test");
        roleMapper.setRole(MybatisRole);

        throw new IllegalStateException("this method throw exception");
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public int saveWithNotSupported(Long insertedMemberId) {
        InsertRoleDto MybatisRole = InsertRoleDto.createRole(insertedMemberId, "mybatis-NOT_SUPPORTED-name-test");
        roleMapper.setRole(MybatisRole);

        throw new IllegalStateException("this method throw exception");
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public int jpaSaveWithNotSupported(Member member) {
        System.out.println("LogicService --> currentTransactionName : {}  = " +  TransactionSynchronizationManager.getCurrentTransactionName());

        Role role = Role.createRole(member, "jpa-NOT_SUPPORTED-name-test");
        roleRepository.save(role);

        throw new IllegalStateException("this method throw exception");
    }
}
