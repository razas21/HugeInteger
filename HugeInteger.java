package main;

import java.util.Random;

public class HugeInteger {
	
	private int[] hugeInt;
	
	
	//Constructors//
	public HugeInteger(String val) throws Exception { //creates a HugeInteger through a string input
		int i;

//		if (val.charAt(0) == '0' && val.length() != 1) {  //checks for leading zero
//			throw new Exception("Leading Zeroes Error!");
//		}
		if (val.charAt(0) == '-') {  //check to see if HugeInteger is a negative number
			hugeInt = new int[val.length()-1];
			hugeInt[0] = ((int) val.charAt(1))%48*-1;
//			System.out.print(hugeInt[0]);
			for (i=2; i<=val.length()-1; i++) {
//				System.out.print(val.charAt(i));
				hugeInt[i-1] = ((int) val.charAt(i))%48;
			}
		}
		else {
			hugeInt = new int[val.length()];	//allocates HugeInteger with # of characters in val
			for (i=0; i<val.length();i++){
				if ((val.charAt(0) != '-') && (48 > ((int) val.charAt(i)) || ((int) val.charAt(i)) > 57)) {  //checks for invalid characters
					throw new Exception("Invalid Character!");
				}
//				else if (val.charAt(i) == '-') {
//					hugeInt[0] = (int) (val.charAt(1)%48)*-1;
//					i++;
//					continue;
//				}
				hugeInt[i] = ((int) val.charAt(i))%48;
			}
		}
		
	}
	
	public HugeInteger(int n) throws Exception { //creates HugeInteger w/ n number of random digits
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
	public int length() {  //returns length of HugeInteger
		int length = this.hugeInt.length;
		return length;
	}
	
	public HugeInteger copyTo(HugeInteger h) {  //creates a copy of HugeInt
		int i;
		for (i=0; i<this.length();i++) {
			h.hugeInt[i] = this.hugeInt[i];
		}
		return h;
	}
	
	public HugeInteger removeLeadingZeroes() throws Exception {  //removes leading zeroes of HugeInteger
		//eliminating any leading zeroes//
		int i, zeroCount = 0;
		for (i=0; i<this.length();i++) {
			if (this.hugeInt[i] == 0) {
				zeroCount++;
			}
			else {
				break;
			}
		}
		if (zeroCount>0) { //creates HugeInnteger w/o leading zeroes
			HugeInteger newArray = new HugeInteger(this.length()-zeroCount); //initializes new hugeInt with length disregarding leading zeroes of difference
			for (i=zeroCount; i<this.length();i++) { //appropriately assigns new Difference values
				newArray.hugeInt[i-zeroCount] = this.hugeInt[i];
			}
			return newArray;
		}
		else { //no leading zeroes case
			HugeInteger newArray = this;
			return newArray;
		}
	}
	
	//Methods//
	public HugeInteger add(HugeInteger h) throws Exception {
		int length = this.length() >= h.length() ? this.length() : h.length(); //creates starting size of sumArray (same size as bigger BigInt)
		int i, place; 
		
		
		HugeInteger sumArray = new HugeInteger(length);
		int thisPlace = this.length()-1; //starting index at ones place of this BigInteger
		int hPlace = h.length()-1; //starting index of h BigInteger
		int sum, carry;
		
		if (this.hugeInt[0]<0 && h.hugeInt[0]<0) { //case where both ints are negative
			this.hugeInt[0]*= -1;
			h.hugeInt[0]*= -1;
			sumArray = this.add(h);
			sumArray.hugeInt[0] *= -1;
			return sumArray;

		}
		else if (this.hugeInt[0]<0) { //case where first int is negative
			this.hugeInt[0]*= -1;
			sumArray = this.subtract(h);
			sumArray.hugeInt[0] *= -1;
			return sumArray;
		}
		else if (h.hugeInt[0]<0) { //case where second int is negative
			h.hugeInt[0] *= -1;
			return this.subtract(h);
		}
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
	
	public HugeInteger subtract(HugeInteger h) throws Exception {
		
		int i, place; 
		int length = this.length() >= h.length() ? this.length() : h.length(); //creates starting size of sumArray (same size as bigger BigInt)
		
		HugeInteger diffArray = new HugeInteger(length);
		int comparator = this.compareTo(h);
		int thisPlace = this.length()-1; //starting index at ones place of this BigInteger
		int hPlace = h.length()-1; //starting index of h BigInteger
		
		if (comparator == 0) { //case where both ints are equal
			diffArray.hugeInt = new int[1];
			diffArray.hugeInt[0] = 0;
			return diffArray;
		}
		else if (h.hugeInt[0]<0 && this.hugeInt[0]<0) {
			h.hugeInt[0] *= -1;
			this.hugeInt[0] *= -1;
			diffArray = this.subtract(h);
			diffArray.hugeInt[0] *= -1;
			return diffArray;
		}
		else if (h.hugeInt[0]<0) { //case where second number is negative
			h.hugeInt[0] *= -1;
			return this.add(h);
		}
		else if (this.hugeInt[0]<0){ //case where first number is negative
			this.hugeInt[0] *= -1;
			diffArray = this.add(h);
			diffArray.hugeInt[0] *= -1;
			return diffArray;
		}
		else if (comparator == 1) { //case where this>h
			for(i=length-1;i>=0;i--) {
				if (this.hugeInt[thisPlace]>=h.hugeInt[hPlace]) { //checks if current digit of this is greater than h
					diffArray.hugeInt[i] = this.hugeInt[thisPlace] - h.hugeInt[hPlace];
				}
				else { 
					this.hugeInt[thisPlace-1]--;
					this.hugeInt[thisPlace] += 10;
					diffArray.hugeInt[i] = this.hugeInt[thisPlace] - h.hugeInt[hPlace];
				}
				if (thisPlace==0 || hPlace==0) { 
					if (thisPlace==0) { //case where first hugeInt is smaller
						for(i=hPlace-1;i>=0;i--) {
							diffArray.hugeInt[i] = h.hugeInt[i]; 
						}
						break;
					}
					else if (hPlace==0) { //case where first hugeInt is bigger
						for(i=thisPlace-1;i>=0;i--) {
							diffArray.hugeInt[i] = this.hugeInt[i];
						}
						break;
					}
				}
				hPlace--;
				thisPlace--;
			}
		}
		else { //case where h>this
			for(i=length-1;i>=0;i--) {
				if (h.hugeInt[hPlace]>=this.hugeInt[thisPlace]) { //checks if current digit of this is greater than h
					diffArray.hugeInt[i] = h.hugeInt[hPlace] - this.hugeInt[thisPlace];
				}
				else { 
					h.hugeInt[hPlace-1]--;
					h.hugeInt[hPlace] += 10;
					diffArray.hugeInt[i] = h.hugeInt[hPlace] - this.hugeInt[thisPlace];
				}
				if (thisPlace==0 || hPlace==0) { 
					if (hPlace==0) { //case where first hugeInt is smaller
						for(i=thisPlace-1;i>=0;i--) {
							diffArray.hugeInt[i] = this.hugeInt[i]; 
						}
						break;
					}
					else if (thisPlace==0) { //case where first hugeInt is bigger
						for(i=hPlace-1;i>=0;i--) {
							diffArray.hugeInt[i] = h.hugeInt[i];
						}
						break;
					}
				}
				hPlace--;
				thisPlace--;
			}
			diffArray.hugeInt[0] *= -1;
		}
		
		//eliminating any leading zeroes:
		return diffArray.removeLeadingZeroes();

	}
	
	public HugeInteger multiply(HugeInteger h) throws Exception {
		//using Karatsuba Algorithm
		int i;
		int maxLength = this.length() >= h.length() ? this.length() : h.length(); //determines larger int
		if ((this.hugeInt[0]==0 && this.length() == 1 )|| (h.hugeInt[0] == 0 && h.length()==1)) {
			HugeInteger productArray = new HugeInteger(1);
			productArray.hugeInt[0] = 0;
			return productArray;
		}
		if (maxLength==1) { //base case
			int product = this.hugeInt[0]*h.hugeInt[0];
			if (product>9) {
				HugeInteger productArray = new HugeInteger(2);
				productArray.hugeInt[0] = product/10;
				productArray.hugeInt[1] = (product-productArray.hugeInt[0]*10);
				return(productArray);
			}
			else {
				HugeInteger productArray = new HugeInteger(1);
				productArray.hugeInt[0] = product;
				return(productArray);
			}
			
		}
		
		if (this.length()>h.length()) { //case where this>h : adds leading zeroes
			HugeInteger hCopy = new HugeInteger(this.length());
			hCopy = this.copyTo(hCopy);
			
			int additionalZeroes = this.length()-h.length();
			h = new HugeInteger(additionalZeroes+h.length());
			for (i=0;i<h.length();i++) {
				if (i<additionalZeroes) {
					h.hugeInt[i] = 0;
				}
				else {
					h.hugeInt[i] = hCopy.hugeInt[i-additionalZeroes];
				}
			}
		}
		
		HugeInteger This = this;
		if (This.length()<h.length()) { //case where this<h : adds leading zeroes
			HugeInteger thisCopy = new HugeInteger(this.length());
			thisCopy = this.copyTo(thisCopy);
			
			int additionalZeroes = h.length()-This.length(); //1
			This = new HugeInteger(additionalZeroes+This.length()); //int(2)
			for (i=0;i<This.length();i++) { //<2
				if (i<additionalZeroes) { //<1
					This.hugeInt[i] = 0; //[0x]
				}
				else {
					This.hugeInt[i] = thisCopy.hugeInt[i-additionalZeroes]; //[01]
				}
//				System.out.print(This.hugeInt[i]);
			}
			System.out.print("\n");

			for (i=0;i<h.length();i++) {
//				System.out.print(h.hugeInt[i]);
			}
			
//			System.out.print("\n");
		}
		int thisMiddle = This.length()/2;
		int hMiddle = h.length()/2;
		
		
//		//declaring A,B,C,D //
		HugeInteger A = new HugeInteger(thisMiddle);
		HugeInteger B = new HugeInteger(This.length()-thisMiddle);
		HugeInteger C = new HugeInteger(hMiddle);
		HugeInteger D = new HugeInteger(h.length()-hMiddle);
//		
//		//initializing A,B,C,D//
		//A//
		for (i=0; i<thisMiddle; i++) {
//			System.out.print(this.hugeInt[i] + " ");

			A.hugeInt[i] = This.hugeInt[i];	
			System.out.print("A: "+A.hugeInt[i] +",");
		}
//		System.out.print("*");
		//B//
		for (i=0; i<This.length()-thisMiddle; i++) {
			B.hugeInt[i] = This.hugeInt[i+thisMiddle];
			System.out.print(B.hugeInt[i]+",");
		}
//		System.out.print("*");


		//C//
		for (i=0; i<hMiddle; i++) {
			C.hugeInt[i] = h.hugeInt[i];
			System.out.print(C.hugeInt[i]+",");

		}
//		System.out.print("*");

		//D//
		for (i=0; i<h.length()-hMiddle; i++) {
			D.hugeInt[i] = h.hugeInt[i+hMiddle];
			System.out.print(D.hugeInt[i]+",");
		}
//		System.out.print("=");
		
		//declaring and initializing AC //
		HugeInteger AC = new HugeInteger(A.length()*C.length());
		AC = A.multiply(C);
		//'multiplying' by 10^(n)
		HugeInteger newAC = new HugeInteger(AC.length()+maxLength);
		//filling newAC in:
		for (i=0; i<newAC.length();i++) {
			if (i>AC.length()-1) { 
				newAC.hugeInt[i] = 0; //fills in zeroes of maxLength
			}
			else {
				newAC.hugeInt[i] = AC.hugeInt[i];
			}
//			System.out.print(newAC.hugeInt[i]);

		}
//		
		System.out.print("\n");
		

//////		//initializing ADCB (A+B)(C+D)//
		HugeInteger AB = A.add(B);
		HugeInteger CD = C.add(D);
		HugeInteger ADCB = AB.multiply(CD);
		for (i=0;i<ADCB.length();i++) {
		System.out.print(ADCB.hugeInt[i]);
		}
//////		
//////		//'multiplying' by 10^(n/2)
		HugeInteger newADCB = new HugeInteger(ADCB.length()+(maxLength/2));
		for (i=0; i<newADCB.length();i++) {
			if (i>ADCB.length()-1) { 
				newADCB.hugeInt[i] = 0; //fills in zeroes of maxLength
			}
			else {
				newADCB.hugeInt[i] = ADCB.hugeInt[i];
			}
			System.out.print(newADCB.hugeInt[i]);

		}
//////		
//////		//initializing BD//
		HugeInteger BD = new HugeInteger(B.length()*D.length());
		BD = B.multiply(D);
//		for (i=0; i<BD.length();i++) {
//			System.out.print(BD.hugeInt[i]);
//		}

		
		HugeInteger productArray = (newAC.add(newADCB)).add(BD);

//		//eliminating any leading zeroes//
		productArray.removeLeadingZeroes();
		
		for (i=0; i<productArray.length();i++) {
			System.out.print(productArray.hugeInt[i]);
		}
		return productArray;
//		return h;
	}

	public int compareTo(HugeInteger h) { //returns: 1 if this>h: -1 if this<h: 0 if this==0
		int i;
		
		if (this.hugeInt[0]<0||h.hugeInt[0]<0) { //case where number(s) is negative
			if (this.hugeInt[0]<0&&h.hugeInt[0]<0) {
				//both negative
				if(this.length()==h.length()) {
					for(i=0; i<this.length();i++) {
						if (this.hugeInt[i]==h.hugeInt[i]) {
							if(i==this.length()-1) { //case where two hugeInts are equal
								return 0;
							}
							else {
								continue;
							}
						}
						else if (this.hugeInt[i]<h.hugeInt[i]) {
							return 1;
						}
						else {
							return -1;
						}
					}
				}
				else {
					if(this.length()<h.length()) {
						return 1;
					}
					else {
						return -1;
					}
				}
			}
			else {
				if (this.hugeInt[0]<0) {
					return -1;
				}
				else {
					return 1;
				}
			}
		}
		
		else { //comparing two positive hugeInts
			if(this.length()==h.length()) {
				for(i=0; i<this.length();i++) {
					if (this.hugeInt[i]==h.hugeInt[i]) {
						if(i==this.length()-1) { //case where two hugeInts are equal
							return 0;
						}
						else {
							continue;
						}
					}
					else if (this.hugeInt[i]>h.hugeInt[i]) {
						return 1;
					}
					else {
						return -1;
					}
				}
			}
			else {
				if(this.length()>h.length()) {
					return 1;
				}
				else {
					return -1;
				}
			}
			
		}
		return 0;
		
	}
	
	public String toString() {
		int i;
		String output = new String();
		for (i=0; i<this.length();i++) {
//			System.out.print(this.hugeInt[i]);
//			output = output + ((char)(this.hugeInt[i]+48) + "");
		}
		if (this.hugeInt[0]<0) {
			output = output + "-";
			output = output + ((char)((this.hugeInt[0]*-1) + 48));
			for (i=1; i<this.length();i++) {
				output = output + ((char)(this.hugeInt[i]+48) + "");
			}
		}
		else {
			for (i=0; i<this.length(); i++) {
				if (this.hugeInt[i]>9) { //case where a digit>9
					int first = this.hugeInt[i] / 10;
					int second = (this.hugeInt[i]/first) % 10;
					output = output + ((char)(first+48) + "");
					output = output + ((char)(second+48) + "");
					continue;

				}
				output = output + ((char)(this.hugeInt[i]+48) + "");
			}
		}
	
		return output;
	
	}
	
}


