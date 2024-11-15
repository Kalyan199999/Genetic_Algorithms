package com.zdt1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Selection {
	
	public static void selectSolution(List<Individual> population ,List<Individual> offsprings) {
		
		Random random = new Random();
		
		List<Individual> tournement_1 = new ArrayList<Individual>();
		List<Individual> tournement_2 = new ArrayList<Individual>();
		
		for(Individual ind:population)
		{
			tournement_1.add(ind);
			tournement_2.add(ind);
		}
		
		while(!tournement_1.isEmpty())
		{
			Individual parent_1 = tournement_1.remove( random.nextInt(0,tournement_1.size()) );
			Individual parent_2 = tournement_1.remove( random.nextInt(0,tournement_1.size()) );
			
			
			if(parent_1.fitness <= parent_2.fitness)
			{
				offsprings.add(parent_1);
			}
			else {
				offsprings.add(parent_2);
			}
		}
		
		while(!tournement_2.isEmpty())
		{
			Individual parent_1 = tournement_2.remove( random.nextInt(tournement_2.size()) );
			Individual parent_2 = tournement_2.remove( random.nextInt(tournement_2.size()) );
			
			
			if(parent_1.fitness <= parent_2.fitness)
			{
				offsprings.add(parent_1);
			}
			else {
				offsprings.add(parent_2);
			}
		}
		
		
	}
}
