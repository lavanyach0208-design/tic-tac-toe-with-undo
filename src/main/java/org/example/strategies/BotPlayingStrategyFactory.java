package org.example.strategies;


import org.example.models.BotDifficultyLevel;

public class BotPlayingStrategyFactory {
    public static BotPlayingStrategy getStrategyForLevel(BotDifficultyLevel botDifficultyLevel) {
        return switch (botDifficultyLevel) {
            case EASY -> new EasyPlayingStrategy();
            case MEDIUM -> new MediumPlayingStrategy();
            case HARD -> new HardPlayingStrategy();
            default -> throw new IllegalArgumentException("Unknown Bot Difficulty Level");
        };
    }
}
