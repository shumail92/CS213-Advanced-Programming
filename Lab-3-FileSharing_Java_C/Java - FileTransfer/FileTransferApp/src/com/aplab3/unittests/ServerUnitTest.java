/*
 * Advanced Programming - Lab 3
 * Java file server 
 * 
 * Unit Test for testing server module
 * 
 * by @Shumail Mohy-ud-Din
 * 4-march-2015
 */

package com.aplab3.unittests;
import java.io.File;
import java.io.IOException;

import org.junit.Test;

import com.lab3.source.FileTransferServer;

public class ServerUnitTest {
	
	public final static int port = 55555;  // port
	public final static String filename= "/home/shumail/workspace/FileTransferApp/xaxa.txt";  // you may change this


	@Test
	public void unit_test_for_Server() throws IOException {
		//just checking the file loading path
		System.out.println(new File(".").getAbsolutePath());
		FileTransferServer.server_send_file_to_client(port, filename);
	}

}