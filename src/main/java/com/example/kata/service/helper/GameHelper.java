package com.example.kata.service.helper;

import com.example.kata.entity.Game;
import com.example.kata.entity.Movement;
import com.example.kata.entity.Player;
import com.example.kata.entity.Position;

import javax.xml.bind.ValidationException;

public class GameHelper {

    /**
     * Fill the new Board
     *
     * @return
     */
    public static Character[][] newBoard() {
        Character[][] board = new Character[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }

        return board;
    }

    /**
     * Which player must play in this turn
     *
     * @param game
     * @return
     */
    public static Player findTurnPlayer(Game game) {
        return game.getPlayerOne().getIsTurn() ? game.getPlayerOne() : game.getPlayerTwo();
    }

    /**
     * Changes for the player that must play in this turn
     *
     * @param game
     */
    public static void changePlayer(Game game) {
        if (game.getPlayerOne().getIsTurn()) {
            game.getPlayerOne().setIsTurn(false);
            game.getPlayerTwo().setIsTurn(true);
        } else {
            game.getPlayerOne().setIsTurn(true);
            game.getPlayerTwo().setIsTurn(false);
        }
    }

    /**
     * Validate the movement of the player
     *
     * @param game
     * @param movement
     */
    private static void validateMovement(Game game, Movement movement) throws ValidationException {

        Position realPosition = GameHelper.getRealPosition(movement.getPosition());

        if (realPosition.getX() < 0 || realPosition.getX() > 3 || realPosition.getY() < 0 || realPosition.getY() > 3) {
            throw new ValidationException("position.outOfBounds");
        } else if (GameHelper.findTurnPlayer(game).getType() != movement.getPlayer()) {
            throw new ValidationException("player.cantPlay", String.valueOf(movement.getPlayer()));
        } else if (game.getBoard()[realPosition.getX()][realPosition.getY()] != '-') {
            throw new ValidationException("position.filled", String.valueOf(movement.getPlayer()));
        }
    }

    /**
     * Do the movement
     *
     * @param game
     * @param movement
     */
    public static void doMovement(Game game, Movement movement) throws ValidationException {
        GameHelper.validateMovement(game, movement);
        Position realPosition = GameHelper.getRealPosition(movement.getPosition());
        game.getBoard()[realPosition.getX()][realPosition.getY()] = movement.getPlayer();
    }

    /**
     * Check if the char on the line is equal
     *
     * @param board
     * @return
     */
    private static Boolean checkRows(Character[][] board) {
        for (int i = 0; i < 3; i++) {
            if (checkIfIsEqual(board[i][0], board[i][1], board[i][2])) {
                return true;
            }
        }

        return false;
    }

    /**
     * Check if the char on the line is equal
     *
     * @param board
     * @return
     */
    private static Boolean checkColumns(Character[][] board) {
        for (int i = 0; i < 3; i++) {
            if (checkIfIsEqual(board[0][i], board[1][i], board[2][i])) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check the lines
     *
     * @param board
     * @return
     */
    private static Boolean checkDiagonal(Character[][] board) {
        return ((checkIfIsEqual(board[0][0], board[1][1], board[2][2])) || (checkIfIsEqual(board[0][2], board[1][1], board[2][0])));
    }

    /**
     * Check if the chars are different of -
     *
     * @param c1
     * @param c2
     * @param c3
     * @return
     */
    private static Boolean checkIfIsEqual(Character c1, Character c2, Character c3) {
        return ((!c1.equals('-')) && (c1.equals(c2)) && (c2.equals(c3)));
    }

    /**
     * Check if there is a winner
     *
     * @param board
     * @return
     */
    public static Boolean checkWinner(Character[][] board) {
        return (GameHelper.checkRows(board) || GameHelper.checkColumns(board) || GameHelper.checkDiagonal(board));
    }

    /**
     * Check the board is full and there is no winner
     *
     * @param board
     * @return
     */
    public static Boolean isDraw(Character[][] board) {

        boolean isDraw = true;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j].equals('-')) {
                    isDraw = false;
                }
            }
        }

        return isDraw;
    }

    /**
     * Check the real position
     *
     * @param position
     * @return
     */
    private static Position getRealPosition(Position position) {
        if (position.getX() == 0 && position.getY() == 0) {
            return new Position(2, 0);
        } else if (position.getX() == 2 && position.getY() == 0) {
            return new Position(2, 2);
        } else if (position.getX() == 2 && position.getY() == 2) {
            return new Position(0, 2);
        } else if (position.getX() == 0 && position.getY() == 2) {
            return new Position(0, 0);
        } else if (position.getX() == 1 && position.getY() == 2) {
            return new Position(0, 1);
        } else if (position.getX() == 1 && position.getY() == 0) {
            return new Position(2, 1);
        } else {
            return new Position(position.getY(), position.getX());
        }
    }
}
