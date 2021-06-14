package com.example.kata.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "player")
@Getter
@Setter
@NoArgsConstructor
public class Player {

    public static final char TYPE_X = 'X';
    public static final char TYPE_O = 'O';

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Integer id;

    @Column(name = "type")
    private char type;

    @Column(name = "isTurn")
    private Boolean isTurn;

    public Player(char type, Boolean isTurn) {
        this.type = type;
        this.isTurn = isTurn;
    }

}
