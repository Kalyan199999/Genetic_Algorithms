package com.nsga;

import java.util.List;
import java.util.Random;

public class Generate {
	
	public static void generatePopulation(List<Individual> population , int N ) {
		
		Random random = new Random(); 
		
		for(Individual ind:population)
		{
			int n = ind.X.length;
			
			for(int i=0;i<n;i++)
			{
				ind.X[i] = random.nextDouble( ind.lower , ind.upper );
			}
			
		}
		
	}
}
