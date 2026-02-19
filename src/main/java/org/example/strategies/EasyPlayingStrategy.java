package org.example.strategies;

import org.example.models.Board;
import org.example.models.Cell;
import org.example.models.CellStatus;
import org.example.models.Move;

import java.util.List;

public class EasyPlayingStrategy implements BotPlayingStrategy {

    @Override
    public Move makeMove(Board board) {
        for(List<Cell> boardCells: board.getBoardCells()){
            for(Cell cell:boardCells){
                if(cell.getStatus() == CellStatus.EMPTY){
                    return new Move(null, cell);
                }
            }
        }
        return null;
    }
}
