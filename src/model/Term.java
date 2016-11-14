package model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Term Class for BruteAuto
 * This class reads in data from a .txt file and saves it to an arrayList
 * @author John Cuddihy
 * @version 04/11/2016
 */

public class Term{
	
	public String term;
	public double weight;
	
	public static ArrayList<Term> newList = new ArrayList<Term>();
	
	private static Scanner scanner;
	
	/**
	 * Term Constructor
	 * @param weight
	 * @param term
	 */
	public Term(double weight ,String term){
		this.weight = weight;
		this.term = term;
	}

	/**
	 * Read in terms method, using scanner
	 * trims each line read,
	 * splits each line
	 * parses double "weight"
	 * saves to arraylist termsList
	 * @throws IOException
	 */
	public static void inTerms() throws IOException{
		File items = new File("././data/datalist.txt");
		scanner = new Scanner(items);
		String spaces = "[	]";
		while(scanner.hasNextLine()){
			String itemsGot = scanner.nextLine();
			itemsGot = itemsGot.trim();
	
			String[] termItems = itemsGot.split(spaces);
			
			Term itemList = new Term(Double.parseDouble(termItems[0]), termItems[1]);
			
			getTermsList().add(itemList);
		}
			}
	
	/**
	 * Getter and setters 
	 * and toString methods
	 * @return
	 */
	public String getTerm(){
		return term;
	}
	
	public double getWeight(){
		return weight;
	}
	
	public String toString(){
		return String.format("%14.1f\t%s", weight, term);
	}

	public static ArrayList<Term> getTermsList() {
		return newList;
	}

	public static void setTermsList(ArrayList<Term> termsList) {
		Term.newList = termsList;
	}



}
