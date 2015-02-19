/**
 * Advanced Programming - Lab # 1 (Matrix Multiplication and unit tests)
 * This class contains the FILE_INPUT Function And some dummy stuff in main()
 * for personal testing without test cases.
 *
 * @author Shumail Mohy-ud-Din
 * Reg: 01947 
 * BSCS-2B
 * 
 * 19 Feb, 2014
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Main class with main() & read_file function
 *
 * @author Shumail Mohy-ud-Din
 */

public class MatrixCal {

	public static ArrayList<Matrix> matrices = new ArrayList<Matrix>();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(new File(".").getAbsolutePath());
		read_file();
		MatrixHelper helper = new MatrixHelper();
		
		//ADDING FIRST 2 MATRICES
		System.out.println("Testing Addition...");
		Matrix tempA = matrices.get(0);
		Matrix tempB = matrices.get(1);
		helper.addMatrices(tempA, tempB);
		
		//Subtracting FIRST 2 MATRICES
		System.out.println("Testing Subtraction...");
		Matrix tempC = matrices.get(2);
		Matrix tempD = matrices.get(1);
		helper.subtractMatrices(tempC, tempD);
		
		//Multiplying FIRST 2 MATRICES
		System.out.println("Testing Multiplication...");
		Matrix tempE = matrices.get(3);
		Matrix tempF = matrices.get(4);
		helper.multiplyMatrices(tempE, tempF);
		
	}

	public static void read_file() {
		
		/*
		 * Funciton to read file and parse it for extractig matrices
		 * and save into the "matrices" Global Arraylist 
		 * 
		 * Format is that each line represents a particular matrix
		 * comma is row separator
		 * space is column separator
		 * 
		 * e.g  1 2 3,4 5 6,7 8 9 represents
		 * 
		 * | 1 2 3 |
		 * | 4 5 6 |
		 * | 7 8 9 |
		 * 
		 */
		
		
		try(BufferedReader br = new BufferedReader(new FileReader("matrices.txt"))) {
			
			String currLine;
			
			//reading matrix from file
			while((currLine = br.readLine()) != null) {
				System.out.println("Read: " + currLine);
				//araylist for matrices elemetns
				ArrayList<Integer> E = new ArrayList<Integer>();
				//split from ,
				String[] r = currLine.split(",");
				//temp matrix 
				Matrix temp = new Matrix(0, 0);
				int colno = 0;
				//splitting from spaces and adding each element into arraylist
				for(int i =0 ; i<r.length;i++){
					String[] element = r[i].split(" ");

					for(int j = 0 ; j<element.length;j++){
						E.add(Integer.parseInt(element[j]));
					}
					colno = element.length;
				}
				
				//initialize matrix for order
				temp.matrixInitialize(r.length, colno);
				//testing printing of arraylist
				System.out.println(E);
				//Filling the matrix with values read from file for that matrix
				temp.addValuesToMatrixFromArrayList(E);
				//adding the matrix to matries arraylist
				matrices.add(temp);
				//testing by priting
				Matrix tempPrintTestMatrix = matrices.get(matrices.size()-1);
				tempPrintTestMatrix.printMatrix();
				
				
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} //end read_file
}
