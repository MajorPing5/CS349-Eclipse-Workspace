package grade;

import java.util.Scanner;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double average = -1,
				score1 = -1,
				score2 = -1,
				score3 = -1;
		
		Scanner keyboard = new Scanner(System.in);
				
		System.out.println("This program averages 3 test scores.");

		// Get the first score.
		
		while(score1 < 0) {
			System.out.print("Enter score #1: ");

			while(!keyboard.hasNextDouble()) {
				System.out.println("Please enter the correct score");
				keyboard.next();
			}
			
			score1 = keyboard.nextDouble();
			keyboard.nextLine(); // CONSUME any remaining newline
		}
		
		while(score2 < 0) {
			System.out.print("Enter score #2: ");

			while(!keyboard.hasNextDouble()) {
				System.out.println("Please enter the correct score");
				keyboard.next();
			}
			
			score2 = keyboard.nextDouble();
			keyboard.nextLine(); // CONSUME any remaining newline
		}

		while(score3 < 0) {
			System.out.print("Enter score #3: ");

			while(!keyboard.hasNextDouble()) {
				System.out.println("Please enter the correct score");
				keyboard.next();
			}
			
			score3 = keyboard.nextDouble();
			keyboard.nextLine(); // CONSUME any remaining newline
		}
		
		average = (score1 + score2 + score3) / 3.0;
		
		if(average<60)
			System.out.println("Grade F");
		
	}

}
