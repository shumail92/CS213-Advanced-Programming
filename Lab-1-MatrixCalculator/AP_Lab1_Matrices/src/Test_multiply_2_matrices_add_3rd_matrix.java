/**
 * Advanced Programming - Lab # 1 (Matrix Multiplication and unit tests)
 * This class is JUnit Test for "Multiply 2 Matrices and add 3rd Matrix"
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


public class Test_multiply_2_matrices_add_3rd_matrix {

	MatrixHelper helper = new MatrixHelper();
	
	@Test
	public void add3Matrices() {
		
		MatrixCal.read_file();
		ArrayList<Matrix> sample_matrices = MatrixCal.matrices;
		
		System.out.println("-------------------------------------------------");
		
		//ADDING FIRST 2 MATRICES
		System.out.println("Testing Multiplication for two matrices and adding 3rd Matrix...");
		Matrix mat1 = sample_matrices.get(3);
		Matrix mat2 = sample_matrices.get(4);
		Matrix temp = helper.multiplyMatrices(mat1, mat2);
		
		System.out.println("\nNow Adding 3rd Matrix to Result of Multiplication of 1st 2");
		
		//temp is answer of multiplication
		
		//now add mat3 to result of multiplication
		Matrix mat3 = sample_matrices.get(4);
		temp = helper.addMatrices(temp, mat3);
		
		//temp is now final answer of multiplying 2 matrices and adding 3rd one
		
		//Expected Matrix here
		Matrix expectedMatrix = new Matrix(0, 0);
		expectedMatrix.matrixInitialize(mat1.rows, mat2.columns);
		
		/*
		 * expected matrix:
		 * 
		 * 	 41 28 20
			 98 58 46
			148 96 71
		 * 
		 */
		
		//values of expected matrix
		ArrayList<Integer> x = new ArrayList<Integer>();
		
		x.add(41);
		x.add(28);
		x.add(20);
		x.add(98);
		x.add(58);
		x.add(46);
		x.add(148);
		x.add(96);
		x.add(71);
		
		expectedMatrix.addValuesToMatrixFromArrayList(x );
		
			
		//calculate assertion value to test 
		int assertval = assertMatrices(expectedMatrix, temp);
		
		if(assertval == 0) {
			System.out.println("TEST PASSED !!! ");
		} else {
			System.out.println("TEST FAILED");
		}
		
		assertEquals("(Mat1 x Mat2) + Mat3 should be equal to expectedMatrix", 0, assertval);
		
	}
	
	/*
	 * Simple Assert Matrix funciton that takes 2 matrices and match all elements individually
	 */
	
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
