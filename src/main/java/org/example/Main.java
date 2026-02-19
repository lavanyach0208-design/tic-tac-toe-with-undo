package org.example;

import org.example.Controllers.GameController;
import org.example.exceptions.InvalidMoveException;
import org.example.models.*;
import org.example.strategies.ColumnWinningStrategy;
import org.example.strategies.DiagonalWinningStrategy;
import org.example.strategies.RowWinningStrategy;
import org.example.strategies.WinningStrategy;

import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws InvalidMoveException {
        System.out.println("Hello World!");

        int dimension=3;
        System.out.println("Tic Tac Toe Game of dimension: "+dimension);

        List<Player> players=new ArrayList<>();
      //  players.add(new Player("Lavanya",new Symbol("x")));
        // players.add(new Player("Roopa",new Symbol("O")));
         players.add(new Bot("Robert",new Symbol("O"),BotDifficultyLevel.EASY));
        players.add(new Player("Lavanya",new Symbol("x")));


        List<WinningStrategy> strategyes=new ArrayList<>();
        strategyes.add(new RowWinningStrategy());
        strategyes.add(new ColumnWinningStrategy());
        strategyes.add(new DiagonalWinningStrategy(dimension));


        GameController gameController=new GameController();

        Game game=gameController.startGame(dimension,players,strategyes);
        System.out.println("Game started with ID "+ game.getStatus());


        while(game.getStatus() == GameStatus.IN_PROGRESS){
            // Game loop logic to be implemented
            gameController.printBoard(game);
            gameController.makeMove(game);
        }

        if(game.getStatus() == GameStatus.WIN){
            if(game.getWinner()!=null){
                System.out.println("Winner is:"+game.getWinner().getName());
            }
        }else{
            System.out.println("Game ended in a draw!");
        }
    }
}
