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
        public static final String DeterministicTournament = "deterministictournament";
        public static final String EliteSelection = "eliteselection";
        public static final String ProbabilisticTournament = "probabilistictournament";
        public static final String RouletteSelection = "rouletteselection";
        public static final String UniversalSelection = "universalselection";
        public static final String BoltzmanSelection = "boltzmanselection";
        public static final String RankingSelection = "rankingselection";
    }

    public static class Crossover {
        public static final String AnularCrossOver = "anularcrossover";
        public static final String OnePointCrossOver = "onepointcrossover";
        public static final String TwoPointsCrossOver = "twopointscrossover";
        public static final String UniformCrossOver = "uniformcrossover";
    }

    public static class Mutation {
        public static final String CompleteMultiGenMutation = "completemultigenmutation";
        public static final String GenMutation = "genmutation";
        public static final String LimitedMultiGenMutation = "limitedmultigenmutation";
        public static final String UniformMultiGenMutation = "uniformmultigenmutation";
    }

    public static class Type {
        public static final String Archer = "archer";
        public static final String Warrior = "warrior";
        public static final String Defender = "defender";
        public static final String Infiltrate = "infiltrate";
    }

    public static class Criteria{
        public static final String AcceptableSolutionCriteria = "acceptablesolution";
        public static final String ContentCriteria = "contentcriteria";
        public static final String NumberOfGenerationsCriteria  = "numberofgenerations";
        public static final String StructureCriteria = "structure";
        public static final String TimeCriteria = "time";
    }
}
