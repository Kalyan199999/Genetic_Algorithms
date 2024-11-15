package com.nsga;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class CrossOver {
	
	static Random random = new Random();
	
	static int n_c = 15;
	
	private static double calulate_beta() {
		
		double res = 0.0;
		
		double u_i = random.nextDouble(0,1);
		
		double denominator = 1 / (n_c+1);
		
		if(u_i <= 0.5)
		{
			res = Math.pow( (2*u_i) , denominator);
		}
		else 
		{
			res = Math.pow( ( 1/ ( 2*(1-u_i)) ) , denominator);
		}
		
		return res;
	}
	
//	private static double probability_distribution(double beta) 
//	{
//		
//		double sum = 0.5 * (n_c+1);
//		double res = 0.0;
//		
//		if(beta <= 1)
//		{
//			res = sum * Math.pow(beta, n_c);
//		}
//		else {
//			res = sum * Math.pow(beta, n_c+2);
//		}
//		
//		return res;
//		
//	}
	
	public static void cross(List<Individual> tournment , List<Individual> offsprings) {
		
		while(!tournment.isEmpty())
		{
			
			Individual parent_1 = tournment.remove(  random.nextInt(0 , tournment.size()) );
			Individual parent_2 = tournment.remove(  random.nextInt(0 , tournment.size()) );
			
			Individual offspring_1 = new Individual(parent_1.objectives.length, parent_1.X.length,parent_1.id);
			
			Individual offspring_2 = new Individual(parent_1.objectives.length, parent_1.X.length , parent_2.id);
			
			int n = parent_1.X.length;
			
			double pc = 0.9;
			
			double rand = random.nextDouble(0,1);
			
			if(rand <= pc)
			{
				for(int i=0;i<n;i++)
				{
					double beta = calulate_beta();
					
					double a = parent_1.X[i];
					double b = parent_2.X[i];
					
					if( a <= b )
					{
						offspring_1.X[i] = 0.5* ( (a+b) - beta * (b-a) );
						
						offspring_2.X[i] = 0.5* ( (a+b) + beta * (b-a) );
					}
					else {
						
	                    offspring_1.X[i] = 0.5* ( (a+b) - beta * (a-b) );
						
						offspring_2.X[i] = 0.5* ( (a+b) + beta * (a-b) );
					}
					
				}
				
				offsprings.add(offspring_1);
				offsprings.add(offspring_2);
			}
			else 
			{
				offsprings.add(parent_1);
				offsprings.add(parent_2);
				
			}
			
		}
		
	}
	
	public static void crossover(List<Individual> population , List<Individual> offsprings) 
	{
		
		List<Individual> tournment = new ArrayList<Individual>();
	
		for(Individual ind:population)
		{
			tournment.add(ind);
		}
		
		cross(tournment, offsprings);
		
		
		
	}

}
