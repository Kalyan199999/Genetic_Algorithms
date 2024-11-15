package com.knapsack;

import java.util.List;
import java.util.Random;

public class Mutation {
	
	public static void mutate(List<Individual> offsprings) {
		
		double pm = 0.3;
		Random random = new Random();
		
		for(Individual ind:offsprings)
		{
			if(random.nextDouble() <= pm)
			{
				int site = random.nextInt(ind.chromosome.length);
				
				if(ind.chromosome[site] == 0)
				{
					ind.chromosome[site] = 1;
				}
				else 
				{
					ind.chromosome[site] = 0;
				}
			}
		}
		
	}
}
