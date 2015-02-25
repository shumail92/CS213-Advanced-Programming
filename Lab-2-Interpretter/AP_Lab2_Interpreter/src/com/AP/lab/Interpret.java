/*
 * Advanced Programming
 * Lab # 2
 * Interpreter
 * 
 * by Shumail Mohy-ud-Din
 * BSCS-2B - 01947
 * 
 */
package com.AP.lab;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Interpret {
	
	public static int ASSERT_FLAG = 0;
	
	/*
	 * Let x = 0
	 * Let y = 1
	 * y = x + 1
	 * print x
	 * 
	 *  Manage errors too
	 *  Generic for Int, Float, String,
	 *  Point out error for undeclared variables too
	 */
	
	public static void main(String[] args) {
		System.out.println(new File(".").getAbsolutePath());
		read_file();
		
	}
	
	
public static void read_file() {
				
		try(BufferedReader br = new BufferedReader(new FileReader("instructions.txt"))) {
			
			if(br != null) {
				ParseHelper helper = new ParseHelper();
				helper.parse(br);
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
