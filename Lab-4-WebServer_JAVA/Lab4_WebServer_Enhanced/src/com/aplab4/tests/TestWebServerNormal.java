package com.aplab4.tests;

import static org.junit.Assert.fail;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestWebServerNormal {

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
			url = new URL("http://127.0.0.1:9090");
			HttpURLConnection conn =     (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("HEAD");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			
			
			Map<String, java.util.List<String>> map = conn.getHeaderFields();
			for (Entry<String, java.util.List<String>> entry : map.entrySet()) {
				System.out.println("Header_Field : " + entry.getKey() + 
		                 " ,Header_FieldValue : " + entry.getValue());
			}
			conn.disconnect();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			fail();
			e.printStackTrace();
		}
	}

}
