
public class CreditCard {
	String NumberCC; // nomer karty
	String Pincode; // pinkod karty
	int Balance; // balans scheta
	boolean Active; // aktivna li karta

	public CreditCard(String NumberCC, String Pincode, int Balance, boolean Active) {
		this.NumberCC = NumberCC;
		this.Pincode = Pincode;
		this.Balance = Balance;
		this.Active = Active;

	}
}
