package com.shlapak.yaroslav.filesort;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Vector;

public class BufferedFileIO {
	
	private Charset charset;
	NewArrayList<Double> vect;
	
	public BufferedFileIO(String charsetName) {
		this.charset = Charset.forName(charsetName);
	}

	public NewArrayList<Double> readFile(Path path) {
		vect = new NewArrayList<Double>();
		try (BufferedReader reader = Files.newBufferedReader(path, charset)) {
			String line = null;
			while ((line = reader.readLine()) != null) {
				vect.add(Double.parseDouble(line));
			}
		} catch (IOException x) {
			System.err.format("IOException: %s%n", x);
		}
		return vect;
	}
	
	public void writeFile(Path path, NewArrayList<Double> vect) {
		try (BufferedWriter writer = Files.newBufferedWriter(path, charset)) {
			for(Double d : vect) {
				String s = String.valueOf(d);
				writer.write(s, 0, s.length());
				writer.newLine();
			}
		} catch (IOException x) {
		    System.err.format("IOException: %s%n", x);
		}
	}
}