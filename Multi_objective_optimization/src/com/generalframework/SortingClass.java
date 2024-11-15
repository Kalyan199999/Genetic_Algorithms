package com.generalframework;

import java.util.Comparator;

public class SortingClass {

}


class ComparatorSortAsc implements Comparator<Individual>{
	
	ComparatorSortAsc(){
		
	}

	@Override
	public int compare(Individual o1, Individual o2) {
		
		if(o1.FITNESS <= o2.FITNESS)
		{
			return -1;
		}
		else if(o1.FITNESS > o2.FITNESS)
		{
			return 1;
		}
		
		return 0;
	}

}

class ComparatorSortDesc implements Comparator<Individual>{
	
	ComparatorSortDesc(){
		
	}

	@Override
	public int compare(Individual o1, Individual o2) {
		
		if(o1.FITNESS <= o2.FITNESS)
		{
			return 1;
		}
		else if(o1.FITNESS > o2.FITNESS)
		{
			return -1;
		}
		
		return 0;
	}

}
