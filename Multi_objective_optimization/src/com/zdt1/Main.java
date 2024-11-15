package com.zdt1;

import java.util.ArrayList;
import java.util.List;


public class Main {
	
	public static void show(List<Individual> population) {
		
		for(Individual ind:population)
		{
//			for(int x:ind.chromosome)
//			{
//				System.out.print(x+" ");
//			}
			
			for(double x:ind.objectives)
			{
				System.out.print(x+" ");
			}
			
//			for(double x:ind.X)
//			{
//				System.out.print(x+" ");
//			}
			
			
			System.out.println(ind.fitness);
			System.out.println();
		}
		
		System.out.println("**********************************************************************************************************************************************");
		
	}

	public static void main(String[] args) {
		
		List<Individual> population = new ArrayList<Individual>();
		int N = 6;
		
		for(int i=0;i<N;i++)
		{
			population.add(new Individual(3, 30, 1));
		}
		
		Generate.generatePopulation(population, N);
		Scaling.scale(population, N);
		Evaluate.evaluate(population);
		
		
		int t=1 , T=10;
		
		while(t < T)
		{
			show(population);
			
			List<Individual> selected = new ArrayList<Individual>();
			Selection.selectSolution(population, selected);
			
			List<Individual> offsprings = new ArrayList<Individual>();
			CrossOver.crossOver(selected, offsprings);
			
			Mutation.mutate(offsprings);
			Evaluate.evaluate(offsprings);
			
            List<Individual> survive = new ArrayList<Individual>();
			
			Survival.survival(population, offsprings, survive);
			
			population.clear();
			population.addAll(survive);
			
			t++;
		}
		
		show(population);

	}

}
