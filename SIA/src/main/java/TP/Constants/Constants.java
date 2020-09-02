package TP.Constants;

public class Constants {

    public static class Equipment {
        public static final String boots = "boots";
        public static final String helmet = "helmet";
        public static final String front = "front";
        public static final String gloves = "gloves";
        public static final String weapons = "weapons";
    }

    public static class Selection {
        public static final String DeterministicTournament = "DeterministicTournament";
        public static final String EliteSelection = "EliteSelection";
        public static final String ProbabilisticTournament = "ProbabilisticTournament";
        public static final String RouletteSelection = "RouletteSelection";
        public static final String UniversalSelection = "UniversalSelection";
    }

    public static class Crossover {
        public static final String AnularCrossOver = "AnularCrossOver";
        public static final String OnePointCrossOver = "OnePointCrossOver";
        public static final String TwoPointsCrossOver = "TwoPointsCrossOver";
        public static final String UniformCrossOver = "UniformCrossOver";
    }

    public static class Mutation {
        public static final String CompleteMultiGenMutation = "CompleteMultiGenMutation";
        public static final String GenMutation = "GenMutation";
        public static final String LimitedMultiGenMutation = "LimitedMultiGenMutation";
        public static final String UniformMultiGenMutation = "UniformMultiGenMutation";
    }

    public static class Type {
        public static final String Archer = "Archer";
        public static final String Warrior = "Warrior";
        public static final String Defender = "Defender";
        public static final String Infiltrate = "Infiltrate";
    }
}
