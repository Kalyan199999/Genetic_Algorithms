package com.zdt1;

import java.util.List;

public class Scaling {
	
	public static void scale(List<Individual> population , int N) {
		
		for(int i=0;i<N;i++)
		{
			int n = population.get(i).X.length;
			
			for(int j=0;j<n;j++)
			{
				int decode_value = 0;
				
				int mul = 1;
				
				int n_c = population.get(i).chromosome.length;
				
				for(int k=n_c-1;k>=0;k--)
				{
					decode_value += population.get(i).chromosome[k]*mul;
					mul *= 2;
				}
				
				double lower = population.get(i).lower , upper = population.get(i).upper;
				
				population.get(i).X[j] = lower+(upper-lower)*decode_value;
			}
		}
		
	}
}
