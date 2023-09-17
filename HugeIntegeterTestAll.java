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
				h = new HugeInteger("-15");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String expected = "-15";
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
			String expected = "0003123";
			
			assertEquals(expected, h.toString());
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
	
	@Test
	void testHugeIntegerInt() throws Exception {
		HugeInteger h = new HugeInteger(15);
		System.out.print("----Ok-----");

		String expected = "000";
		
		assertEquals("123456789123455", h.toString());
	}

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
			h = new HugeInteger("4");
			i = new HugeInteger("24");
			HugeInteger sum = h.add(i);
			assertEquals("28", sum.toString());

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
	@Test
	void testAddNegatives() {
		HugeInteger h;
		HugeInteger i;
		try {
			h = new HugeInteger("-103");
			i = new HugeInteger("-3");
			HugeInteger sum = h.add(i);
			assertEquals("-106", sum.toString());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	@Test
	void testSubtractZeroDifference() {
		HugeInteger h;
		HugeInteger i;
		try {
			h = new HugeInteger("21638721638191");
			i = new HugeInteger("21638721638191");
			HugeInteger diff = h.subtract(i);
			assertEquals("0", diff.toString());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
		}
	
	@Test
	void testSubtract() {
		HugeInteger h;
		HugeInteger i;
		try {
			h = new HugeInteger("1213");
			i = new HugeInteger("0");
			HugeInteger diff = h.subtract(i);
			assertEquals("1213", diff.toString());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
		}
	@Test
	void testSubtractSecondNumBigger() {
		HugeInteger h;
		HugeInteger i;
		try {
			h = new HugeInteger("10");
			i = new HugeInteger("1212");
			HugeInteger diff = h.subtract(i);
			assertEquals("-1202", diff.toString());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
		}
	
	@Test
	void testSubtractSecondNumberNegative() {
		HugeInteger h;
		HugeInteger i;
		try {
			h = new HugeInteger("14");
			i = new HugeInteger("-2");
			HugeInteger diff = h.subtract(i);
			assertEquals("16", diff.toString());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
		}
	
	@Test
	void testSubtractFirstNubermNegative() {
		HugeInteger h;
		HugeInteger i;
		try {
			h = new HugeInteger("-14");
			i = new HugeInteger("2");
			HugeInteger diff = h.subtract(i);
			assertEquals("-16", diff.toString());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
		}
	
	@Test
	void testSubtractBothNegative() {
		HugeInteger h;
		HugeInteger i;
		try {
			h = new HugeInteger("-2");
			i = new HugeInteger("-14");
			HugeInteger diff = h.subtract(i);
			assertEquals("12", diff.toString());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
		}
//
	@Test
	void testMultiply() {
		HugeInteger h;
		HugeInteger i;
		try {
			h = new HugeInteger("123");
			i = new HugeInteger("456");
			HugeInteger product = h.multiply(i);
			assertEquals("56088", product.toString());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	@Test
	void testMultiplyZeroes() {
		HugeInteger h;
		HugeInteger i;
		try {
			h = new HugeInteger("0");
			i = new HugeInteger("456");
			HugeInteger product = h.multiply(i);
			assertEquals("0", product.toString());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
//
	@Test
	void testCompareTo() {
		HugeInteger h;
		HugeInteger i;
		try {
			h = new HugeInteger("1983129083");
			i = new HugeInteger("1983129083");
//			HugeInteger sum = h.add(i);
			assertEquals(0, h.compareTo(i));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	@Test
	void testCompareToNegative() {
		HugeInteger h;
		HugeInteger i;
		try {
			h = new HugeInteger("-1012312");
			i = new HugeInteger("-10");
//			HugeInteger sum = h.add(i);
			assertEquals(-1, h.compareTo(i));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	@Test
	void testRemoveLeadingzeroes() {
		HugeInteger h;
		try {
			h = new HugeInteger("056");
			assertEquals("56", h.removeLeadingZeroes().toString());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

}
