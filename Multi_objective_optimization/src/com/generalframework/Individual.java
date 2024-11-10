package com.generalframework;

public class Individual {
	
	 double OBJECTIVE_VALUE;
	 double FITNESS;
	 int[] CHROMOSOME;
	
//	xbin store the decode value of each variable
	 int[] XBIN;
	
//	stores the length of each decision variable
	 int[] DECISION_VARIABLE_LENGTH;
	
//	store the lower and upper bounds of each variable;
	 int[] LOWER_BOUND;
	 int[] UPPER_BOUND;
	
	public Individual(int length,int n) {
		
		CHROMOSOME = new int[length];
		
		LOWER_BOUND = new int[n];
		
		UPPER_BOUND = new int[n];
		
		XBIN = new int[n];
		
		DECISION_VARIABLE_LENGTH = new int[n];
		
		OBJECTIVE_VALUE = 0.0;
		
		FITNESS = 0.0;
	}
}
