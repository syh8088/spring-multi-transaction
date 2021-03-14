package com.transaction.domain.role;

import com.transaction.entities.role.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface RoleMapper {
    void setRole(InsertRoleDto insertRoleDto);
}
