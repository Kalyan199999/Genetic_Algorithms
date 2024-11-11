package com.nsga;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Selection {
	
	static Random random = new Random();
	
	private static void binaryTournment(List<Individual> population  , List<Individual> offsprings) 
	{
		
		while(!population.isEmpty())
		{
			Individual ind_1 = population.remove( random.nextInt( 0 , population.size() ) );
			
			Individual ind_2 = population.remove( random.nextInt( 0 , population.size() ) );
			
			if(ind_1.rank != ind_2.rank)
			{
				if(ind_1.rank < ind_2.rank)
				{
					offsprings.add(ind_1);
				}
				else {
					offsprings.add(ind_2);
				}
			}
			else if(ind_1.crowding_distance != ind_2.crowding_distance )
			{
				if( ind_1.crowding_distance > ind_2.crowding_distance)
				{
					offsprings.add(ind_1);
				}
				else 
				{
					offsprings.add(ind_2);
				}
			}
			else {
				
				if (random.nextDouble() <= 0.5 )
				{

					offsprings.add(ind_1);
				}
				else {

					offsprings.add(ind_2);
				}
			}
		}
	}
	
	public static void selected( List<Individual> population  , List<Individual> offsprings ) {
		
		List<Individual> t_1 = new ArrayList<Individual>(population);

		List<Individual> t_2 = new ArrayList<Individual>(population);
		
		binaryTournment(t_1, offsprings);

		binaryTournment(t_2, offsprings);
	}
}
