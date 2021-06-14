package com.example.kata.controller;

import com.example.kata.entity.Movement;
import com.example.kata.response.GameResponse;
import com.example.kata.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.ValidationException;

@RestController
@RequestMapping("/game")
public class GameController {

    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping
    public GameResponse create() {
        return this.gameService.createGame();
    }

    @PostMapping("{id}/movement")
    public GameResponse doMovement(@RequestBody Movement movement) throws ValidationException {
        return this.gameService.makeAMove(movement);
    }
}
