/**
 * This program demonstrates the ++ and -- operators in prefix mode.
 */

public class Prefix {
	public static void main(String[] args) {

		int i = 3;
		int j = ++i; // prefix increment
		System.out.println(i); // prints 4
		System.out.println(j); // prints 4

		i = 3;
		j = i++; // postfix increment
		System.out.println(i); // prints 4
		System.out.println(j); // prints 3

	}
}
