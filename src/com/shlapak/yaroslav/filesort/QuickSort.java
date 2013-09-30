package com.shlapak.yaroslav.filesort;

import java.util.ArrayDeque;
import java.util.Deque;

public class QuickSort <T extends Number> {
	
	private Integer first, last, boundLo, boundHi, pivot;
	Integer temp[] = {0, 0};
	
	public QuickSort() {
		super();
	}
	
	public void sort(NewArrayList<T> vect) {
		Deque<Integer[]> stack = new ArrayDeque<Integer[]>();
		// initialize indices
		first = 0;
		last = vect.size() - 1;
		// push first group of indices into stack
		stack.push(new Integer[] {first, last});
		// apply sorting loop
		while(!stack.isEmpty()) {
			sortStep(vect, stack);	
		}
	}
	
	private void sortStep(NewArrayList<T> vect, Deque<Integer[]> stack) {
		// initialize indices
		temp = stack.pop();
		first = temp[0];
		last = temp[1];
		// initialize bounds
		boundLo = first;
		boundHi = last;
		pivot = last;
		System.out.println("Bounds: " + boundLo + " " + boundHi);
		// sort loop step
		while(first < last) {
			if(vect.get(first).doubleValue() >= vect.get(pivot).doubleValue()) {
				last--;
				if(first != last) 
					vect.swap(first, last);			
				vect.swap(last, pivot);
				pivot--;
			}
			else first++;
			System.out.println(vect);
		}
		// add new bound indices
		if(boundLo < (pivot - 1)) {
			stack.add(new Integer[] {boundLo, pivot - 1});
		}
		if(boundHi > (pivot + 1)) {
			stack.add(new Integer[] {pivot + 1, boundHi});
		}

		System.out.println("Stack size: " + stack.size());
	}
	
}


