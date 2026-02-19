package org.example.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Scanner;

@Getter
@Setter
public class Player {
    private String name;
    private Symbol symbol;
    private PlayerType type;
    private static Scanner sc=new Scanner(System.in);
    public Player(String name,PlayerType type)
    {
        this.name=name;
    }

    public Player(String name, Symbol symbol) {
        this.name = name;
        this.symbol = symbol;
        this.type=PlayerType.HUMAN;
    }

    public Move makeMove(Board board) {
        System.out.println("Enter the row (or type UNDO): ");
        String input=sc.next();
        // if player wants to undo
        if(input.equalsIgnoreCase("UNDO")){
            Move move=new Move(this,null);
            move.setUndoMove(true);
            return move;
        }

        int row=Integer.parseInt(input);

        System.out.println("Enter the column for move: ");
        int col=sc.nextInt();
        return new Move(this,new Cell(row, col));

    }
}
