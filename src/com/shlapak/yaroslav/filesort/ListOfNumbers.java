package com.shlapak.yaroslav.filesort;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.Vector;

public class ListOfNumbers {
    private Vector<Integer> victor;
    private static final int SIZE = 10;

    public ListOfNumbers (String fileName) {
        victor = new Vector<Integer>(SIZE);
        for (int i = 0; i < SIZE; i++)
            victor.addElement(new Integer(i));
        
        this.readList(fileName);
        this.writeList();
    }
    
    public void readList(String fileName) {
    	String line = null;
    	try (RandomAccessFile raf = new RandomAccessFile(fileName, "r")) {
    		while ((line = raf.readLine()) != null) {
    			Integer i = new Integer(Integer.parseInt(line));
    			System.out.println(i);
    			victor.addElement(i);
    		}
    	} catch(FileNotFoundException fnf) {
    		System.err.println("File: " + fileName + " not found.");
    	} catch (IOException io) {
    		System.err.println(io.toString());
    	}

    }
    
    public void writeList() {
        PrintWriter out = null;

        try {
            System.out.println("Entering try statement");
            out = new PrintWriter(new FileWriter("OutFile.txt"));
        
            for (int i = 0; i < SIZE; i++)
                out.println("Value at: " + i + " = " + victor.elementAt(i));
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Caught ArrayIndexOutOfBoundsException: " +
                                 e.getMessage());
        } catch (IOException e) {
            System.err.println("Caught IOException: " + e.getMessage());
        } finally {
            if (out != null) {
                System.out.println("Closing PrintWriter");
                out.close();
            } else {
                System.out.println("PrintWriter not open");
            }
        }
    }
}
