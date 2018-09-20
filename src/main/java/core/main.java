package core;

import java.util.Scanner;

import core.ConsoleGame; 

public class main {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		System.out.println("Please select either console (c) or file (f) input: ");
		String s = scan.next();
		//scan.close();
		System.out.printf("You chose: %s \n\n", s);
		if(s.equals("c")) {
			ConsoleGame consoleGame = new ConsoleGame();
			consoleGame.main();
		}
		else if(s.equals("f")) {
//			ConsoleGame consoleGame = new ConsoleGame();
//			consoleGame.main();
		}

	}

}
