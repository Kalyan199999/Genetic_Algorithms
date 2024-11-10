package com.zdt1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Generate {
	
	public static void generatePopulation(List<Individual> population,int N ) {
		
		Random random = new Random(); 
		
		for(Individual ind:population)
		{
			
			for(int i=0;i<ind.chromosome.length;i++)
			{
				ind.chromosome[i] = random.nextInt(2);
			}
			
		}
		
	}
}
