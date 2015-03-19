package com.AP_Assign1.test;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Random;

import org.junit.Test;

import com.AP_Assign1.src.AssemblyLine;

public class AssemblyLineProblemTest {

	
	/* set the value of dataSet variable to 1 for first values set, 2 for 2nd set of values
	 * and 3 for random generation of values */
	
	private int dataSet = 3;
	
		@Test
	public void test() {
		
		/* SET # 1 */
		
		if (dataSet == 1 ) {	
			
			int actual = 20;
			int expected = 0;
			
			int lineEntryTimes[] = {1,3};
			int lineExitTimes[] = {3,3};
			int processingTimes[][]={{5,7,5},{8,4,6}};
			int transferTimes[][]={{1,3},{2,2}};
			int numberOfStations = 3;
			
			printValues(lineEntryTimes, lineExitTimes, processingTimes, transferTimes, numberOfStations);
			
			expected = AssemblyLine.fastestWayAssemblyLine(lineEntryTimes,processingTimes,lineExitTimes, transferTimes,numberOfStations);
			
			if(expected != actual) {
				System.out.println("FAILED: Actual & Expected costs don't match");
				fail("FAILED: Actual & Expected costs don't match");
			}
				
			else {
				System.out.println("Test Passed: Actual & Expected match with each other");
			}	
		}
		
		
		/* Set # 2 */
		
		if(dataSet == 2) {
			
			int lineEntryTimes[] = {2,4};
			int lineExitTimes[] = {3,2};
			int processingTimes[][]={{7,9,3,4,8,4},{8,5,6,4,5,7}};
			int transferTimes[][]={{2,3,1,3,4},{2,1,2,2,1}};
			int numberOfStations = 6;
			int actual = 38;
			int expected = 0;
			
			printValues(lineEntryTimes, lineExitTimes, processingTimes, transferTimes, numberOfStations);
			
			expected = AssemblyLine.fastestWayAssemblyLine(lineEntryTimes,processingTimes,lineExitTimes, transferTimes,numberOfStations);
			
			if(expected != actual) {
				System.out.println("FAILED: Actual & Expected costs don't match");
				fail("FAILED: Actual & Expected costs don't match");
			}
				
			else {
				System.out.println("Test Passed: Actual & Expected match with each other");
			}
		
		}
		
		if (dataSet == 3) {
			int numberOfStations = 6;
			int lineEntryTimes[] = entryExitTimeRandomFilling(2);
			int lineExitTimes[] = entryExitTimeRandomFilling(2);
			
			int processingTimes[][]; 
			processingTimes = processingTimeRandomFilling(numberOfStations); 
			int transferTimes[][]= transferTimeRandomFilling(numberOfStations-1);
			
			int actual = 38;
			int expected = 0;
			
			printValues(lineEntryTimes, lineExitTimes, processingTimes, transferTimes, numberOfStations);
			
			expected = AssemblyLine.fastestWayAssemblyLine(lineEntryTimes,processingTimes,lineExitTimes, transferTimes,numberOfStations);
			
			if(expected != actual) {
				System.out.println("FAILED: Actual & Expected costs don't match");
				//fail("FAILED: Actual & Expected costs don't match");
			}
				
			else {
				System.out.println("Test Passed: Actual & Expected match with each other");
			}
		}
		

	}	
	
	private void printValues(int[] lineEntryTimes, int[] lineExitTimes, int[][] processingTimes, int[][] transferTimes, int numberOfStations) {
		System.out.println("No. of Stations: " + numberOfStations);
		System.out.println("Line Entry Times: " + Arrays.toString(lineEntryTimes));
		System.out.println("Line Exit Times: " + Arrays.toString(lineExitTimes));
		System.out.println("Station Processing Times => Line 1:" + Arrays.toString(processingTimes[0]) + " -- Line 2:" + Arrays.toString(processingTimes[1]));
		System.out.println("Station Transfer Times => Line 1:" + Arrays.toString(transferTimes[0]) + " -- Line 2:" + Arrays.toString(transferTimes[1]));
		
		System.out.println("\n");
	}



	/* Function for Filling lineEntryTimes[] and lineExitTimes[] */
	public int[] entryExitTimeRandomFilling(int no_of_Entries ) {
		int temp[] = new int [no_of_Entries];
		for(int i=0; i< no_of_Entries; i++) {
			temp[i] = randInt();
		}
		return temp;
	}
	
	
	/* Function for Filling processingTimes[] */
	public int[][] processingTimeRandomFilling(int no_of_Entries ) {
		int temp[][] = new int [2][no_of_Entries];
//		System.out.println("No_of_Entries: " + no_of_Entries);
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < no_of_Entries; j++ ) {
//				System.out.println("processingtime index [" + i + "][" + j + "]");
				temp[i][j]= randInt();
			}
		}
		return temp;
	}	
	
	/* Function for Filling transferTimes[] */
	public int[][] transferTimeRandomFilling(int no_of_Entries ) {
		
		int temp[][] = new int [2][no_of_Entries];
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < no_of_Entries; j++ ) {
				temp[i][j]= randInt();
			}
		}
		return temp;
	}
	
	public static int randInt() {
		int min = 3;
		int max = 32;
	    // NOTE: Usually this should be a field rather than a method
	    // variable so that it is not re-seeded every call.
	    Random rand = new Random();

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}
	
	
	

}