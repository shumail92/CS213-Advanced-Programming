/**
 * Advanced Programming - Lab # 1 (Matrix Multiplication and unit tests)
 * This helper class contains functions for adding, subtracting and multiplying matrices.
 *
 * @author Shumail Mohy-ud-Din
 * Reg: 01947 
 * BSCS-2B
 * 
 * 19 Feb, 2014
 */


public class MatrixHelper {

	
	/*
	 * Matrix addition function for adding 2 matrices
	 */
	
	public Matrix addMatrices(Matrix a, Matrix b) {
		Matrix temp = new Matrix(0, 0); 
		
		// Check if order of the two matrices is same - only then it can add
		if((a.rows == b.rows) && (a.columns == b.columns)) {
			
			//setting order for temporary matrix
			temp.setRows(a.rows);
			temp.setColumns(a.columns);
			
			//initializing temp matrix according to rows/col
			temp.matrix_values = new int[temp.rows][temp.columns];
			
			//adding the two matrices and saving result in 3rd matrix
			for(int i=0; i< temp.rows; i++){
				for(int j=0; j<temp.columns; j++){
					//adding respective elements
					temp.matrix_values[i][j] = a.matrix_values[i][j] + b.matrix_values[i][j];
				}
			}
			
			temp.printMatrix();
			
		} else {
			System.out.println("Error: Can't add Matrices - Order of matrices isn't same");
		}
		
		return temp;
		
	}
	
	/*
	 * Matrix Subtraction function for subtracting 2 matrices
	 */
	
	public Matrix subtractMatrices(Matrix a, Matrix b) {
		Matrix temp = new Matrix(0, 0); 
		
		// Check if order of the two matrices is same - only then it can add
		if((a.rows == b.rows) && (a.columns == b.columns)) {
			
			//setting order for temporary matrix
			temp.setRows(a.rows);
			temp.setColumns(a.columns);
			
			//initializing temp matrix according to rows/col
			temp.matrix_values = new int[temp.rows][temp.columns];
			
			//adding the two matrices and saving result in 3rd matrix
			for(int i=0; i< temp.rows; i++){
				for(int j=0; j<temp.columns; j++){
					//subtracting respective elements
					temp.matrix_values[i][j] = a.matrix_values[i][j] - b.matrix_values[i][j];
				}
			}
			
			temp.printMatrix();
			
		} else {
			System.out.println("Error: Can't Subtract Matrices - Order of matrices isn't same");
		}
		return temp;	
	}

	
	
	/*
	 * Matrix Multiplication function for multiplying 2 matrices
	 */
	
	public Matrix multiplyMatrices(Matrix a, Matrix b) {
		Matrix temp = new Matrix(0, 0); 
		int sum = 0;
		
		// Check if two matrices can be multiplied
		if(a.columns == b.rows ) {
			
			//initializing temp matrix according to rows/col
			temp.matrixInitialize(a.rows, b.columns);
			
			//loop for multiplying			
			for ( int c = 0 ; c < temp.rows ; c++ )	{	//main outer loop for iterating for no of rows
				
				for ( int d = 0 ; d < temp.columns ; d++ )	{   //inner loop for iterating for no. of columns for each row
				   for ( int k = 0 ; k < b.rows ; k++ )	{	//adding values of multiplied entries into the sum and then save into respective element
					  sum = sum + a.matrix_values[c][k]*b.matrix_values[k][d];
				   }
				   //save sum (multiplication result for that particular element)
				   temp.matrix_values[c][d] = sum;
				   sum = 0;	//reset sum
				}
			 }
			
			temp.printMatrix();
			
		} else {
			//show error if matrices can't be multiplied because the columns of 1st matrix is not equal to rows of 2nd
			System.out.println("Error: Can't Multiply Matrices - Doesn't meet conditions for multiplication");
		}
		return temp;	
	}
	
	

}
