package com.transaction.entities.role;

import com.transaction.entities.member.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "role", schema = "DB_TEST2")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToOne(fetch = FetchType.LAZY)
    private Member member;

    private String name;

    public static Role createRole(Member member, String name) {
        Role role = new Role();
        role.setMember(member);
        role.setName(name);

        return role;
    }
}
