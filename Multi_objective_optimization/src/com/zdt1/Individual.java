package com.zdt1;

public class Individual {
	
	double[] objectives;
	
	double fitness;
	
	int[] chromosome;
	
	double[] X;
	
	double lower = 0 , upper = 1;
	
	public Individual(int n_objective , int n_var,int chromosome_length) {
		
		objectives = new double[n_objective];
		chromosome = new int[chromosome_length];
		X = new double[n_var];
				
	}
	

}
