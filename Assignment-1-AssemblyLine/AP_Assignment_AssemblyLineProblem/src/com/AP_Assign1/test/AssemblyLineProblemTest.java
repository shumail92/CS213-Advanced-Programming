package com.AP_Assign1.test;
import static org.junit.Assert.*;

import org.junit.Test;

import com.AP_Assign1.src.AssemblyLine;

public class AssemblyLineProblemTest {

		@Test
	public void test() {
		
		/* SET # 1 */
			
		int actual = 20;
		int expected = 0;
		
		int lineEntryTimes[] = {1,3};
		int lineExitTimes[] = {3,3};
		int processingTimes[][]={{5,7,5},{8,4,6}};
		int transferTimes[][]={{1,3},{2,2}};
		int numberOfStations = 3;
		
		/* Set # 2 *
		
		/*int entryTime[] = {2,4};
		int exitTime[] = {3,2};
		int processingTime[][]={{7,9,3,4,8,4},{8,5,6,4,5,7}};
		int transferTime[][]={{2,3,1,3,4},{2,1,2,2,1}};
		int noOfStations = 6;
		int actualMinCost = 38;
		int expMinCost = 0;*/
		
		
		expected = AssemblyLine.fastestWayAssemblyLine(lineEntryTimes,processingTimes,lineExitTimes, transferTimes,numberOfStations);
		
		if(expected != actual) {
			System.out.println("FAILED: Actual & Expected costs don't match");
			fail("FAILED: Actual & Expected costs don't match");
		}
			
		else {
			System.out.println("Test Passed: Actual & Expected match with each other");
		}

	}

}