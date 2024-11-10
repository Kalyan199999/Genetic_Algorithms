package com.nsga;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

class Temporary{
	Set<Individual> set;
	int id;
	int n;
	public Temporary() {
		set = new HashSet<>();
		n = 0;
		id = 0;
	}
}

public class AssignRank {
	
	static int N ;
	
	private static boolean checkDominance(Individual soln_1 , Individual soln_2)
	{
		int n = soln_1.objectives.length;
		
//		checks whether the soln-1 dominates the soln-2
		
		boolean flag = false;
		
		for(int k=0;k<n;k++)
		{
			double obj_1 = soln_1.objectives[k];
			double obj_2 = soln_2.objectives[k];
			
//			System.out.println(obj_1+" "+obj_2);
			
			if(obj_1 > obj_2)
			{
				return false;
				
			}
			else {
				flag = true;
			}
		}
		
		return flag;
	}
	
	private static void stage_1( List<Individual> population , PriorityQueue<Individual> queue ,List<Temporary> ranks) {
		
		for(int i=0;i<N;i++)
		{
			Individual P = population.get(i);
			
			for(int j=0; j<N && i!=j;j++)
			{
				Individual Q = population.get(j);
				
				if( checkDominance(P, Q) )
				{

					System.out.println(i);
					
					ranks.get(i).set.add(Q);
				}
				else if( checkDominance( Q , P ) ) 
				{

					ranks.get(i).n += 1;
				}
			}
			
			if(ranks.get(i).n == 0)
			{
				population.get(i).rank = 1;
				
				queue.offer(population.get(i));
			}
		}
		
//		System.out.println(queue.size());
		
	}
	
	private static void stage_2(List<Individual> population,Queue<PriorityQueue<Individual>> fronts , List<Temporary> ranks )
	{
		int i = 1;
		
		while(!fronts.isEmpty())
		{
			PriorityQueue<Individual> queue = fronts.poll();
			
			PriorityQueue<Individual> store = new PriorityQueue<Individual>( (a,b)-> (int) b.crowding_distance - (int) a.crowding_distance );
			
			while(!queue.isEmpty())
			{
				Individual ind = queue.poll();
				
				int id = ind.id;
				
				for(Individual solution:ranks.get(id).set)
				{
					ranks.get(solution.id).n -= 1;
					
					if(ranks.get(solution.id).n == 0)
					{
//						System.out.println("kalyan");
						solution.rank = i+1;
						
						store.offer(solution);
					}
				}
				
			}
			
			
			i++;
			
			if(!store.isEmpty()) {
				fronts.offer(store);
			}
		}
		
	}
	
	public static void findRank(List<Individual> population) {
		
		Queue<PriorityQueue<Individual>> fronts = new LinkedList<>();
		
		List<Temporary> ranks = new ArrayList<Temporary>();
		
		N =population.size();
		
		for(int i=0;i<N;i++)
		{
			ranks.add(new Temporary());
			ranks.get(i).id = i;
		}
		
		PriorityQueue<Individual> queue_1 = new PriorityQueue<Individual>((a,b)-> (int) b.crowding_distance - (int) a.crowding_distance);
		
		stage_1(population, queue_1, ranks);

//		System.out.println(queue_1.size());
		
		fronts.add(queue_1);
//		
		stage_2(population, fronts, ranks);
		
//		for(Temporary temp:ranks)
//		{
//			System.out.println(temp.id +" "+temp.n+" "+population.get(temp.id).rank);
//		}
		
		System.out.println("*********************************************************");
		
		
	}

}
