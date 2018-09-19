import java.util.Scanner; 

public class main {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		System.out.println("Please select either console (c) or file (f) input: ");
		String s = scan.next();
		scan.close();
		System.out.printf("You chose: %s \n", s);

	}

}
