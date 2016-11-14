package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * BruteAutoClient Client Class for BruteAuto
 * This is a menu driven class, where the user can input a prefix to search best matches
 * top match and the weight through a cli interface.
 *  @author John Cuddihy
 * @version 04/11/2016
 */

public class BruteAutoClient {
	
	static Term term;
	static BruteAutocomplete slow;
	private static String prefix;
	private static int k;
	private Scanner read;
	private static ArrayList<Term> newList;

	/**
	 * Client Constructor which instantiates scanner and bruteauto class 
	 */
	public BruteAutoClient(){
		read = new Scanner(System.in);
		slow = new BruteAutocomplete(newList);
		//comp = new TermComparator();
	}
	

	/**
	 * Main method
	 * Runs term class, read in terms
	 * Runs app - CLI interface
	 * @param args
	 */
	public static void main(String args[]){
		BruteAutoClient app = new BruteAutoClient();

		try {
			Term.inTerms();
		} catch (IOException e) {
			System.out.println(e + "Array failing to pop");
		}
		newList = Term.getTermsList();
		app.run();
	}
	
	
	/**
	 * Run method
	 * CLI interface, reads in prefix and k
	 * calls methods from brute auto
	 */
	public void run(){
		try{
		System.out.println("Please input a prefix in which you want to search for matches:");
		String inputP = read.nextLine().toLowerCase();
	    setPrefix(inputP);
		}catch(Exception e){
			System.out.println(e.getMessage());
			run();
		}
		try{
		System.out.println("Please enter a max search number between 1-10:");
		int inputK = read.nextInt();
		if(inputK > 10){
			System.out.println("Invalid option, please pick a number between 1 and 10:");
			run();
		}else{
		setK(inputK);
		}
		}catch(Exception i){
			System.out.println("Invalid option: " + i.getMessage());
			run();
		System.out.println("----------");
	}
		slow = new BruteAutocomplete(newList);
		int option = menu();
		while(option != 0){
			switch (option){
			case 1: 
				System.out.println("\nList of matches for " + getPrefix() + " is: ");
				System.out.println(slow.matches(prefix, k));
				break;
			case 2:
				System.out.println("\nThe best match for " + getPrefix() + " is: ");
				System.out.println(slow.bestMatch(prefix));
				break;
			case 3:
				System.out.println("\nThe weight for " + getPrefix() + " is: ");
				System.out.println(slow.weightOf(prefix));
				//brute.matches(prefix, k);
				break;
			default:
				System.out.println("Invalid option entered: " + option);
				break;
			}
			System.out.println("\n Press any key to continue.");	
			option = menu();
		}
		System.out.println("Good eh :) \nGoodBye!");		
	}
	
	/**
	 * menu method
	 * outputs options for CLI
	 * returns users choice
	 * @return
	 */
	private int menu(){
		System.out.println("----------");
		System.out.println("Please select the number of the option you want below.");
		System.out.println("   1) List of Matches of the prefix");
		System.out.println("   2) Best Match of the prefix");
		System.out.println("   3) Weight of prefix.");
		System.out.println("-----------\n   0)Exit");
		int choice = read.nextInt();
		return choice;
	}
	
	/**
	 * to string methods
	 * setters and getters for client class
	 */
	
	public String toString(){
		//return "Prefix:" + prefix + ", Number of wanted matches: " + getK();
		return String.format("%14.1f\t%s", k, prefix);
	}
	
	public static void setPrefix(String prefix){
		BruteAutoClient.prefix = prefix;
	}
	
	public String getPrefix(){
		return prefix;
	}

	public static int getK() {
		return k;
	}

	public static void setK(int k) {
		BruteAutoClient.k = k;
	}

}
