import java.util.Scanner;
import java.util.regex.Pattern;

public class Menu {
	public static String MeinMeu(Scanner in) {
		boolean isInputValid;

		String operation;
		do {
			System.out.println("Choose operation: ");
			System.out.println("Enter 1 - to check the balance");
			System.out.println("Enter 2 - to withdraw cash");
			System.out.println("Enter 3 - to deposit");
			System.out.println("Enter 4 - to exit");

			operation = in.nextLine();
			isInputValid = Pattern.matches("^[1-4]{1}$", operation);

			if (!isInputValid) {
				System.out.println("Incorrect operation");
				System.out.println("");
			}
		} while (!isInputValid);

		return operation;
	}
}
