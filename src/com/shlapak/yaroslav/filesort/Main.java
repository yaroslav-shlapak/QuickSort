package com.shlapak.yaroslav.filesort;

import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.SwingUtilities;

public class Main {

	public static void main(String[] args) {
		
		String charsetName = "UTF-8";
		
		Path pathForSort = Paths.get("D:\\temp\\ololo.txt");
		Path pathSorted = Paths.get("D:\\temp\\ololoSorted.txt");
		
		BufferedFileIO bufFile = new BufferedFileIO(charsetName);
		
		NewArrayList<Double> vv = new NewArrayList<Double>();
		vv = bufFile.readFile(pathForSort);
		QuickSort<Double> qs = new QuickSort<Double>();
		System.out.println(vv);
		System.out.println("Test1");
		qs.sort(vv);
		System.out.println(vv);
		System.out.println("Test3");
		bufFile.writeFile(pathSorted, vv);
		
		
		
		
		// запуск программы в safe потоке
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				setGUI();
			}
		});
	}

	private static void setGUI() {
		Bankomat gui = new Bankomat();
	}

	
}

