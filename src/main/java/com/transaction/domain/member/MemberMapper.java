package com.transaction.domain.member;

import com.transaction.entities.member.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface MemberMapper {
    void setMember(Member member);
}
