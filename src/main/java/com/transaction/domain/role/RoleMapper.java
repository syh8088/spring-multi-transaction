package com.transaction.domain.role;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface RoleMapper {
    void setRole(InsertRoleDto insertRoleDto);
}
