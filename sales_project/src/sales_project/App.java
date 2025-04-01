package sales_project;

import java.io.*;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String fileName = getFileName();
			
		double totalSales = getTotalSales(fileName);
		double average = getAverage(totalSales);
		double highest = getHighest(fileName);
		double lowest = getLowest(fileName);
		
		JOptionPane.showMessageDialog(null,  "Total sales ="+totalSales+"\n"
		+"Average sale = "+average+"\n"
		+"Highest sale = "+highest+"\n"
		+"Lowest sale = "+lowest);
		
		System.exit(0);
	}
	
	/**
	 * getFileNme method
	 * @return file name from user
	 */
	
	public static String getFileName() {
		String fileName = JOptionPane.showInputDialog("Enter the name"+
				" of the file including the extension.");
		return fileName;
	}
	
	/**
	 * getTotalSales method read from the file and calculate the total sales
	 * @param fileName
	 * @return totalSales
	 */
	public static double getTotalSales(String fileName) {
		double sales, totalSales = 0;
		
		try {
			// Open the file
			File file = new File("src/"+fileName);
			
			// Read from the file
			Scanner fileReader = new Scanner(file);
			
			//use loops to read the entire file
			while(fileReader.hasNext()) {
				sales = fileReader.nextDouble();
				
				//add to the sales total
				totalSales += sales;
			}
			
			fileReader.close();
		}
		catch(IOException e) {
			JOptionPane.showMessageDialog(null, "Something went wrong.");
		}
		
		return totalSales;
	}
	
	/**
	 * getAverage method
	 * @param total
	 * @return average sale
	 */
	public static double getAverage(double total) {
		return total/30;
	}
	
	/**
	 * getHighest method: find the highest sale
	 * @param fileName
	 * @return highest sale
	 */
	public static double getHighest(String fileName) {
		double sales, highest = 0;
		
		try {
			// open the file
			File file = new File("src/"+fileName);
			Scanner fileReader = new Scanner(file);
			
			// read the first sale
			highest = fileReader.nextDouble();
				
			while(fileReader.hasNext()) {
				sales = fileReader.nextDouble();
				
				if(sales > highest) {
					highest = sales;
				}
			}
			fileReader.close();
		}
		
		catch(IOException ex) {
			JOptionPane.showMessageDialog(null,  "Something went wrong.");
		}
		
		return highest;
	}
	
	/**
	 * getLowest method: find the lowest sale
	 * @param fileName
	 * @return lowest sale
	 */
	public static double getLowest(String fileName) {
		
		// create variables
		double sales, lowest = 0;
		
		try {
			// open the file
			File file = new File("src/"+fileName);
			Scanner fileReader = new Scanner(file);
			
			// read the first sale
			lowest = fileReader.nextDouble();
			
			// loop through the entire file
			// starting from the second line
			while(fileReader.hasNext()) {
				sales = fileReader.nextDouble();
				
				if(sales < lowest) {
					lowest = sales;
				}
			}
			fileReader.close();
		}
		
		catch(IOException ex) {
			JOptionPane.showMessageDialog(null,  "Something went wrong");
		}
		return lowest;
	}
}
