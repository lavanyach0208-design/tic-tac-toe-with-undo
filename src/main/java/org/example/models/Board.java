package org.example.models;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Board {
    private int dimension;
   private  List<List<Cell>> boardCells;
   public Board(int dimension) {
       this.dimension=dimension;
       this.boardCells=new ArrayList<>();
       for(int i=0;i<dimension;i++){
           boardCells.add(new ArrayList<>());
       for(int j=0; j < dimension; j++){
           boardCells.get(i).add(new Cell(i,j));
       }
   }
}

public  void printBoard(){
       for(List<Cell> cells: boardCells){
           for(Cell cell: cells){
               if(cell.getStatus() ==  CellStatus.EMPTY){
                   System.out.print("|   |");
               }else{
                   System.out.print("| "+cell.getPlayer().getSymbol().getSymbol()+" |");
               }
           }
           System.out.println();
       }
}
}
