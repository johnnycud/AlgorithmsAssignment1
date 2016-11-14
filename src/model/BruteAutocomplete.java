package model;

import java.util.ArrayList;
import java.util.List;

/**
 * BruteAutoComplete Class for BruteAuto
 * The class implements AutoComplete Interface
 *  @author John Cuddihy
 * @version 04/11/2016
 */



	public class BruteAutocomplete implements AutoCompleteInterface {
		
		Term term;
		
		List<Term> termsList;
		
		String maxTerm;
		String terms;
		double maxWeight = -1.0;
		
		
		/**
		 * BruteAutocomplete constructor
		 * @param termsList
		 */
		  public BruteAutocomplete(List<Term> termsList) {
			  this.termsList = termsList;
		  }
		  
		 /**
		  * Returns the weight of the term, or 0.0 if no such term
		  */
		@Override
		public double weightOf(String terms) {
			double termWeight = 0.0;
			for (Term t : termsList) {
				if(t.getWeight() > termWeight && t.getTerm().toLowerCase().startsWith(terms)){
					termWeight = t.getWeight();
				}
			}
			return termWeight;
		}
		
		/**
		 * bestMatch method
		 * Returns the 
		 * highest weighted matching term, or null if no matching term.
		 */
		@Override
		public String bestMatch(String prefix){
			for (Term t : termsList){
				if(t.getWeight() > maxWeight && t.getTerm().toLowerCase().startsWith(prefix)){
					maxWeight = t.getWeight();
					maxTerm = t.getTerm();
				}
			}
			return maxTerm;
		}
		
		/**
		 *  Returns the highest weighted k matching terms (in descending order of weight), as an
          *  iterable.
          *  If fewer than k matches, return all matching terms (in descending order
          *  of weight).
		 */
		@Override
		public Iterable<String> matches(String prefix, int k) {
			String terms;
			int count = 0;
			ArrayList<String> matches = new ArrayList<String>();
			for (Term t : termsList){
				if(count <= k-1){
				if(t.getWeight() > maxWeight && t.getTerm().toLowerCase().startsWith(prefix)){
					terms = t.getTerm();
					matches.add(terms);
					count++;
					}
				}
			}
		return matches;
		}

	}

