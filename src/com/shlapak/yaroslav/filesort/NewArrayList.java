package com.shlapak.yaroslav.filesort;

import java.util.ArrayList;
import java.util.Vector;

public class  NewArrayList<T> extends ArrayList<T> {
	public NewArrayList() {
		super();
	}
	
	public void swap(int index1, int index2) {
		this.set(index1, this.set(index2, this.get(index1)));
	} 
}