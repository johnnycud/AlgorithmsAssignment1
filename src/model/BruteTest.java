package model;


import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

	/**
	 * BruteTest class for BruteAuto
	 * This test classes, tests the three methods in the Brute Auto Complete class
	/**
	 * @author John Cuddihy
	 * @version 04/11/2016
	 */
	public class BruteTest {
		
		public static ArrayList<Term> termsTest = new ArrayList<Term>();

		
		/**
		 * Setup an arraylist in order to test to best
		 *  predict the results of the tests.
		 */
		@Before
		public void setup(){
			Term woe = new Term(11109.0, "woe");Term but = new Term(2110.0, "but");
			Term what = new Term(3210.0, "what");Term it = new Term(4321.0, "it");
			Term dove = new Term(5432.0, "dove");Term rave = new Term(6543.0, "rave");
			Term wow = new Term(7654.0, "wow");Term wood = new Term(8765.0, "wood");
			Term wooden = new Term(9876.0, "wooden");
			
			
			termsTest.add(woe);termsTest.add(but);termsTest.add(what);
			termsTest.add(it);termsTest.add(dove);termsTest.add(rave);termsTest.add(wow);
			termsTest.add(wood);termsTest.add(wooden);
			
		}
		
		
		/**
		 * tests bestMatch method by checking every way of spelling the returns the as best match
		 */
		@Test
		public void testBestMatch(){
			BruteAutocomplete b = new BruteAutocomplete(termsTest);
			assertEquals("dove", b.bestMatch("d"));assertEquals("rave", b.bestMatch("r"));
			assertEquals("wooden", b .bestMatch("woo"));
		}
		

		/**
		 * Tests the weightOf method
		 */
		@Test
		public void testWeightOf(){
			BruteAutocomplete b = new BruteAutocomplete(termsTest);
			//tests the weight is returned
			assertEquals(6543.0, b.weightOf("rave") , 0.001);
			
			//tests if the prefix does not exsist that 0.0 is returned
			assertEquals(0.0, b.weightOf("zyxwvu"), 0.001);
			
			//test to see if prefix is not complete word, it searchs for best match and returns weight
			assertEquals(7654.0, b.weightOf("wow"), 0.001);
		}

		/**
		 * Tests matches method
		 */
		@Test
		public void testTopMatches(){
			BruteAutocomplete b = new BruteAutocomplete(termsTest);
			
			//sets up an arraylist of expected results
			ArrayList<String> eList1 = new ArrayList<>();
			eList1.add("woe");
			
			//tests matches for the looking for 1 search
			assertEquals(eList1, b.matches("woe", 1));
			
			//sets up an arraylist of expected results
			ArrayList<String> eList2 = new ArrayList<>();
			eList2.add("woe");eList2.add("wow");
			eList2.add("wood");eList2.add("wooden");
			
			//test returns 4 matches
			assertEquals(eList2, b.matches("wo", 4));
			
			//test that it will return the max number of matches if a higher "k" is inserted
			assertEquals(eList2, b.matches("wo", 10));
			
			//tests that .toLowerCase changes the prefix so that it will search no matter of caps lock
			String upper = "WO".toLowerCase();
			assertEquals(eList2, b.matches(upper, 4));
			
			//sets up a blank arraylist for expected results
			ArrayList<String> eList3 = new ArrayList<>();
			
			//returns blank array is no match is found
			assertEquals(eList3, b.matches("zoolander", 10));
			
		}
		


}
