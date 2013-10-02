package com.shlapak.yaroslav.filesort;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

public class QuickSort <T extends Number> {
	
	private int first, last, boundLo, boundHi, pivot;
	int temp[] = {0, 0};
	
	public QuickSort() {
		super();
	}
	
	public void sort(List<T> list) {
		
		Deque<int[]> stack = new ArrayDeque<int[]>();

		first = 0;
		last = list.size() - 1;
		
		stack.push(new int[] {first, last});

		while(!stack.isEmpty()) {
			sortStep(list, stack);	
		}
	}
	
	private void sortStep(List<T> list, Deque<int[]> stack) {

		temp = stack.pop();
		first = temp[0];
		last = temp[1];

		boundLo = first;
		boundHi = last;
		pivot = last;

		while(first < last) {
			if(list.get(first).doubleValue() >= list.get(pivot).doubleValue()) {
				last--;
				if(first != last) 
					Collections.swap(list, first, last);			
				Collections.swap(list, last, pivot);
				pivot--;
			}
			else first++;
		}

		if(boundLo < (pivot - 1)) 
			stack.add(new int[] {boundLo, pivot - 1});
		
		if(boundHi > (pivot + 1)) 
			stack.add(new int[] {pivot + 1, boundHi});
	}
	
}


