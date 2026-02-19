package org.example.models;

import org.example.strategies.BotPlayingStrategy;
import org.example.strategies.BotPlayingStrategyFactory;

public class Bot extends Player{
   private BotDifficultyLevel botDifficultyLevel;
   private BotPlayingStrategy botPlayingStrategy;

   public Bot(String name,Symbol symbol,BotDifficultyLevel botDifficultyLevel) {
       super(name,symbol);
       this.botDifficultyLevel=botDifficultyLevel;
       this.setType(PlayerType.BOT);
       this.botPlayingStrategy=
               BotPlayingStrategyFactory.getStrategyForLevel(botDifficultyLevel);
   }

   public Move makeMove(Board board){
       Move move= botPlayingStrategy.makeMove(board);
       move.setPlayer(this);
       return move;
   }

}
