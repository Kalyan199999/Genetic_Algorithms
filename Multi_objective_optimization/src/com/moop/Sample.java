package com.moop;

public class Sample {

	public static void main(String[] args) {
		
		MultiObjective mo = new MultiObjective(10, 8);
		
		mo.initialPopulation();
		System.out.println("Initial Population");
		mo.display();
		
		System.out.println("Initial Population in decimal");
		mo.displayDecimal();
		
		System.out.println();
		mo.evaluatePopulation();
		mo.displayPopulation();
	}

}
