package com.example.kata.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@Getter
@Setter
public class GameResponse {

    private Integer id;
    private Character firstPlayer;
    private String msg;
    private String winner;

    public GameResponse(Integer id, Character firstPlayer) {
        this.id = id;
        this.firstPlayer = firstPlayer;
    }

}
