package com.nsga;

//import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CrowdingDistance {
	
	private static void sortList(List<Individual> F , int i) {
		
		Collections.sort(F,(a,b)-> (int) a.objectives[i] - (int)b.objectives[i]);
	}
	
	private static void calculateDistance(List<Individual> F )
	{	
		int r = F.size();
		
		if(r == 0) return;
		
		if(r == 1) {
			F.get(0).crowding_distance = Double.MAX_VALUE;
			return;
		}
		
		if(r == 2) {

			F.getFirst().crowding_distance = Double.MAX_VALUE;

			F.getLast().crowding_distance = Double.MAX_VALUE;
			
			return;
		}
		
		for(int x=0;x<r;x++)
		{
			Individual ind = F.get(x);
			
			int n = ind.objectives.length;
			
			ind.crowding_distance = 0;
			
//			List<Individual> list = new ArrayList<Individual>(F);
			
			for(int i=0;i<n;i++)
			{
				sortList(F,i);
				
				F.getFirst().crowding_distance = Double.MAX_VALUE;
				
				F.getLast().crowding_distance = Double.MAX_VALUE;
				
				for(int j=1;j<r-1;j++)
				{
					if( F.get(j).crowding_distance != Double.MAX_VALUE )
					{
						double numerator = Math.abs( F.get(j+1).objectives[i] - F.get(j-1).objectives[i] );
						
						double deniminator = Math.abs( F.getLast().objectives[i] - F.getFirst().objectives[i] ) ;
						
						F.get(j).crowding_distance += ( numerator ) / ( deniminator ) ;
					}
				}
			}
			
//		F.clear();
//		
//		F.addAll(list);
		
		}
	}
	
	
	public static void crowding_distance(List<List<Individual>> fronts ) {
		
		for(List<Individual> F : fronts)
		{
			calculateDistance(F);
		}
		
		
	}

}
