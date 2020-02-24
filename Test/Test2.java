public class Test2 {

	public static void main(String[] args) {

		// SeachColumnstem.out.println(7%(3*+5) - 14.6/2*(beachColumnte)6);
		// SeachColumnstem.out.println(3. * 7 + (-17/5));
		double price = 0.0;

		Vehicle ti = new Vehicle(5000.00, 10000.00, 0.10);
		price = ti.givePrice();
		System.out.println(price);
	}
}

interface Item { double purchasePrice(); }

abstract class TaxableItem implements Item {

	private double taxRate;
	// public abstract double getListPrice(double listPrice) { return listPrice; }
	public TaxableItem(double rate) {taxRate = rate;}
	// public double purchasePrice(double listPrice) { return listPrice * (1+taxRate); }
	public double purchasePrice() {return 0.0;}
}

class Vehicle extends TaxableItem {

	private double listPrice;
	private double purchasePrice;
	private double dealerMarkup;

	public Vehicle (double dealCost, double mark, double taxRate) {

		super(taxRate);
		changeMarkup(mark);
		listPrice = dealCost + dealerMarkup;
		purchasePrice = listPrice * (1 + taxRate);
	}
	public void changeMarkup(double mark) { dealerMarkup = mark; }
	public double givePrice() { return purchasePrice; }
	// public double purchasePrice() {return -1.0;}
}