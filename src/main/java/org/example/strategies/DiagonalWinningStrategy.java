package org.example.strategies;

import org.example.models.Board;
import org.example.models.Move;
import org.example.models.Symbol;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiagonalWinningStrategy implements WinningStrategy {
    private Map<Symbol,Integer> primaryDiagonalCount=new HashMap<>();
    private Map<Symbol,Integer> secondaryDiagonalCount=new HashMap<>();
        int dimension;
    public DiagonalWinningStrategy(int dimension) {
        this.dimension=dimension;
    }

    @Override
    public boolean checkWinner(Board board, Move move) {
        int Row=move.getCell().getRow();
        int Col=move.getCell().getCol();
        int dimension=board.getDimension();
        Symbol symbol=move.getPlayer().getSymbol();

        // primary diagonal
        if(Row == Col){
            primaryDiagonalCount.put(symbol,primaryDiagonalCount.getOrDefault(symbol,0)+1);

            if(primaryDiagonalCount.get(symbol) == dimension){
                return true;
            }
        }

        // Secondary diagonal

        if(Row+Col == dimension-1){
            secondaryDiagonalCount.put(symbol,secondaryDiagonalCount.getOrDefault(symbol,0)+1);
            if(secondaryDiagonalCount.get(symbol) == dimension){
                return true;
            }
        }
        return false;

    }

    @Override
    public void undoMove(List<Move> moves) {
        Move move=moves.get(moves.size()-1);
        Symbol symbol=move.getPlayer().getSymbol();
        int row=move.getCell().getRow();
        int col=move.getCell().getCol();

        // if in primary
        if(row == col){
            primaryDiagonalCount.put(symbol,primaryDiagonalCount.get(symbol)-1);
        }
        // if in secondary
        if(row+col == dimension-1){
            secondaryDiagonalCount.put(symbol,secondaryDiagonalCount.get(symbol)-1);
        }
    }
}
