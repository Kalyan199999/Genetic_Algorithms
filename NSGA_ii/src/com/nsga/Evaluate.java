package com.nsga;

import java.util.List;

public class Evaluate {
	
	public static double sum(double[] X) {
		
		double res = 0.0;
		
		for(int i=1;i<X.length;i++)
		{
			res += X[i];
		}
		
		return res;
	}
	
	public static void evaluate(List<Individual> population ) {
		
		for(Individual ind : population)
		{
			ind.rank = 0;
			
			for(int i=0;i<ind.objectives.length;i++)
			{
				switch (i) {
				
				case 0: {
					
					ind.objectives[i] = ind.X[0];
					
					break;
				}
               case 1: {
					
            	   double mul = sum(ind.X);
            	   
            	   double g_x = 1+ (9/29)*mul;
            	   
            	   Double h_x = 1 - (Math.sqrt( ind.objectives[0] / g_x ));
            	   
					ind.objectives[i] = g_x*h_x;
					
					break;
				}
               
				default:{
					throw new IllegalArgumentException("Unexpected value: " + i);
				}
				}
			}
			
		}
		
		
	}
}
