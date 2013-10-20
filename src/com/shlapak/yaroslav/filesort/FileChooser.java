package com.shlapak.yaroslav.filesort;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/*
 * FileChooserDemo.java uses these files:
 *   images/Open16.gif
 *   images/Save16.gif
 */
public class FileChooser extends JPanel {
	static private final String newline = "\n";
	JButton openButton, sortButton;
	JTextArea log;
	private JTextField openPathTxt, savePathTxt;
	JFileChooser fc;
	private File openFile, saveFile;
	private JPanel mainPanel;
	private JPanel secondPanel;
	//private JFrame frame;

	public FileChooser() {
		super(new BorderLayout());

		// Create the log first, because the action listeners
		// need to refer to it.
		log = new JTextArea(20, 30);
		log.setMargin(new Insets(5, 5, 5, 5));
		log.setEditable(false);
		JScrollPane logScrollPane = new JScrollPane(log);
        //addComponents();
		
		//create Listeners
		ActionListener openButtonListener = new OpenButtonListener();
		ActionListener sortButtonListener = new SortButtonListener();

		// Create a file chooser
		fc = new JFileChooser();

		// Uncomment one of the following lines to try a different
		// file selection mode. The first allows just directories
		// to be selected (and, at least in the Java look and feel,
		// shown). The second allows both files and directories
		// to be selected. If you leave these lines commented out,
		// then the default mode (FILES_ONLY) will be used.
		//
		// fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		// fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

		// Create the open button. We use the image from the JLF
		// Graphics Repository (but we extracted it from the jar).
		openFile = (Paths.get("D:\\temp\\ololo.txt")).toFile();
		saveFile = (Paths.get("D:\\temp\\ololoSorted.txt")).toFile();
		openPathTxt = new JTextField(openFile.toString());
		savePathTxt = new JTextField(saveFile.toString());
		
		
		openButton = new JButton("Open a File", createImageIcon("/Open16.gif"));
		openButton.addActionListener(openButtonListener);

		// Create the save button. We use the image from the JLF
		// Graphics Repository (but we extracted it from the jar).

		sortButton = new JButton("Sort a File", createImageIcon("/Sort16.gif"));
		sortButton.addActionListener(sortButtonListener);

		// For layout purposes, put the buttons in a separate panel
		mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(2, 2));
        mainPanel.add(openPathTxt);
		mainPanel.add(openButton);
		mainPanel.add(savePathTxt);
		mainPanel.add(sortButton);

		// Add the buttons and the log to this panel.
		add(mainPanel, BorderLayout.PAGE_START);
		add(logScrollPane, BorderLayout.PAGE_END);
	}


	/** Returns an ImageIcon, or null if the path was invalid. */
	protected static ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = FileChooser.class.getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

	/**
	 * Create the GUI and show it. For thread safety, this method should be
	 * invoked from the event dispatch thread.
	 */
	private static void createAndShowGUI() {
		// Create and set up the window.
		JFrame frame = new JFrame("FileSort");
		//frame.getContentPane().add(mainPanel);
        frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(200, 150);
        frame.setLocation(300, 300);
        frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Add content to the window.
		frame.add(new FileChooser());

		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}

	public static void run() {
		// Schedule a job for the event dispatch thread:
		// creating and showing this application's GUI.
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				// Turn off metal's use of bold fonts
				UIManager.put("swing.boldMetal", Boolean.FALSE);
				createAndShowGUI();
			}
		});
	}
	private class OpenButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == openButton) {
				int returnVal = fc.showOpenDialog(FileChooser.this);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					openFile = fc.getSelectedFile();
					openPathTxt.setText(openFile.toString());
					// This is where a real application would open the file.
					log.append("Opening: " + openFile.getName() + "." + newline);
				} else {
					log.append("Open command cancelled by user." + newline);
				}
				log.setCaretPosition(log.getDocument().getLength());
			}

		}

	}
	private void saveFile() {

				int returnVal = fc.showSaveDialog(FileChooser.this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					saveFile = fc.getSelectedFile();
					// This is where a real application would save the file.
					savePathTxt.setText(saveFile.toString());
					log.append("Saving: " + saveFile.getName() + "." + newline);
				} else {
					log.append("Save command cancelled by user." + newline);
				}
				log.setCaretPosition(log.getDocument().getLength());



	}
	private class SortButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == sortButton) {
				String charsetName = "UTF-8";
				BufferedFileIO<Double> bufFile = new BufferedFileIO<Double>(charsetName);
				
				List<Double> vv = new ArrayList<Double>();
				
				vv = bufFile.readFile(openFile.toPath(), vv);
				QuickSort<Double> qs = new QuickSort<Double>();
				System.out.println(vv);
				System.out.println(vv.size());
				qs.sort(vv); 
				log.append("Sorted!" + newline);
				log.setCaretPosition(log.getDocument().getLength());
				System.out.println(vv);
				saveFile();
				bufFile.writeFile(saveFile.toPath(), vv);
			}

		}

	}
}


