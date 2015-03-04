/*
 * Advanced Programming - Lab 3
 * Java file server 
 * 
 * Unit Test for testing client module
 * 
 * by @Shumail Mohy-ud-Din
 * 4-march-2015
 */

package com.aplab3.unittests;
import java.io.IOException;

import org.junit.AfterClass;
import org.junit.Test;

import com.lab3.source.FileTransferClient;

public class ClientUnitTest {
	
	  public final static int port = 55555;      // port where app runs
	  public final static String server = "127.0.0.1";  // ip
	  public final static String filename = "cserver_to_jclient.txt";  // filename 
	  
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void unit_test_for_client() throws IOException {
		FileTransferClient.client_receive_file(port, server, filename);
	}

}
