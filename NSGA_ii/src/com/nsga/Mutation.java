package com.nsga;

import java.util.List;
import java.util.Random;

public class Mutation {
	
	static double pm ;
	static double n_m = 15;
	
	static Random random  = new Random();
	
	private static double calculate_delta() {
		double res = 0.0;
		double r = random.nextDouble(0,1);
		
		double sum = 1 / ( n_m +1 );
		
		if(r < 0.5)
		{
			res = Math.pow((2*r), sum) - 1;
		}
		else {
			res = 1 - Math.pow( ( 2*(1-r) ), sum) - 1;
		}
		
		
		return res;
	}
	
	public static void mutate( List<Individual> offsprings) {
		
		int N = offsprings.size();
		
		pm = 1 / N;
		
		for(Individual ind:offsprings)
		{
			double temp = random.nextDouble(0,1);
			
			if(temp <= pm )
			{
				int n = ind.X.length;
				
				for(int i=0;i<n;i++)
				{
					Double mul = calculate_delta();
					
					ind.X[i] += (ind.upper - ind.lower) * mul ;
				}
			}
		}
		
		
	}

}
