package com.moop;

import java.util.Random;

public class GeneticAlgorithm {

    private int populationSize;
    private int chromosomeLength;
    private int[][] population;
    private int[] fitnessScores;
    private double crossoverRate;
    private double mutationRate;
    private Random random = new Random();

    // Constructor
    public GeneticAlgorithm(int populationSize, int chromosomeLength, double crossoverRate, double mutationRate) {
        this.populationSize = populationSize;
        this.chromosomeLength = chromosomeLength;
        this.population = new int[populationSize][chromosomeLength];
        this.fitnessScores = new int[populationSize];
        this.crossoverRate = crossoverRate;
        this.mutationRate = mutationRate;
    }

    // Step 1: Generate Initial Population
    public void generateInitialPopulation() {
        for (int i = 0; i < populationSize; i++) {
            for (int j = 0; j < chromosomeLength; j++) {
                population[i][j] = random.nextInt(2); // Randomly assign 0 or 1
            }
        }
    }

    // Step 2: Evaluate Population (Fitness is number of 1's in chromosome)
    public void evaluatePopulation() {
        for (int i = 0; i < populationSize; i++) {
            fitnessScores[i] = calculateFitness(population[i]);
        }
    }

    // Fitness Function: Counts number of 1's
    private int calculateFitness(int[] chromosome) {
        int fitness = 0;
        for (int gene : chromosome) {
            fitness += gene;
        }
        return fitness;
    }

    // Step 3: Selection (Tournament Selection)
    public int[] selectParent() {
        int tournamentSize = 3; // Number of individuals to randomly select
        int bestIndex = random.nextInt(populationSize); // Initial random selection

        for (int i = 1; i < tournamentSize; i++) {
        	
            int randomIndex = random.nextInt(populationSize);
            
//            System.out.println("Random index:"+randomIndex);
            
            if (fitnessScores[randomIndex] > fitnessScores[bestIndex]) {
                bestIndex = randomIndex;
            }
        }
        return population[bestIndex];
    }

    // Step 4: Crossover (Single-Point Crossover)
    public int[] crossover(int[] parent1, int[] parent2) {
        int[] offspring = new int[chromosomeLength];
        if (random.nextDouble() < crossoverRate) {
            int crossoverPoint = random.nextInt(chromosomeLength);
            for (int i = 0; i < chromosomeLength; i++) {
                offspring[i] = (i < crossoverPoint) ? parent1[i] : parent2[i];
            }
        } else {
            // If no crossover, offspring is a copy of one parent
            offspring = parent1.clone();
        }
        return offspring;
    }

    // Step 5: Mutation
    public void mutate(int[] chromosome) {
        for (int i = 0; i < chromosomeLength; i++) {
            if (random.nextDouble() < mutationRate) {
                chromosome[i] = 1 - chromosome[i]; // Flip the gene (0 -> 1 or 1 -> 0)
            }
        }
    }

    // Step 6: Replace population with new generation
    public void newGeneration() {
        int[][] newPopulation = new int[populationSize][chromosomeLength];

        for (int i = 0; i < populationSize; i++) {
            // Step 3: Selection
            int[] parent1 = selectParent();
            int[] parent2 = selectParent();

            // Step 4: Crossover
            int[] offspring = crossover(parent1, parent2);

            // Step 5: Mutation
            mutate(offspring);

            // Add offspring to the new population
            newPopulation[i] = offspring;
        }

        // Replace old population with the new one
        population = newPopulation;
    }

    // Method to display population
    public void displayPopulation() {
        for (int i = 0; i < populationSize; i++) {
            for (int j = 0; j < chromosomeLength; j++) {
                System.out.print(population[i][j] + " ");
            }
            System.out.println(" | Fitness: " + fitnessScores[i]);
        }
    }

    // Method to check if the goal has been reached (All genes are 1)
    public boolean isGoalAchieved() {
        for (int score : fitnessScores) {
            if (score == chromosomeLength) {
                return true;
            }
        }
        return false;
    }

    // Main algorithm execution
    public void run(int maxGenerations) {
        // Step 1: Generate initial population
        generateInitialPopulation();

        for (int generation = 1; generation <= maxGenerations; generation++) {
            System.out.println("Generation " + generation);

            // Step 2: Evaluate population
            evaluatePopulation();

            // Display the population
            displayPopulation();

            // Check if the goal is achieved
            if (isGoalAchieved()) {
                System.out.println("Goal achieved in generation " + generation);
                break;
            }

            // Step 6: Generate new population
            newGeneration();
        }
    }

    // Main method to run the GA
    public static void main(String[] args) {
        // Parameters: population size, chromosome length, crossover rate, mutation rate
        int populationSize = 10;
        int chromosomeLength = 8;
        double crossoverRate = 0.7;
        double mutationRate = 0.01;
        int maxGenerations = 100;

        GeneticAlgorithm ga = new GeneticAlgorithm(populationSize, chromosomeLength, crossoverRate, mutationRate);

        // Run the algorithm for a maximum of 'maxGenerations' generations
        ga.run(maxGenerations);
    }
}

