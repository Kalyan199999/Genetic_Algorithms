package com.nsga;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

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
		boolean flag = true;
		
		for(int k=0;k<n;k++)
		{
			double obj_1 = soln_1.objectives[k];
			
			double obj_2 = soln_2.objectives[k];
			
			
			if(obj_1 > obj_2)
			{
				flag =  false;
				break;
			}
			
		}
		
		return flag;
	}
	
	private static void stage_1( List<Individual> population , List<Individual> queue , List<Temporary> ranks) 
	{
		for(int i=0;i<N;i++)
		{
			Individual P = population.get(i);
			
			for(int j=0; j<N && i!=j ;j++)
			{
				Individual Q = population.get(j);
				
				if( checkDominance(P, Q) )
				{
					ranks.get(i).set.add(Q);
					
//					System.out.println(i);
				}
				else if( checkDominance( Q , P ) ) 
				{
					ranks.get(i).n += 1;
//					System.out.println(j);
				}
				else {
//					System.out.println("kalyan");
				}
			}
			
			if(ranks.get(i).n == 0)
			{
				population.get(i).rank = 1;
				
				queue.add(population.get(i));
			}
		}
		
		
	}
	
	private static void stage_2(List<Individual> population , List<List<Individual>> fronts , List<Temporary> ranks , List<List<Individual>> fronts_2 )
	{
		int i = 1;
		
		while(!fronts.isEmpty())
		{
			List<Individual> queue = fronts.removeFirst();
			
			fronts_2.add(new ArrayList<Individual>(queue));
			
			List<Individual> store = new ArrayList<Individual>();
			
			while(!queue.isEmpty())
			{

				Individual ind = queue.removeFirst();
				
				int id = ind.id;
				
				for(Individual solution:ranks.get(id).set)
				{
					ranks.get(solution.id).n -= 1;
					
					if(ranks.get(solution.id).n == 0)
					{
						solution.rank = i+1;
						
						store.add(solution);
					}
				}
				
			}
			
			
			i++;
			
			if(!store.isEmpty()) {
				fronts.add(store);
			}
		}
		
	}
	
//	private static void show(List<List<Individual>> fronts) {
//		
//		for(List<Individual> list:fronts)
//		{
//			for(Individual ind:list)
//			{
//				System.out.println(ind.crowding_distance);
//			}
//		}
//		
//		System.out.println("**********************************************************************");
//	}
	
	public static void findRank(List<Individual> population , List<List<Individual>> fronts_2  ) {
		
		List<List<Individual>> fronts = new ArrayList<>();
		
		List<Temporary> ranks = new ArrayList<Temporary>();
		
		N = population.size();
		
		for(int i=0;i<N;i++)
		{
			ranks.add(new Temporary());
			ranks.get(i).id = i;
		}
		
		List<Individual> queue_1 =  new ArrayList<>();
		
		stage_1(population, queue_1, ranks);
		
		fronts.add(queue_1);
		
		stage_2(population, fronts, ranks , fronts_2);

		CrowdingDistance.crowding_distance(fronts_2);
		
		
		
	}

}
