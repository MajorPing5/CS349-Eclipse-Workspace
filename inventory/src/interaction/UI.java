package interaction;

import java.io.*;
import javax.swing.JOptionPane;

public class UI {
	private String entry;
	
	public String getEntry() {
		return entry;
	}
	
	public void setEntry(String entry) {
		this.entry = entry;
	}
	// custom methods
	/**
	 * 
	 */
	public int invokeChoice() throws IOException {
		// Prompt user for which option they would prefer to choose
		// Must convert from string to int, and verify if it's an int or not
		entry = JOptionPane.showInputDialog(""); // Missing prompt
		return entry;
	}
	
}
