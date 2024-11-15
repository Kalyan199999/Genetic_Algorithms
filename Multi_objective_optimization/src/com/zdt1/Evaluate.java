package com.zdt1;


import java.util.List;

public class Evaluate {
	
	public static double sum(double[] X) {
		
		double res = 0.0;
		
		for(int i=1;i<X.length;i++)
		{
			res += X[i];
		}
		
//		System.out.println(res);
		
		return res;
	}
	
	public static void evaluate(List<Individual> population ) {
		
		for(Individual ind : population)
		{
			double fit = 0;
			
			for(int i=0;i<ind.objectives.length;i++)
			{
				switch (i) {
				
				case 0: {
					
					ind.objectives[i] = ind.X[0];
					fit += ind.objectives[i];
					break;
				}
               case 1: {
					
            	   double mul = sum(ind.X);
            	   
					ind.objectives[i] = 1+ (9/29)*mul;
					
					fit += ind.objectives[i];
					
					break;
				}
               case 2: {
					
					ind.objectives[i] = 1 - (Math.sqrt( ind.objectives[0] /ind.objectives[1] ));
					
					fit += ind.objectives[i];
					
					break;
				}
				default:
					throw new IllegalArgumentException("Unexpected value: " + i);
				}
			}
			
			ind.fitness = fit /3;
		}
		
		
	}
}
