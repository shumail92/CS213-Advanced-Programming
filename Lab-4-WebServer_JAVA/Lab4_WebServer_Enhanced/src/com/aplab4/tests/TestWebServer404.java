package com.aplab4.tests;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class TestWebServer404 {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		URL url;
		try { 
			url = new URL("http://127.0.0.1:9090/NonExistentFile"); 
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		

		if (conn.getResponseCode() != 404) { 
			throw new IOException(conn.getResponseMessage()); 
		} 
		
		} catch (IOException e) { // TODO Auto-generated catch block e.printStackTrace(); }
		
		}
			
	}

}
