package com.moop;

import java.util.Random;

public class MultiObjective {
	
	private int POPULATION_SIZE;
	private int CHROMOSOME_LENGTH;
	private double[][] population;
	private double[] fitnessScores;
	
//	Initilizaing variable using constructor
	public MultiObjective(int ps,int cl) {
		this.POPULATION_SIZE = ps;
		this.CHROMOSOME_LENGTH = cl;
		fitnessScores = new double[POPULATION_SIZE];
		population = new double[POPULATION_SIZE][CHROMOSOME_LENGTH];
		
	}
	
	
//	Generation the initial population
	
	public void initialPopulation() {
		
		Random random = new Random();
		
		
		for(int i=0;i<POPULATION_SIZE;i++)
		{
			for(int j=0;j<CHROMOSOME_LENGTH;j++)
			{
				population[i][j] = random.nextInt(2);
			}
		}
		
	}
	
	// Fitness function: Counts the number of 1s in a chromosome
    public int calculateFitness(double[] chromosome) {
        int fitness = 0;
        for (double gene : chromosome) {
            fitness += gene; // Add 1 if the gene is 1 (ignore if 0)
        }
        return fitness;
    }
    
    // Method to evaluate the entire population
    public void evaluatePopulation() {
        for (int i = 0; i < POPULATION_SIZE; i++) {
            fitnessScores[i] = calculateFitness(population[i]);
        }
    }
	
//	Dislay
	public void display() {
		
		for(int i=0;i<POPULATION_SIZE;i++)
		{
			for(int j=0;j<CHROMOSOME_LENGTH;j++)
			{
				System.out.print(population[i][j]+" ");
			}
			System.out.println();
		}
		
	}
	
//	convert binary into decimal
	public void displayDecimal()
	{
		for(int i=0;i<POPULATION_SIZE;i++)
		{
			int pow = 1;
			double value = 0;
			
			for(int j=CHROMOSOME_LENGTH-1;j>=0;j--)
			{
				value += pow*population[i][j];
				pow *= 2;
			}
			
			System.out.println(value);
		}
	}
	
	// Method to display the population and fitness scores
    public void displayPopulation() {
        for (int i = 0; i < POPULATION_SIZE; i++) {
            for (int j = 0; j < CHROMOSOME_LENGTH; j++) {
                System.out.print(population[i][j] + " ");
            }
            System.out.println(" | Fitness: " + fitnessScores[i]);
        }
    }
	

}
