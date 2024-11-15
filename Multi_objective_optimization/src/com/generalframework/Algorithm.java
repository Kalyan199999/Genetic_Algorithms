package com.generalframework;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Algorithm {
	
	static Scanner sc = new Scanner(System.in);
	
	static Random random = new Random();

	static int N = 6 , n = 2 , chromoLength = 10;
	
//	Probability of cross-over and mutation
	static double pc , pm;
	
	static void inputs(List<Individual> population)
	{
//		solutions = new Individual[N];
		
		int[] decision_variable_length = new int[n];
		
		int[] lb = new int[n];
		int[] ub = new int[n];
		
		for(int i=0;i<N;i++)
		{
			population.add(new Individual(chromoLength, n));
			
			for(int j=0;j<n;j++)
			{
				if(i == 0)
				{
					
					System.out.println("Length of the decision variable:"+(j+1));
					
					decision_variable_length[j] = sc.nextInt();
					
					System.out.println("Lower bound of decision variable:"+(j+1));
					lb[j] = sc.nextInt();
					
					System.out.println("Upper bound of decision variable:"+(j+1));
					ub[j] = sc.nextInt();
				
				}
			    
				population.get(i).DECISION_VARIABLE_LENGTH[j] = decision_variable_length[j];
				population.get(i).LOWER_BOUND[j] = lb[j];
				population.get(i).UPPER_BOUND[j] = ub[j];
			}
		}
		
		pc = 0.9;
		
		pm = 0.3;
	}
	
//	Generate the random population
	public static void generatePopulation(List<Individual> population) {
		
		for(int i=0;i<N;i++)
		{
			int idx = 0;
			
			for(int j=0;j<n;j++)
			{
				for(int k=0; k< population.get(i).DECISION_VARIABLE_LENGTH[j]; k++)
				{
					 population.get(i).CHROMOSOME[idx] = random.nextInt(2);
					idx++;
				}
			}
		}
	}

//		Scaling the decision variables
	public static void scale(List<Individual> population)
	{
		
		for(int i=0;i<N;i++)
		{
			int idx =0 ;
			
			for(int j=0;j<n;j++)
			{
				int val = 0;
				int mul = 1;
				
				int temp =  population.get(i).DECISION_VARIABLE_LENGTH[j];
				
				for(int k=1;k<=temp;k++)
				{
					val += mul*population.get(i).CHROMOSOME[idx+temp-k];
					
					mul <<= 1;
				}
				
				idx += temp;
				
				int lowerBound = population.get(i).LOWER_BOUND[j];
				int upperBound = population.get(i).UPPER_BOUND[j];
				int deniminator = 1 <<population.get(i).DECISION_VARIABLE_LENGTH[j];
				
//				System.out.println(val);
				
				population.get(i).XBIN[j] = lowerBound + ( (upperBound - lowerBound) / (deniminator - 1) ) * val ;
				
//				System.out.print(population.get(i).XBIN[j]+" ");
				
			}
		}
	}
	
	

	
//	Selection operator
	public static void selectionOperator(List<Individual> population ,List<Individual> selected)
	{
		List<Individual> tournement_1 = new ArrayList<>();

		List<Individual> tournement_2 = new ArrayList<>();
		
		for(int i=0;i<N;i++)
		{
			tournement_1.add(population.get(i));
			tournement_2.add(population.get(i));
		}
		
//		System.out.println("Selected solution:");
		
		while(!tournement_1.isEmpty())
		{
			Individual a = tournement_1.remove( random.nextInt(tournement_1.size()) );
			Individual b = tournement_1.remove( random.nextInt(tournement_1.size()) );
			
			if(a.FITNESS <= b.FITNESS)
			{
//				System.out.println(a.FITNESS);
				selected.add(a);
			}
			else 
			{
//				System.out.println(b.FITNESS);
				selected.add(b);
			}
			

		}
		
		while(!tournement_2.isEmpty())
		{
			Individual a = tournement_2.remove( random.nextInt(tournement_2.size()) );
			Individual b = tournement_2.remove( random.nextInt(tournement_2.size()) );
			
			if(a.FITNESS <= b.FITNESS)
			{
//				System.out.println(a.FITNESS);
				selected.add(a);
			}
			else 
			{
//				System.out.println(b.FITNESS);
				selected.add(b);
			}
			
		}
		
	}
	
//	cross-over
	public static void crossover(List<Individual> selected,List<Individual> offsprings) {
		
		List<Individual> parents = new ArrayList<>();

		for(int i=0;i<N;i++)
		{
			parents.add(selected.get(i));
			
		}
		
		while(!parents.isEmpty())
		{
			int p_1_idx = random.nextInt(parents.size());
			
			Individual p1 = parents.remove( p_1_idx  );
//			System.out.println(p1.FITNESS);
			
			int p_2_idx = random.nextInt(parents.size());
			
			Individual p2 = parents.remove( p_2_idx );
//			System.out.println(p2.FITNESS);
			
			Individual c1 = new Individual(chromoLength, n);
			Individual c2 = new Individual(chromoLength, n);
			
			if(random.nextDouble(1) <= pc)
			{
//				int idx = 0;
				
//				for(int j=0;j<n;j++)
//				{
//					
//					int site = random.nextInt(p1.DECISION_VARIABLE_LENGTH[j]);
//					
//					for(int k=0;k<site;k++)
//					{
//						c1.CHROMOSOME[idx+k] = p1.CHROMOSOME[idx+k];
//						c2.CHROMOSOME[idx+k] = p2.CHROMOSOME[idx+k];
//					}
//					
//					for(int k=site;k<p1.DECISION_VARIABLE_LENGTH[j];k++)
//					{
//						c1.CHROMOSOME[idx+k] = p2.CHROMOSOME[idx+k];
//						c2.CHROMOSOME[idx+k] = p1.CHROMOSOME[idx+k];
//					}
//					
//					idx += p1.DECISION_VARIABLE_LENGTH[j];
//					
//				}
				
				int site = random.nextInt(0,chromoLength);
				
				for(int i=0;i<site;i++)
				{
					c1.CHROMOSOME[i] = p1.CHROMOSOME[i];

					c2.CHROMOSOME[i] = p2.CHROMOSOME[i];
				}
				
				for(int i=site;i<chromoLength;i++)
				{
					c1.CHROMOSOME[i] = p2.CHROMOSOME[i];

					c2.CHROMOSOME[i] = p1.CHROMOSOME[i];
				}
				
				
				offsprings.add(c1);
				offsprings.add(c2);
				
			}
			else {
				offsprings.add(p1);
				offsprings.add(p2);
			}
		}
	}
	
	public static void copy_details(List<Individual> population , List<Individual> offsprings)
	{
		for(int i=0;i<N;i++)
		{
			for(int j=0;j<n;j++)
			{
				offsprings.get(i).XBIN[j] = population.get(i).XBIN[j];
				
				offsprings.get(i).DECISION_VARIABLE_LENGTH[j] = population.get(i).DECISION_VARIABLE_LENGTH[j];
				
				offsprings.get(i).LOWER_BOUND[j] = population.get(i).LOWER_BOUND[j];
				
				offsprings.get(i).UPPER_BOUND[j] = population.get(i).UPPER_BOUND[j];
			}
		}
	}
	
//	Mutation
	public static void mutation(List<Individual> offsprings) {
		
		for(int i=0;i<offsprings.size();i++)
		{
			if(random.nextDouble() <= pm)
			{
				int site = random.nextInt(0, offsprings.get(i).CHROMOSOME.length );
				
				if(offsprings.get(i).CHROMOSOME[site] == 0)
				{
					offsprings.get(i).CHROMOSOME[site] = 1;
				}
				else {
					offsprings.get(i).CHROMOSOME[site] = 0;
				}
				
			}
		}
		
	}
	
	public static void copy_survived(List<Individual> population , List<Individual> survived) 
	{
		for(int i=0;i<survived.size();i++)
		{
			population.add(survived.get(i));
		}
	}

}
