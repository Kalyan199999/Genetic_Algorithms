package com.generalframework;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;



public class Main {
	
	private static void show(List<Individual> population) 
	{
		List<Double> fitness = new ArrayList<>();
		List<String> chromo = new ArrayList<>();
		
		for(Individual ind:population)
		{
			fitness.add(ind.FITNESS);
			String s = "";
			for(int x:ind.CHROMOSOME)
			{
				s += x;
			}
			
			chromo.add(s);
			
			
		}
//		System.out.println(chromo);
		System.out.println(fitness);
		
		System.out.println();
		
		System.out.println("*************************************");
		
	}
	
	public static void main(String[] args) {
		
		List<Individual> population = new ArrayList<>();
		
		Algorithm.inputs(population);
		Algorithm.generatePopulation(population);
		Algorithm.scale(population);
		EvaluateObjectives.evaluateCan(population,population.size());
		
		int t = 1 , T = 500;
		
		while( t < T )
		{
//			System.out.println(t);
			show(population);
			
//			selection
			List<Individual> selected = new ArrayList<Individual>();
			Algorithm.selectionOperator( population,selected);
			
			
//			cross over
			List<Individual> offsprings = new ArrayList<Individual>();
			Algorithm.crossover(selected,offsprings);
			
//			mutation
			Algorithm.mutation(offsprings);
			Algorithm.copy_details(population, offsprings);
			Algorithm.scale(offsprings);
			EvaluateObjectives.evaluateCan(offsprings,offsprings.size());

			
//			survival(mue+lamda) starategy
			
			PriorityQueue<Individual> queue = new PriorityQueue<Individual>(new ComparatorSortAsc());
			
			List<Individual> survived = new ArrayList<Individual>();
			
			for(int i=0;i<population.size();i++)
			{
				queue.offer(population.get(i));

				queue.offer(offsprings.get(i));
			}
			
			for(int i=0;i<population.size();i++)
			{
				survived.add(queue.poll());
			}
			
			population.clear();
			queue.clear();
			Algorithm.copy_survived(population,survived);
			t++;
		}
		
		show(population);

	}

}
