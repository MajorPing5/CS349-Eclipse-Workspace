
import java.util.Scanner;
import java.util.Random;

/**
 * A program that simulates rolling two d6.
 *
 * @author Mark Young (A00000000)
 */
public class RollDice {

	public static void main(String[] args) {
		// create variables
		Scanner input = new Scanner(System.in);
		Random generator = new Random();
		int d1, d2, dice;

		// Introduce yourself
		System.out.println("\n\n" + "I'm just rollin' some dice!\n\n");
		System.out.println();
		System.out.print("Press Enter...");
		input.nextLine();
		System.out.println();

		// roll dice -- Math.random gives us a random number in range [0,1)
		d1 = 1 + generator.nextInt(6);
		d2 = 1 + generator.nextInt(6);

		dice = d1 + d2;

		// report the result
		System.out.println("I got a " + d1 + " and a " + d2 + ".\n" + "The total is " + dice + ".");
		System.out.println();
	}

}
