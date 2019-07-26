
import java.util.Scanner;

import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class DataWork {

	public static ArrayList<CreditCard> LoadFromFile(String Filename) throws IOException {
		ArrayList<CreditCard> CCList = new ArrayList<CreditCard>();
		int totalCash = 0;

		Scanner in = new Scanner(new FileReader(Filename));
		totalCash = Integer.parseInt(in.nextLine());
		while (in.hasNextLine()) {
			String NumberCC = in.next();
			String Pincode = in.next();
			int Balance = Integer.parseInt(in.next());
			boolean Active = Boolean.parseBoolean(in.next());
			CCList.add(new CreditCard(NumberCC, Pincode, Balance, Active));

		}
		in.close();

		return CCList;
	}

	public static int LoadFromFileCash(String Filename) throws IOException {
		int totalCash = 0;
		Scanner in = new Scanner(new FileReader(Filename));
		totalCash = Integer.parseInt(in.nextLine());
		in.close();

		return totalCash;
	}

	public static void SaveToFile(List<CreditCard> CClist, String Filename, int totalCash) throws IOException {
		int i = 0;
		File fw = new File(Filename);
		fw.createNewFile();
		FileWriter writer = new FileWriter(fw);
		writer.write(String.valueOf(totalCash) + System.getProperty("line.separator"));
		for (i = 0; i < CClist.size() - 1; i++) {
			CreditCard CC = CClist.get(i);

			writer.write(CC.NumberCC + " " + CC.Pincode + " " + String.valueOf(CC.Balance) + " "
					+ String.valueOf(CC.Active) + System.getProperty("line.separator"));

		}

		CreditCard CC = CClist.get(i);
		writer.write(
				CC.NumberCC + " " + CC.Pincode + " " + String.valueOf(CC.Balance) + " " + String.valueOf(CC.Active));
		writer.close();
	}

}
