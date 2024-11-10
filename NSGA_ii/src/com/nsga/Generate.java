package com.nsga;

import java.util.List;
import java.util.Random;

public class Generate {
	
	public static void generatePopulation(List<Individual> population,int N ) {
		
		Random random = new Random(); 
		
		for(Individual ind:population)
		{
			for(int i=0;i<ind.X.length;i++)
			{
				ind.X[i] = random.nextDouble( ind.lower , ind.upper );
			}
			
		}
		
	}
}
