package com.nsga;

public class Individual {
	
	int id;
	
	double[] objectives;
	
	double crowding_distance;
	
	int rank;
	
	double[] X;
	
	double lower = 0 , upper = 1;
	
	public Individual(int n_objective , int n_var , int id) {
		
		objectives = new double[n_objective];
		
		X = new double[n_var];
		
		crowding_distance = 0.0;
		
		rank =0;
		
		this.id = id;
				
	}
	

}