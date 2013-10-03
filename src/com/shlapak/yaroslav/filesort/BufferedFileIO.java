package com.shlapak.yaroslav.filesort;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class BufferedFileIO <T extends Number> {
	
	private Charset charset;
	
	public BufferedFileIO(String charsetName) {
		this.charset = Charset.forName(charsetName);
	}

	public List<Double> readFile(Path path, List<Double> list) {
		try (BufferedReader reader = Files.newBufferedReader(path, charset)) {
			String line = null;
			while ((line = reader.readLine()) != null) {
				list.add(Double.parseDouble(line));
			}
		} catch (IOException x) {
			System.err.format("IOException: %s%n", x);
		}
		return list;
	}
	
	public void writeFile(Path path, List<T> list) {
		try (BufferedWriter writer = Files.newBufferedWriter(path, charset)) {
			for(T d : list) {
				String s = String.valueOf(d);
				writer.write(s, 0, s.length());
				writer.newLine();
			}
		} catch (IOException x) {
		    System.err.format("IOException: %s%n", x);
		}
	}
}