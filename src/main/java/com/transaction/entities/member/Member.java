package com.transaction.entities.member;

import com.transaction.entities.board.Board;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "member", schema = "DB_TEST")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String name;

    private int age;

    public static Member createMember(String name, int age) {
        Member member = new Member();
        member.setName(name);
        member.setAge(age);

        return member;
    }
}
