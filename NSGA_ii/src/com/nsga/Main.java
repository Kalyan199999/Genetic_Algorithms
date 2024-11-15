package com.nsga;

import java.util.ArrayList;
import java.util.List;

public class Main {
	
	public static void show(List<Individual> population , int t) {
		
		System.out.println(t);
		
		for(Individual ind:population)
		{
			
//			for(int i=0;i<ind.X.length;i++)
//			{
//				System.out.print(ind.X[i]+"  ");
//			}
			
			for(int i=0;i<ind.objectives.length;i++)
			{
				System.out.print(ind.objectives[i]+"     ");
			}

			System.out.println();
		}
		
		
		
		System.out.println("*******************************************************************************************************************************************************************************");
	}

	public static void main(String[] args) {
		
		int N = 100;
		
		int t = 1 , T = 100;
		
		List<Individual> population = new ArrayList<Individual>();

		for(int i=0;i<N;i++)
		{
			population.add(new Individual(2, 30,i));
		}
		
		Generate.generatePopulation(population, N);
		
		Evaluate.evaluate(population);
		
		AssignRank.findRank(population  , new ArrayList<List<Individual>>() );
		
		while(t <= T)
		{
			show(population,t);
			
			List<Individual> selected = new ArrayList<Individual>();
			
			Selection.selected(population, selected);

			List<Individual> offsprings = new ArrayList<Individual>();
			
			CrossOver.crossover(selected , offsprings);
			selected.clear();
			
			Mutation.mutate(offsprings);
			
			Evaluate.evaluate(offsprings);
			
			List<Individual> survive = new ArrayList<Individual>();

			for(int i=0;i<N;i++)
			{
				survive.add( population.get(i) );
				survive.add( offsprings.get(i) );
			} 
			
			offsprings.clear();
			
			List<List<Individual>> fronts = new ArrayList<>();
			
			AssignRank.findRank(survive , fronts);
			
			Strategy.surviedSolution(population, fronts, N);
			
			t++;
		}
		
	}

}
