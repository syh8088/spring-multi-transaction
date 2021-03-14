package com.transaction.domain.role;

import com.transaction.entities.role.Role;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class InsertRoleDto {
    private int id;
    private int memberId;
    private String name;

    public static InsertRoleDto createRole(int memberId, String name) {
        InsertRoleDto insertRoleDto = new InsertRoleDto();
        insertRoleDto.setMemberId(memberId);
        insertRoleDto.setName(name);

        return insertRoleDto;
    }
}
