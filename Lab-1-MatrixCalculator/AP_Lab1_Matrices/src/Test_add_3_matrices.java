/**
 * Advanced Programming - Lab # 1 (Matrix Multiplication and unit tests)
 * This class is JUnit Test for "Add 3 Matrices"
 *
 * @author Shumail Mohy-ud-Din
 * Reg: 01947 
 * BSCS-2B
 * 
 * 19 Feb, 2014
 */

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;


public class Test_add_3_matrices {

	MatrixHelper helper = new MatrixHelper();
	
	@Test
	public void add3Matrices() {
		
		MatrixCal.read_file();
		ArrayList<Matrix> sample_matrices = MatrixCal.matrices;
		
		System.out.println("-------------------------------------------------");
		
		//ADDING FIRST 2 MATRICES
		System.out.println("Testing Addition for 3 matrices ...");
		Matrix mat1 = sample_matrices.get(0);
		Matrix mat2 = sample_matrices.get(1);
		Matrix temp = helper.addMatrices(mat1, mat2);
		
		System.out.println("\nAdding 3rd Matrix to result of 1st 2");
		
		//Adding 3rd matrix to result of first 2
		Matrix mat3 = sample_matrices.get(2);
		Matrix temp2 = helper.addMatrices(temp, mat3);
		
		//check temp2 now for final result against expected
		
		//Expected Matrix here
		Matrix expectedMatrix = new Matrix(mat1.rows, mat1.columns);
		expectedMatrix.matrixInitialize(mat1.rows, mat1.columns);
		
		/*
		 * expected matrix
		 	
		 	8  9 
		 	8 21
		*
		*/
		
		ArrayList<Integer> x = new ArrayList<Integer>();
		
		x.add(8);
		x.add(9);
		x.add(8);
		x.add(21);
		expectedMatrix.addValuesToMatrixFromArrayList(x );
		
		//calculate assertion value to test agains
		int assertval = assertMatrices(expectedMatrix, temp2);
		
		if(assertval == 0) {
			System.out.println("TEST PASSED !!! ");
		} else {
			System.out.println("TEST FAILED");
		}
		
		assertEquals("Mat1 + Mat2 + Mat3 should be equal to expectedMatrix", 0, assertval);
		
	}
	
	
	public int assertMatrices(Matrix expected, Matrix actual) {
		int flag = 0;
		
		for(int i=0; i< expected.rows; i++) {
			for(int j=0; j<expected.columns; j++) {
				if(expected.matrix_values[i][j] == actual.matrix_values[i][j]) {
					//no issue
				} else {
					flag = -1;
					return flag;
					// issue - matrix not equal
				}
			}
		}
		
		return flag;
	}

}
