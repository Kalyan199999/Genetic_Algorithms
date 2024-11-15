package com.generalframework;

import java.util.List;

public class EvaluateObjectives {
	
	EvaluateObjectives(){
//		constructor
	}
	
	
//	Evaluate the population
	public static void evaluateCan(List<Individual> population,int N) {
		double pi = Math.PI , cost = 0.065;
		
		for(int i=0;i<N;i++)
		{
			int d = population.get(i).XBIN[0];
			int h = population.get(i).XBIN[1];
			
			if( ( pi * (d*d*h) ) /4  >= 300.00)
			{
				population.get(i).OBJECTIVE_VALUE = cost*( (pi*d*d)/2 + (pi*d*h) ) ;
				
				population.get(i).FITNESS = population.get(i).OBJECTIVE_VALUE;
			}
			else 
			{
				population.get(i).OBJECTIVE_VALUE = Double.MAX_VALUE;
				population.get(i).FITNESS = population.get(i).OBJECTIVE_VALUE;
			}
		}
	}
	
	public static void evaluateSinX(List<Individual> population,int N) {
		
		for(int i=0;i<N;i++)
		{
			double x = population.get(i).XBIN[0];
			population.get(i).OBJECTIVE_VALUE = Math.sin(x);
			population.get(i).FITNESS = population.get(i).OBJECTIVE_VALUE;
		}
	}
	
	public static void evaluateXSquare(List<Individual> population,int N) {
		
//		F(x) = x^2
		
		for(int i=0;i<N;i++)
		{
			double x = population.get(i).XBIN[0];
			
			if(x>=population.get(i).LOWER_BOUND[0] && x<=population.get(i).UPPER_BOUND[0] )
			{
				population.get(i).OBJECTIVE_VALUE = x*x;
				population.get(i).FITNESS = population.get(i).OBJECTIVE_VALUE;
			}
			else {
				population.get(i).OBJECTIVE_VALUE = Double.MIN_VALUE;
				population.get(i).FITNESS = population.get(i).OBJECTIVE_VALUE;
			}
		}
		
	}

}
