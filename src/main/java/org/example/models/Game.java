    package org.example.models;

    import lombok.Getter;
    import lombok.Setter;
    import org.example.exceptions.InvalidMoveException;
    import org.example.strategies.WinningStrategy;

    import java.util.ArrayList;
    import java.util.List;

    @Getter
    @Setter
    public class Game {
        private Board board;
        private List<Player> players;
        private List<Move> moves;
        private Player Winner;
        private GameStatus status;
        private int nextPlayerIndex;
        private List<WinningStrategy> winningStrategies;

        public static Builder builder(){
            return new Builder();
        }

        public void printBoard(){
            board.printBoard();
        }
        private Game(int dimension,List<Player> players,List<WinningStrategy> winningStrategies){
            this.board=new Board(dimension);
            this.players=players;
            this.moves=new ArrayList<>();
            this.Winner=null;
            this.status=GameStatus.IN_PROGRESS;
            this.nextPlayerIndex=0;
            this.winningStrategies=winningStrategies;

        }

        public void makeMove() throws InvalidMoveException {
            Player currentPlayer=players.get(nextPlayerIndex);
            System.out.println("Current turn: "+currentPlayer.getName());
            // move logic to be implemented

            // Ask player for their move
            Move move=currentPlayer.makeMove(board);

            // Handle undo request
            if(move.isUndoMove()){
                Move lastMove=moves.get(moves.size()-1);
                UndoMove();
                // If it was bot game and last move was bot,
                 // undo one more move
                if(!moves.isEmpty() && lastMove.getPlayer() instanceof Bot){
                    UndoMove();
                }

                return;
            }
            if(!validateMove(move)){
                System.out.println("Invalid move try again.");
                return;
            }

            // Apply the move to the board
            int row=move.getCell().getRow();
            int col=move.getCell().getCol();

            Cell cell=board.getBoardCells().get(row).get(col);
            cell.setPlayer(currentPlayer);
            cell.setStatus(CellStatus.FILLED);

            //Add move to moves history
            moves.add(move);


            // Check for Winner
            if(checkWinner(move)){
                this.Winner=currentPlayer;
                this.status=GameStatus.WIN;
                return;
            }

            if(moves.size() == board.getDimension() * board.getDimension()){
                this.status=GameStatus.DRAW;
                return;
            }

            // Update next player
            nextPlayerIndex=(nextPlayerIndex+1) %  players.size();
                this.status=GameStatus.IN_PROGRESS;


        }

        // Undo Move

            public void UndoMove(){

                if(moves.isEmpty()){
                    System.out.println("No moves to undo!");
                    return;
                }

                // Reset the symbol count in strategy's
                for(WinningStrategy winningStrategy:winningStrategies){
                    winningStrategy.undoMove(moves);
                }


                // Get the last move
                Move lastMove=moves.remove(moves.size()-1);
                int row=lastMove.getCell().getRow();
                int col=lastMove.getCell().getCol();
                Cell cell=board.getBoardCells().get(row).get(col);
                // Reset the cell
                cell.setPlayer(null);
                cell.setStatus(CellStatus.EMPTY);

                // Set the next player index back
                 nextPlayerIndex=players.indexOf(lastMove.getPlayer());

                 // Reset the game status
                this.Winner=null;
                this.status=GameStatus.IN_PROGRESS;

                System.out.println("Last move undone by player: "+lastMove.getPlayer().getName());

            }

        private boolean checkWinner(Move move) {
            for(WinningStrategy winningStrategy:winningStrategies){
                if(winningStrategy.checkWinner(board,move)){
                    return true;
                }
            }
            return false;
        }

        private boolean validateMove(Move move) {
            Player player=move.getPlayer();
            Cell cell=move.getCell();
            int col=cell.getCol();
            int row=cell.getRow();

            if(row < 0 || row >= board.getDimension() ||
                    col < 0 || col >= board.getDimension() ||
                    board.getBoardCells().get(row).get(col).getStatus() != CellStatus.EMPTY){

                return false;

            }
            return true;
        }

        public static class Builder{
            private int dimension;
            private List<Player> players;
            private List<WinningStrategy> winningStrategies;

            public  Builder dimension(int dimension){
                this.dimension=dimension;
                return this;
            }
            public Builder players(List<Player> players){
                this.players=players;
                return this;
            }
            public Builder winningStrategies(List<WinningStrategy> winningStrategies){
                this.winningStrategies=winningStrategies;
                return this;
            }
            public Game build(){
                validateBotCount();
                return new Game(dimension,players,winningStrategies);
            }
            public void validateBotCount(){
                int botCount=0;
                for(Player p:players){
                    if(p.getType() == PlayerType.BOT){
                        botCount++;
                    }
                }
                if(botCount > 1){
                    throw new IllegalArgumentException("Bot count can't exceed 1");
                }
            }
        }

}
