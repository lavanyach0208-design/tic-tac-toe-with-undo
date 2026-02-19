package org.example.strategies;

import org.example.models.Board;
import org.example.models.Move;
import org.example.models.Symbol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ColumnWinningStrategy implements WinningStrategy {
    private Map<Integer, Map<Symbol,Integer>> colSymbolCountMap=new HashMap<>();
    @Override
    public boolean checkWinner(Board board, Move move) {

        int col=move.getCell().getCol();
        Symbol symbol=move.getPlayer().getSymbol();
        if(!colSymbolCountMap.containsKey(col)){
            colSymbolCountMap.put(col,new HashMap<>());
        }

        Map<Symbol,Integer> symbolCountMap=colSymbolCountMap.get(col);
        if(!symbolCountMap.containsKey(symbol)){
            symbolCountMap.put(symbol,0);
        }
        colSymbolCountMap.get(col).put(symbol,symbolCountMap.get(symbol)+1);


        if(colSymbolCountMap.get(col).get(symbol) == board.getDimension()){
            return true;
        }
        return false;
    }

    public void undoMove(List<Move> moves) {
        Move move=moves.get(moves.size()-1);
        Symbol symbol=move.getPlayer().getSymbol();
        int col=move.getCell().getCol();
        Map<Symbol,Integer> symbolCountMap=colSymbolCountMap.get(col);
        colSymbolCountMap.get(col).put(symbol,symbolCountMap.get(symbol)-1);
    }
}
