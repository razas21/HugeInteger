package main;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class HugeIntegeterTestAll {

	@Test
	void testHugeIntegerString() {
			HugeInteger h = null;
			try {
				h = new HugeInteger("2183781723687126454126335478256423674523645871265462533752475273542635487213564782654762351461572653162376526325784521785");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String expected = "2183781723687126454126335478256423674523645871265462533752475273542635487213564782654762351461572653162376526325784521785";
			
			assertEquals(expected, h.toString());
    }
	
	@Test
	void testHugeIntegerStringNegative() {
			HugeInteger h = null;
			try {
				h = new HugeInteger("-422423510293850");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String expected = "-422423510293850";
			
			assertEquals(expected, h.toString());
    }
	
	@Test
	void testHugeIntegerStringLeadingZeroes() {
			HugeInteger h = null;
			String actual ="Empty for Now";
			try {
				h = new HugeInteger("0003123");
			} catch (Exception e) {
				actual = e.getMessage();			
			}
			String expected = "Leading Zeroes Error!";
			
			assertEquals(expected, actual);
    }
	
	@Test
	void testHugeIntegerStringInvalidBeginning() {
			HugeInteger h = null;
			String actual ="Empty for Now";
			try {
				h = new HugeInteger("%$123");
			} catch (Exception e) {
				actual = e.getMessage();			
			}
			String expected = "Invalid Character!";
			
			assertEquals(expected, actual);
    }
	
	@Test
	void testHugeIntegerStringInvalidMiddle() {
			HugeInteger h = null;
			String actual ="Empty for Now";
			try {
				h = new HugeInteger("12131!$@ 2341");
			} catch (Exception e) {
				actual = e.getMessage();			
			}
			String expected = "Invalid Character!";
			
			assertEquals(expected, actual);
    }
	
	@Test
	void testHugeIntegerStringInvalidEnd() {
			HugeInteger h = null;
			String actual ="Empty for Now";
			try {
				h = new HugeInteger("121312341$$$@##");
			} catch (Exception e) {
				actual = e.getMessage();			
			}
			String expected = "Invalid Character!";
			
			assertEquals(expected, actual);
    }
	
//	@Test
//	void testHugeIntegerInt() throws Exception {
//		HugeInteger h = new HugeInteger(15);
////		System.out.print("----Ok-----");
//
//		String expected = "000";
//		
//		assertEquals("123456789123455", h.toString());
//	}

	@Test
	void testLength() throws Exception {
		HugeInteger h = new HugeInteger(15);
		
		assertEquals(15, h.length());
		}

	@Test
	void testAdd() {
		HugeInteger h;
		HugeInteger i;
		try {
			h = new HugeInteger("11");
			i = new HugeInteger("11");
			HugeInteger sum = h.add(i);
			assertEquals("22", sum.toString());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	@Test
	void testAddFirstNumBigger() {
		HugeInteger h;
		HugeInteger i;
		try {
			h = new HugeInteger("31231");
			i = new HugeInteger("100");
			HugeInteger sum = h.add(i);
			assertEquals("31331", sum.toString());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	@Test
	void testAddSecondNumBigger() {
		HugeInteger h;
		HugeInteger i;
		try {
			h = new HugeInteger("100");
			i = new HugeInteger("31231");
			HugeInteger sum = h.add(i);
			assertEquals("31331", sum.toString());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	@Test
	void testAddCarry() {
		HugeInteger h;
		HugeInteger i;
		try {
			h = new HugeInteger("16");
			i = new HugeInteger("16");
			HugeInteger sum = h.add(i);
			assertEquals("32", sum.toString());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	@Test
	void testAddCarryLargerAnswer() {
		HugeInteger h;
		HugeInteger i;
		try {
			h = new HugeInteger("6");
			i = new HugeInteger("6");
			HugeInteger sum = h.add(i);
			assertEquals("12", sum.toString());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	@Test
	void testAddCarryFirstNumBigger() {
		HugeInteger h;
		HugeInteger i;
		try {
			h = new HugeInteger("9813098120980808302480238");
			i = new HugeInteger("98997987");
			HugeInteger sum = h.add(i);
			assertEquals("9813098120980808401478225", sum.toString());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
//	@Test
//	void testSubtract() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testMultiply() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testCompareTo() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testToString() {
//		fail("Not yet implemented");
//	}

}
