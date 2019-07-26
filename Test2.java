
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Test2 {

	public static int GetIndexCC(List<CreditCard> CClist, Scanner in) {
		// Scanner in = new Scanner(System.in);
		boolean isInputValid;
		int Index = -1;

		do {
			System.out.println("Please, enter the card number");
			System.out.println("XXXX-XXXX-XXXX-XXXX");
			String eCardNumber = in.nextLine();
			isInputValid = Pattern.matches("^[0-9]{4}-[0-9]{4}-[0-9]{4}-[0-9]{4}$", eCardNumber);

			if (!isInputValid) {
				System.out.println("Please, enter the correct CC number");
				System.out.println("");
				continue;
			}
			isInputValid = false;
			for (int i = 0; i < CClist.size(); i++) {
				if (eCardNumber.equals(CClist.get(i).NumberCC)) {
					Index = i;
					break;
				}

			}
			if ((Index >= 0) && (!CClist.get(Index).Active)) {
				System.out.println("Your CC is bloacked");
				System.out.println("");
			} else if (Index < 0) {

				System.out.println("Such CC doesn't exist");
				System.out.println("");
			} else {
				isInputValid = true;
			}

		} while (!isInputValid);

		return Index;
	}

	public static boolean CheckPincode(String pin, Scanner in) {
		boolean isInputValid;
		// = new Scanner(System.in);
		int enterenceCount = 0;

		isInputValid = false;
		do {
			System.out.println("Please, enter the pincode");
			System.out.println("         XXXX");
			System.out.println("");
			String pincode = in.nextLine();
			isInputValid = Pattern.matches("^[0-9]{4}$", pincode);

			if (!isInputValid) {
				System.out.println("The correct pincode should have 4 numbers");
				System.out.println("");
				continue;
			}
			if (!pincode.equals(pin)) {
				enterenceCount++;
				isInputValid = false;
				System.out.println("Pincode entered incorectly");
				System.out.println(String.valueOf(3 - enterenceCount) + " attempts left");
				System.out.println("");
				if (enterenceCount == 3) {
					System.out.println("This CC is temproary blocked");
					return false;
				}
			}

		} while (!isInputValid);

		return true;
	}

	public static void main(String[] args) throws IOException {
		char a;
		String fileName = "Test.txt";
		int indexCC, totalCash = 0;
		Scanner in = new Scanner(System.in);

		ArrayList<CreditCard> CCList = new ArrayList<CreditCard>();
		CCList = DataWork.LoadFromFile(fileName);
		totalCash = DataWork.LoadFromFileCash(fileName);

		do {
			indexCC = GetIndexCC(CCList, in);
			// System.out.println(String.valueOf(CCList.get(indexCC).Pincode));
			CCList.get(indexCC).Active = CheckPincode(CCList.get(indexCC).Pincode, in);
			DataWork.SaveToFile(CCList, fileName, totalCash);
		} while (!CCList.get(indexCC).Active);

		boolean end = false;
		do {
			a = Menu.MeinMeu(in).charAt(0);
			switch (a) {
			case ('1'): {
				System.out.println("You Balanse is:" + String.valueOf(CCList.get(indexCC).Balance));
				System.out.println("");
				break;
			}
			case ('2'): {
				System.out.println("Enter a sum to withdraw");
				int sum = Integer.parseInt(in.nextLine());
				if (CCList.get(indexCC).Balance < sum) {
					System.out.println("You have not enough money to complite this operation");
					break;
				}
				if (totalCash < sum) {
					System.out.println("Cash machine is out of stock");
					System.out.println("Muximum sum to withdraw is " + String.valueOf(totalCash));
					break;
				}
				CCList.get(indexCC).Balance -= sum;
				totalCash -= sum;
				System.out.println("You successfully withdraw " + String.valueOf(sum));
				break;
			}
			case ('3'): {
				System.out.println("Enter a sum to deposit");
				int sum = Integer.parseInt(in.nextLine());
				if (sum > 1000000) {
					System.out.println("Cash machine cannot deposit such big sum");
					System.out.println("Muximum sum to withdraw is 1 000 000");
					break;
				}
				CCList.get(indexCC).Balance += sum;
				totalCash += sum;
				System.out.println("You successfully deposit " + String.valueOf(sum));
				break;
			}
			case ('4'): {
				System.out.println("Good luck");
				end = true;
				break;
			}
			}

		} while (!end);
		in.close();
		DataWork.SaveToFile(CCList, fileName, totalCash);
	}
}
