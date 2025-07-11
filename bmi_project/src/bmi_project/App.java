package bmi_project;
import java.util.Scanner; // needed for console input

public class App {

	public static void main(String[] args) {
		
		// declare variables
		double height, weight, bmi;
		String firstName, lastName;

		// create the scanner object
		Scanner input = new Scanner(System.in);
				
		// prompt for the name
		System.out.println("Enter first name");
		firstName = input.nextLine();
		System.out.println("Enter last name");
		lastName = input.nextLine();
		
		// prompt for weight and height
		System.out.println("Enter weight (lbs)");
		weight = input.nextDouble();
		System.out.println("Enter height (in)");
		height = input.nextDouble();
		
		// compute the bmi
		bmi = (weight/Math.pow(height,2)) * 703;
		
		// show output
		System.out.println("Hello "+firstName+""+lastName+", "
				+"your bmi is "+bmi);
		
		System.exit(0);
	}

}
