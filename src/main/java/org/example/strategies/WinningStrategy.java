package org.example.strategies;

import org.example.models.Board;
import org.example.models.Move;

import java.util.List;

public interface WinningStrategy {
    boolean checkWinner(Board board, Move move);
    void undoMove(List<Move> moves);
}
