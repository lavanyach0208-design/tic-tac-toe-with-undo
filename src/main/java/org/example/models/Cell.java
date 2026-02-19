package org.example.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cell {
    private int row;
    private int col;
    private Player player;
    private CellStatus Status;

    public Cell(int i, int j) {
        this.row = i;
        this.col = j;
        this.Status=CellStatus.EMPTY;
    }

}
