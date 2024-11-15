package com.knapsack;

import java.util.ArrayList;
import java.util.List;

public class Main {
	
	public static void show(List<Individual> currPopulation) {
		
		for(Individual ind:currPopulation)
		{
			for(int x:ind.chromosome)
			{
				System.out.print(x+" ");
			}
			System.out.println(ind.fitness);
		}
		
		System.out.println("*******************************");
	}

	public static void main(String[] args) {
		
		int[] weight = {10,5,25,15,13,33,8,11};
		int[] prices = {10,8,7,12,7,23,4,10};
		 
		int n_items = weight.length;
		
		int bag_weight = 50;
		
		int N = 10;
		
		List<Individual> currPopulation = new ArrayList<Individual>();
		
		for(int i=0;i<N;i++)
		{
			currPopulation.add(new Individual(n_items));
		}
		
		Population.generatePopulation(currPopulation, N);
		Evaluation.evaluate(currPopulation, weight, prices, bag_weight);
		
		
		
		int t = 1 , T = 500;
		
		while(t < T)
		{
			System.out.println(t);
			
			show(currPopulation);
			
			List<Individual> selected = new ArrayList<Individual>();
			Selection.selectSolution(currPopulation, selected);
			
			List<Individual> offsprings = new ArrayList<Individual>();
			CrossOver.crossOver(selected, offsprings);
			
			Mutation.mutate(offsprings);
			Evaluation.evaluate(offsprings, weight, prices, bag_weight);
			
			
			List<Individual> survive = new ArrayList<Individual>();
			
			Survival.survival(currPopulation, offsprings, survive);
			
			currPopulation.clear();
			currPopulation.addAll(survive);

			t++;
		}

	}

}
