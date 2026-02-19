package org.example.strategies;

import org.example.models.Board;
import org.example.models.Move;
import org.example.models.Symbol;

import java.util.HashMap;
import java.util.Map;
import java.util.*;

public class RowWinningStrategy implements  WinningStrategy {

    private Map<Integer, Map<Symbol,Integer>> rowSymbolCountMap=new HashMap<>();
    @Override
    public boolean checkWinner(Board board, Move move) {

        int row=move.getCell().getRow();
        Symbol symbol=move.getPlayer().getSymbol();
        if(!rowSymbolCountMap.containsKey(row)){
            rowSymbolCountMap.put(row,new HashMap<>());
        }

        Map<Symbol,Integer> symbolCountMap=rowSymbolCountMap.get(row);
        if(!symbolCountMap.containsKey(symbol)){
            symbolCountMap.put(symbol,0);
        }
        rowSymbolCountMap.get(row).put(symbol,symbolCountMap.get(symbol)+1);


        if(rowSymbolCountMap.get(row).get(symbol) == board.getDimension()){
            return true;
        }
        return false;
    }

    @Override
    public void undoMove(List<Move> moves) {
        Move move=moves.get(moves.size()-1);
        Symbol symbol=move.getPlayer().getSymbol();
        int row=move.getCell().getRow();
        Map<Symbol,Integer> symbolCountMap=rowSymbolCountMap.get(row);
       rowSymbolCountMap.get(row).put(symbol,symbolCountMap.get(symbol)-1);
    }

}
