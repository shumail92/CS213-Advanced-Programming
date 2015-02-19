/**
 * Advanced Programming - Lab # 1 (Matrix Multiplication and unit tests)
 * This class is JUnit Test for "Multiplying 2 Matrices"
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


public class Test_multiply_two_matrices {

	MatrixHelper helper = new MatrixHelper();
	
	@Test
	public void add3Matrices() {
		
		MatrixCal.read_file();
		ArrayList<Matrix> sample_matrices = MatrixCal.matrices;
		
		//ADDING FIRST 2 MATRICES
		System.out.println("Testing Multiplication for two matrices...");
		Matrix mat1 = sample_matrices.get(3);
		Matrix mat2 = sample_matrices.get(4);
		Matrix temp = helper.multiplyMatrices(mat1, mat2);
		
		//temp is answer of multiplication
		
		//Expected Matrix here
		Matrix expectedMatrix = new Matrix(0, 0);
		expectedMatrix.matrixInitialize(mat1.rows, mat2.columns);
		
		/*
		 * 	 39 24 19
			 90 57 43
			141 90 67
		 * 
		 */
		
		
		//values of expected matrix
		ArrayList<Integer> x = new ArrayList<Integer>();
		
		x.add(39);
		x.add(24);
		x.add(19);
		x.add(90);
		x.add(57);
		x.add(43);
		x.add(141);
		x.add(90);
		x.add(67);
		
		expectedMatrix.addValuesToMatrixFromArrayList(x );
		
			
		//calculate assertion value to test 
		int assertval = assertMatrices(expectedMatrix, temp);
		
		if(assertval == 0) {
			System.out.println("TEST PASSED !!! ");
		} else {
			System.out.println("TEST FAILED");
		}
		
		assertEquals("Mat4 x Mat5 should be equal to expectedMatrix", 0, assertval);
		
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
