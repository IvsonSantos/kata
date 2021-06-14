package com.example.kata.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "game")
@Getter
@Setter
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Integer id;

    @Column(name = "winner")
    private String winner;

    @Column(name = "board")
    private Character[][] board;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_player_one")
    private Player playerOne;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_player_two")
    private Player playerTwo;

    public Game() {
        this.board = new Character[3][3];
        this.playerOne = new Player(Player.TYPE_X, true);
        this.playerTwo = new Player(Player.TYPE_O, false);
    }

}
