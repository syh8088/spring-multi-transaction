package com.transaction.entities.board;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "board", schema = "DB_TEST2")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    private String title;

    private String content;

    public static Board createBoard(String title, String content) {
        Board board = new Board();
        board.setTitle(title);
        board.setContent(content);

        return board;
    }

}