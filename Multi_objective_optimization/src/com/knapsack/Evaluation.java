package com.knapsack;

import java.util.List;

public class Evaluation {
	
	public static void evaluate(List<Individual> population , int[] weights , int[] prices , int bag_weight) {
		
		for(Individual ind : population)
		{
			int n = ind.chromosome.length;
			int W = 0 ;
			double P = 0;
			
			for(int i=0;i<n;i++)
			{
				if(ind.chromosome[i] == 1)
				{
					W += weights[i];
					
					P += prices[i];
				}
			}
			
			if(W <= bag_weight)
			{
				ind.fitness = P;
			}
			else {
				ind.fitness = 0;
			}
		}
		
		
	}
}
