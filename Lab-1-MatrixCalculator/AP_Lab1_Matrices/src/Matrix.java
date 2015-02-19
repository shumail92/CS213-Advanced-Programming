/**
 * Advanced Programming - Lab # 1 (Matrix Multiplication and unit tests)
 * This class contains attributes for Matrix and some helping functions.
 *
 * @author Shumail Mohy-ud-Din
 * Reg: 01947 
 * BSCS-2B
 * 
 * 19 Feb, 2014
 */

import java.util.ArrayList;
import java.util.Scanner;

public class Matrix {

	public int rows;
	public int columns;
	public int [][] matrix_values;
	
	
	public Matrix(int rows, int columns) {
		super();
		this.rows = rows;
		this.columns = columns;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getColumns() {
		return columns;
	}

	public void setColumns(int columns) {
		this.columns = columns;
	}

	public void matrixOrderInput() {
		//ORDER INPUT - BUT NOT USED AS IT'S FOR USER INPUT ONLY
		Scanner input = new Scanner(System.in);
		System.out.print("Enter Rows: ");
		int tempRows = input.nextInt();
		
		System.out.print("Enter Columns: ");
		int tempColumns = input.nextInt();
		
		setColumns(tempColumns);
		setRows(tempRows);
		
		
		System.out.println("Matrix order: " + rows + " x " + columns);

	}
	public void matrixInitialize(int r, int c) {
		//initializing the class variables
		rows = r;
		columns = c;
		matrix_values = new int[r][c];
	}
	
	public int matrixInput(){
		//Function for taking matrix values input from USER
		// NOT USED WHEN INPUT IS FROM FILE
		System.out.println("---Enter Matrix Values---");
		Scanner matrixInput = new Scanner(System.in);
		
		matrix_values = new int[rows][columns];
		
		for(int i=0; i< rows; i++){
			for(int j=0; j<columns; j++){
				matrix_values[i][j] = matrixInput.nextInt();
			}
		}
		
		//matrixInput.close();
		return 0;
	}
	
	public void addValuesToMatrixFromArrayList(ArrayList<Integer> x) {
		//adding values for the passed ArrayList
		int j =0;
	    for (int c = 0 ; c < rows ; c++ ){
	    	for (int d = 0 ; d < columns ; d++ ){
	    		matrix_values[c][d] = x.get(j);
	            j++;
	          }
	      }
	}
	
	public void printMatrix() {
		//iterating over 2d array and prints each element
		for(int i=0; i< rows; i++){
			for(int j=0; j<columns; j++){
				System.out.print(String.format("%1$"+3+ "s", matrix_values[i][j]));
			}
			System.out.print("\n");
		}
	}	//end printMatrix
}
