//reference: tutorialspoint

package com.ap.assignment1.test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.ap.assignment1.src.MyJDBCDriver;

import org.junit.Before;
import org.junit.Test;

public class JDBCTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void JDBCtest() {

		Connection dbconn = null; Statement dbstmt = null;
		
		try{
			//registering driver
			Driver driver = new MyJDBCDriver();
			DriverManager.registerDriver(driver);

			//Connecting
			System.out.println("Connecting ...");
			dbconn = DriverManager.getConnection("jdbc:odbc:advpro","root","");
			System.out.println("Connected !!!");
			
			//querying
			System.out.println(":: Create statement ::");
			dbstmt = dbconn.createStatement();

			String query = "SELECT * FROM devices";
			ResultSet res = dbstmt.executeQuery(query);
			
			//Iterating over resultset
			while(res.next()){
				//Retrieve by column name
				int dv_id  = res.getInt("deviceID");
				
				String type = res.getString("Type");
				String price = res.getString("Price");

				//Display values
				System.out.print("Device ID: " + dv_id);
				
				System.out.print(", Type: " + type);
				System.out.println(", Price: " + price);
			}
			res.close();
		
		}catch(Exception e){
			e.printStackTrace();
			fail(e.getMessage());
		}finally{
			try{
				if(dbstmt!=null)
					dbconn.close();
			}catch(SQLException se){
			}// do nothing
			try{
				if(dbconn!=null)
					dbconn.close();
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		
	}

}
