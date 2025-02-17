package bmi_project;
import javax.swing.JOptionPane;

public class AppDialog {

	public static void main(String[] args) {
		String bmi_input;
		double weight, height, bmi;
		String firstName, lastName;
		
		// Prompt the user for their first and last name
		firstName = JOptionPane.showInputDialog("What is your first name?");
		lastName = JOptionPane.showInputDialog("What is your last name?");
		
		//Prompt the user for their BMI information
		bmi_input = JOptionPane.showInputDialog("Please enter your weight (lbs)");
		weight = Double.parseDouble(bmi_input);
		
		bmi_input = JOptionPane.showInputDialog("Please enter your height (in)");
		height = Double.parseDouble(bmi_input);
		
		// calculate bmi
		bmi = (weight / (Math.pow(height, 2))) * 703; 
		
		// show bmi 
		JOptionPane.showMessageDialog(null,"Hi " + firstName + " " + lastName + ", your BMI is " + bmi);		
	}

}
