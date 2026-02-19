package org.example.Controllers;

import org.example.exceptions.InvalidMoveException;
import org.example.models.Game;
import org.example.models.Player;
import org.example.strategies.WinningStrategy;

import java.util.List;

public class GameController {
    public Game startGame(int dimension, List<Player> players, List<WinningStrategy> strategies){
        return  Game.builder()
                .dimension(dimension)
                .players(players)
                .winningStrategies(strategies)
                .build();
    }
    public void printBoard(Game game){
        game.printBoard();
    }

    public void makeMove(Game game) throws InvalidMoveException {
        game.makeMove();
    }
}
