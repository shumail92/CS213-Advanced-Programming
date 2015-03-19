package com.AP_Assign1.src;
/* 
 * Advanced Programming
 * Assignment 1
 * 
 * Assembly Line Problem
 * Implementation of Fastest-way Algorithm
 * 
 * author: Shumail Mohy-ud-Din
 * BSCS-2B
 *  
 * */


public class AssemblyLine {
	
	private static int stationWiseCost[];
	
/*
 * Implementation of FastestWay Algorithm for solving Assembly Line Problem
 * lineEntryTimes array contains the entry times for assembly lines
 * lineExitTimes array contains the exit times for assembly lines
 * processingTimesp array contains the times for stations
 * transferTime array contains the times for station transfer time (e.g from Station Si,j on 1st line to j+1 station of 2nd line)
 * numberOfStations is integer => no of stations on each line
*/	
	
	/*fastestWayAssemblyLine => implementation of FastestWay */
	public static int fastestWayAssemblyLine(int lineEntryTimes[], int processingTimesp[][], int lineExitTimes[],  int transferTime[][], int numberOfStations)throws IllegalArgumentException {
		
		stationWiseCost = new int [numberOfStations];
		
		/* check if length(entry time) == length(exit time) */
		if(lineEntryTimes.length != lineExitTimes.length) throw new IllegalArgumentException("Error: length(entry time) != length(exit time)");
		
		/* declaration & initialization */
		int noOfAssemblyLines = lineEntryTimes.length;	//total no of lines
		int fCost[][] = new int[noOfAssemblyLines][numberOfStations];	//for entry times
		int trace[][] = new int[noOfAssemblyLines][numberOfStations];	//trace for path 
		int finalCostOfFastestWay = 0;	//final minimum cost 
		int lastLine  = 0;	//flag for last visited
		
		/* Intitially we need to find the time taken for the very first station */
		for(int i=0; i<noOfAssemblyLines; i++) {
			/* Time will be equal to entry time + time for first station */
			fCost[i][0] = lineEntryTimes[i]+processingTimesp[i][0];
		}
		
		/* Select minimum and for sub-problems, we'll be having the already optimal solution */

		for (int j = 1; j < numberOfStations; j++) {
			
			/* check for minimum - taking into account the previous one.
			 * either it will be on the same line
			 * or it'd be on other line.
			 * in case of other line we'll also consider the switching time
			 */
			
			if((fCost[0][j-1] + processingTimesp[0][j]) <= (fCost[1][j-1]+ transferTime[1][j-1]+processingTimesp[0][j])){
				fCost[0][j]= fCost[0][j-1]+ processingTimesp[0][j];
				trace[0][j] = 0;
			}else{
				fCost[0][j]= fCost[1][j-1]+ transferTime[1][j-1]+processingTimesp[0][j];
				trace[0][j]=1;
			}
			
			if(fCost[1][j-1] + processingTimesp[1][j] <= fCost[0][j-1]+ transferTime[0][j-1]+processingTimesp[1][j]){
				fCost[1][j]= fCost[1][j-1]+ processingTimesp[1][j];
				trace[1][j] = 1;
			}else{
				fCost[1][j]= fCost[0][j-1]+ transferTime[0][j-1]+processingTimesp[1][j];
				trace[1][j] = 0;
			}
			
		}
		
		if(fCost[0][numberOfStations-1]+lineExitTimes[0] <= fCost[1][numberOfStations-1]+lineExitTimes[1] ){
			finalCostOfFastestWay =  fCost[0][numberOfStations-1]+lineExitTimes[0];
			lastLine = 0;
		}else{
			finalCostOfFastestWay = fCost[1][numberOfStations-1]+lineExitTimes[1] ;
			lastLine = 1;
		}
		
		displaySolution(finalCostOfFastestWay, lastLine, trace, numberOfStations);
		return finalCostOfFastestWay;
	}
	
	
	public static void displaySolution(int finalCostOfFastestWay, int lastLine, int trace[][], int n){
		int i = lastLine;
		int temp [] = new int [n+1];
		
		//for the last one
		temp[n] = (i+1);
		//loop for back and save all respective costs in temp
		for (int j = n-1; j > 0; j--) {
			i = trace[i][j];
			temp[j] = (i+1);
		}
		
		System.out.println("--Minimum Cost for problem: "+ finalCostOfFastestWay);
		
		//printing stations and lines for final solution
		for(int f=1; f <= n; f++) {
			System.out.println("Line: " + temp[f] + " -- Station: " + f);
		}
			
	}
}