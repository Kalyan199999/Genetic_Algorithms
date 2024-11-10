package com.knapsack;

public class Individual {
	
	 int[] chromosome;
	
	 double fitness;
	
	Individual(int n){
		chromosome = new int[n];
		fitness = 0.0;
	}
}
