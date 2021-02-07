package main;

import java.util.Random;

public class HugeInteger {
	
	private char[] hugeInt;
	
	
	//Constructors//
	public HugeInteger(String val) throws Exception {
		int i;

		if (val.charAt(0) == '0' && val.length() != 1) {  //checks for leading zero
			throw new Exception("Leading Zeroes Error!");
		}
		
		hugeInt = new char[val.length()];	//allocates hugeInt with # of characters in val
		for (i=0; i<val.length();i++){
			if ((val.charAt(0) != '-') && (48 > ((int) val.charAt(i)) || ((int) val.charAt(i)) > 57)) {  //checks for invalid characters
				throw new Exception("Invalid Character!");
			}
			hugeInt[i] = val.charAt(i);
		}
	}
	
	public HugeInteger(int n) throws Exception {
		if (0 >= n) {
			throw new Exception("Invalid Input!");
		}
		int i;
		Random rand = new Random();
		hugeInt = new char[n];
		hugeInt[0] = (char) ((rand.nextInt(9) + 1) + '0');
		for (i=1; i<n; i++) {
			hugeInt[i] = (char) (rand.nextInt(9) + '0');
		}
	}
	
	
	//Helper Methods//
	public int length() {
		int length = this.hugeInt.length;
		return length;
	}
	
	public char charAddition(char a, char b) {
		char c = (char) ('0' + ((int)a)%48 + (int)(b%48));
		return c;
	}
	
	
	//Methods//
	public HugeInteger add(HugeInteger h) throws Exception {
		int length = this.length() >= h.length() ? this.length() : h.length(); //creates starting size of sumArray (same size as bigger BigInt)
		int i, place; 
		
		HugeInteger sumArray = new HugeInteger(length);
//		int thisPlace = this.length()-1; //starting index at ones place of this BigInteger
//		int hPlace = h.length()-1; //starting index of h BigInteger
		
		//[1,1,1] 2
		//[1] 0
		for (i=length-1; i>=0; i--) {
			sumArray.hugeInt[i] = charAddition(this.hugeInt[i],h.hugeInt[i]);
//			if (thisPlace == 0 || hPlace == 0) { //reached the end of one BigInt
//				if (thisPlace == 0) {	
//					while (hPlace>-1) {
//						sumArray.hugeInt[i] = h.hugeInt[i];
//						hPlace--;
//					}
//				}
//				if (hPlace == 0) {	
//					while (thisPlace>-1) {
//						sumArray.hugeInt[i] = this.hugeInt[i];
//						thisPlace--;
//					}
//				}
//				break;
//			}
//			thisPlace--;
//			hPlace--;
//			
//			
		}

		return sumArray;
		
	}
	
	public HugeInteger subtract(HugeInteger h) {
		return h;
		
	}
	
	public HugeInteger multiply(HugeInteger h) {
		return h;
		
	}

	public int compareTo(HugeInteger h) {
		return 0;
		
	}
	
	public String toString() {
		int i;
		String output = new String(); 
		for (i=0; i<this.length(); i++) {
			output = output + ((this.hugeInt[i]) + "");
//			System.out.print(this.hugeInt[i]);
		}
		return output;
	
	}
	
}


