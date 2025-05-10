// Written by Meera Sanjeevirao
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Bingo {
	// constants for win types
	public static final int HORIZONTAL = 1;
	public static final int VERTICAL = 2;
	public static final int DIAGONAL = 3;

	// 2D array to represent bingo card 
	private static int[][] card;
	private static boolean[][] bingoCard;
	
	// counts # of picks  
	private static int pickCount;
	
	public Bingo() { // constructor method 
		// initializes card, bingoCard, bingoCard, and pickCount
		card = new int[5][5];
		bingoCard = new boolean[5][5];
		pickCount = 0;
	}
	
	// main method to start bingo game 
	public static void main(String[] args) {  
		Bingo bingo = new Bingo(); // creates bingo object 
		bingo.fillCard("bingo.in"); // calls method to fill card from the bingo.in file
		bingo.printCard(); // calls method to print  initial card
		bingo.playGame(); // calls method to play game
		bingo.printCard(); // calls method to print card after game 
	}
	
	// fillCard method reads data from file and fills bingo card 
	public void fillCard(String filename) {
		Scanner scanner = null; // initailizes scanner with null value 
		try {
			scanner  = new Scanner(new File("bingo.in")); // reads data from bingo.in
			for(int row = 0; row < 5; row++) { // nested for loops fill the card array using data from bingo.in
				for(int col = 0; col < 5; col++) {
					bingoCard[row][col] = false;
					card[row][col] = scanner.nextInt();
				}
			}
		
		} catch (IOException e) { // both catch statements handle exceptions by printing the stack trace
			System.err.println("An IOException occurred: ");
			e.printStackTrace();
		} catch ( NumberFormatException e) {
			System.err.println("A NumberFomatException occurred: ");
			e.printStackTrace();
		}
	}
	
	// prints the current state of the bingo card 
	public void printCard() {
		System.out.println("YOUR BINGO CARD: ");
		System.out.println("B I N G O");
		for(int row = 0; row < 5; row++) { // prints the bingo card with values for unmarked numbers and X to represent marked numbers
			for(int col = 0; col < 5; col++) {
				if(bingoCard[row][col]) {
					System.out.print(" X "); }
				else {
					System.out.print(String.format("%2d ", card[row][col])); }
			}
		System.out.println();
		}
	}

	// playGame method to track picked numbers, check for duplicates, and  display win message
	public void playGame() {
		int winType = 0; 
		System.out.println("BINGO NUMBERS PICKED AT RANDOM FROM BIN: ");
		boolean[] pickedNumbers = new boolean[76]; // boolean array to track the picked numbers
		while(winType == 0 && pickCount <=75) {
			int randomNumber;
			do {
				randomNumber  = (int)(Math.round((Math.random() * 74) + 1)); // generates a number from 1-75 inclusive
			} while(pickedNumbers[randomNumber]); // checks for duplicates 
			
			pickedNumbers[randomNumber] = true; // marks number as picked 
			System.out.print(randomNumber + " ");

			if(pickCount > 0 && pickCount % 9 == 0) { 
				System.out.println();
			}
	
			markNumber(randomNumber); // calls to mark the number on card
			pickCount++; // increases pick count
			winType = checkForWin(); // calls to check for win condition 
			
			if(pickCount > 75) {
				break;
			}
		}
		System.out.println();
		// displays win message based on win type 
		if(winType == HORIZONTAL) {
			System.out.println("YOU WIN WITH A HORIZONTAL BINGO AFTER " + pickCount + " PICKS!"); }
		else if(winType == VERTICAL) {
			System.out.println("YOU WIN WITH A  VERTICAL BINGO AFTER " + pickCount + " PICKS!"); }
		else if(winType == DIAGONAL) {
			System.out.println("YOU WIN WITH A DIAGONAL BINGO AFTER " + pickCount + " PICKS!"); }
		else { System.out.println("NO WIN CONDITION"); }
	}		

	// markNumber method to mark picked numbers on bingo card 
	public void markNumber(int pickedNumber) {
		for(int i = 0; i < 5; i++) { // marks the number as picked on bingo card by setting the value to true 
			for(int j = 0; j < 5; j++) {
				if(card[i][j] == pickedNumber) {
					bingoCard[i][j] = true;
					return;
				}
			}
		}
	}

	// determines winType 
	public int checkForWin() {
		// check rows first
		for(int row = 0; row < 5; row++) {
			int rowSum = 0;
			for(int col = 0; col < 5; col++) {
				if(bingoCard[row][col]) {
					rowSum ++;;
				}
			}
			if(rowSum == 5) {
				return HORIZONTAL; // returns winType
			}
		}

		// check columns 
		for(int col = 0; col < 5; col++) {
			int colSum = 0;
			for(int row = 0; row < 5; row++) { 
				if(bingoCard[row][col]) {
					colSum ++;
				}
			}
			if(colSum == 5) {
				return VERTICAL; // returns winType 
			}
		}

		// check diagonal 1, top left to bottom right 
		int diag1 = 0;
		for(int row = 0; row < 5; row++) {
			if(bingoCard[row][row]) {
				diag1++;
			}
		}
		if(diag1 == 5) {
			return DIAGONAL; // returns winType 
		}

		// check diagonal, top right to bottom left 
		int diag2 = 0;
		for(int row = 0; row < 5; row++) {
			if(bingoCard[row][4 - row]) {
				diag2 ++;
			}
		}
		if(diag2 == 5) {
			return DIAGONAL; // returns winType 
		}
		return 0; // returns the  default value if no wintype is found 	
	}
}	
