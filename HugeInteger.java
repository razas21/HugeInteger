package main;

import java.util.Random;

public class HugeInteger {
	
	private int[] hugeInt;
	
	
	//Constructors//
	public HugeInteger(String val) throws Exception {
		int i;

		if (val.charAt(0) == '0' && val.length() != 1) {  //checks for leading zero
			throw new Exception("Leading Zeroes Error!");
		}
		
		hugeInt = new int[val.length()];	//allocates hugeInt with # of characters in val
		for (i=0; i<val.length();i++){
			if ((val.charAt(0) != '-') && (48 > ((int) val.charAt(i)) || ((int) val.charAt(i)) > 57)) {  //checks for invalid characters
				throw new Exception("Invalid Character!");
			}
			else if (val.charAt(i) == '-') {
				hugeInt[0] = (int) val.charAt(1)%48*-1;
				i = 1;
				continue;
			}
			hugeInt[i] = ((int) val.charAt(i))%48;
		}
	}
	
	public HugeInteger(int n) throws Exception {
		if (0 >= n) {
			throw new Exception("Invalid Input!");
		}
		int i;
		Random rand = new Random();
		hugeInt = new int[n];
		hugeInt[0] = ((rand.nextInt(9) + 1));
		for (i=1; i<n; i++) {
			hugeInt[i] = (rand.nextInt(9));
		}
	}
	
	
	//Helper Methods//
	public int length() {
		int length = this.hugeInt.length;
		return length;
	}
	
	
	//Methods//
	public HugeInteger add(HugeInteger h) throws Exception {
		int length = this.length() >= h.length() ? this.length() : h.length(); //creates starting size of sumArray (same size as bigger BigInt)
		int i, place; 
		
		
		HugeInteger sumArray = new HugeInteger(length);
		int thisPlace = this.length()-1; //starting index at ones place of this BigInteger
		int hPlace = h.length()-1; //starting index of h BigInteger
		int sum, carry;
		
		for (i=length-1; i>=0; i--) {
			sum = this.hugeInt[thisPlace] + h.hugeInt[hPlace];
			if (sum>9) { //this is the case where carry happens
				sum %= 10;
				sumArray.hugeInt[i] = sum;
				if(i==0) { //this is the case where carry happens when no more digits remain
					HugeInteger newSumArray = new HugeInteger(length+1);
					for (i=1; i<length+1;i++) {
						newSumArray.hugeInt[i] = sumArray.hugeInt[i-1];
					}
					newSumArray.hugeInt[0] = 1;
					return(newSumArray);
				}
				else {
					if (this.length()>=h.length()) {
						this.hugeInt[i-1] += 1;
					}
					else {
						h.hugeInt[i-1] += 1;
					}
				}
			}
			else {
				sumArray.hugeInt[i] = sum;
			}
			//check to see if a hugeInt has reached its end:
			if (thisPlace==0 || hPlace==0) { 
				if (thisPlace==0) { //case where first hugeInt is smaller
					for(i=hPlace-1;i>=0;i--) {
						sumArray.hugeInt[i] = h.hugeInt[i]; 
					}
					break;
				}
				else if (hPlace==0) { //case where first hugeInt is bigger
					for(i=thisPlace-1;i>=0;i--) {
						sumArray.hugeInt[i] = this.hugeInt[i];
					}
					break;
				}
			}
			hPlace--;
			thisPlace--;
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
			if (this.hugeInt[i]<0) {
				output = output + "-";
				output = output + ((char)((this.hugeInt[i]*-1) + 48));
				System.out.print(this.hugeInt[i]);
				i++;
				System.out.print(this.hugeInt[i]);
				continue;
			}
			output = output + ((char)(this.hugeInt[i]+48) + "");
//			System.out.print(this.hugeInt[i]);
		}
		return output;
	
	}
	
}


