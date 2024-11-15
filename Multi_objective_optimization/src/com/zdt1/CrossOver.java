package com.zdt1;


import java.util.List;
import java.util.Random;

public class CrossOver {
	
	public static void crossOver(List<Individual> population ,List<Individual> offsprings) {
		Random random = new Random();
		
		double pc = 0.8;
		
		while(!population.isEmpty())
		{
			Individual parent_1 = population.remove( random.nextInt(0,population.size()) );
			Individual parent_2 = population.remove( random.nextInt(0,population.size()) );
			
			int n = parent_1.chromosome.length;
			
			Individual c1 = new Individual(3,30,1);
			Individual c2 = new Individual(3,30,1);
			
			if(random.nextDouble(1) <= pc)
			{
				int site = random.nextInt(0,n);
				
				for(int i=0;i<site;i++)
				{
					c1.chromosome[i] = parent_1.chromosome[i];
					c2.chromosome[i] = parent_2.chromosome[i];
				}
				
				for(int i=site;i<n;i++)
				{
					c1.chromosome[i] = parent_2.chromosome[i];
					c2.chromosome[i] = parent_1.chromosome[i];
				}
				
				offsprings.add(c1);
				offsprings.add(c2);
			}
			else {

				offsprings.add(parent_1);
				offsprings.add(parent_2);
			}
			
		}
		
	}
}
