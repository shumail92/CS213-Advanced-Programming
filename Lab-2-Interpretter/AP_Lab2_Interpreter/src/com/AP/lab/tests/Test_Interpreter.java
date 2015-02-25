package com.AP.lab.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.AP.lab.Interpret;

public class Test_Interpreter {

	@Test
	public void test() {
		
		Interpret.read_file();
		
		System.out.println("\n\n------------------------------------------------------------------------------------------------------------");
		if(Interpret.ASSERT_FLAG == 0) {
			System.out.println("TESTS PASSED");
		} else {
			System.out.println("Test Failed (because of undeclared variables/inomplete expressions/wrong pattern)- Check log for details");
		}
		
		System.out.println("------------------------------------------------------------------------------------------------------------");
		
		assertEquals("Unit Test for Interpretter", 0, Interpret.ASSERT_FLAG);
	}

}
