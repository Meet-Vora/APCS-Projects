public class PrimeNumbers {
	
	private int num;

	public PrimeNumbers(int n) {
		num = n;
	}

	public static void main(String[] args) {
		PrimeNumbers pn = new PrimeNumbers(101);
		if(pn.run())
			System.out.println("PRIME!!!");
		else
			System.out.println("NOT PRIME!");
	}

	public boolean run() {

		for(int a = num - 1; a > 2; a--) {
			if(num%a == 0)
				return false;
		}
		return true;
	}
}