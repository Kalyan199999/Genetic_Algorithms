package com.zdt1;

import java.util.List;
import java.util.PriorityQueue;

public class Survival {
	
	public static void survival(List<Individual> population , List<Individual> offsprings , List<Individual> survive) {
		
		PriorityQueue<Individual> queue = new PriorityQueue<Individual>((x,y) -> (int)y.fitness - (int) x.fitness);
		
		for(int i=0;i<offsprings.size();i++)
		{
			queue.offer(population.get(i));
			queue.offer(offsprings.get(i));
		}
		
		for(int i=0;i<offsprings.size();i++)
		{
			survive.add(queue.poll());
		}
		
		
	}
}
