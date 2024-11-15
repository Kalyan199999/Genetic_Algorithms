package com.nsga;

import java.util.Collections;
import java.util.List;

public class Strategy {
	
	public static void surviedSolution(List<Individual> population , List<List<Individual>> fronts , int N ) {
		population.clear();
		
		for(List<Individual> list : fronts)
		{
			if( population.size()+list.size() <= N )
			{
				population.addAll(list);
			}
			else {
				Collections.sort(list , (a,b)-> (int)b.crowding_distance - (int)a.crowding_distance);
				
				while(!list.isEmpty())
				{
					if(population.size() == N) {
						break;
					}
					
					population.add(list.removeFirst());
				}
			}
		}
	}
}
