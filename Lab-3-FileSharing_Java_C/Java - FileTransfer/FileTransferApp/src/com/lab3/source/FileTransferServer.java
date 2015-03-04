/*
 * Advanced Programming - Lab 3
 * Java file server 
 * 
 * Server class
 * 
 * by @Shumail Mohy-ud-Din
 * 4-march-2015
 */


package com.lab3.source;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class FileTransferServer {

	
	//MAIN unused
	
	  public static void main (String [] args ) throws IOException {
	  }

	  /*
	   * function to send file to the connecting client
	   */
	  
	  public static void server_send_file_to_client(int SOCKET_PORT, String FILE_TO_SEND) throws IOException{
	  	
		  //declaring bufferedInputStream, FileInputStream, outputstream and socket
		  BufferedInputStream buffer_input_stream = null;
		  FileInputStream file_input_stream = null;
		  
		  OutputStream output_stream = null;
		  ServerSocket server_socket = null;
		  
		  Socket sock = null;
		    
	    try {
	    		//new socket
	    		server_socket = new ServerSocket();
	    		server_socket.setReuseAddress(false);
	    		//binding the socket with port
	    		server_socket.bind(new InetSocketAddress(SOCKET_PORT));
		      
		        System.out.println("Waiting for client to connect...");
		       
		          sock = server_socket.accept();
		          System.out.println("Connecton Accepted: " + sock);
		          // sending file
		          File myFile = new File (FILE_TO_SEND);
		          byte [] mybytearray  = new byte [(int)myFile.length()];
		          
		          //input stream to get data from given file
		          file_input_stream = new FileInputStream(myFile);
		          buffer_input_stream = new BufferedInputStream(file_input_stream);
		          buffer_input_stream.read(mybytearray,0,mybytearray.length);
		          
		          //output stream where data is written
		          output_stream = sock.getOutputStream();
		          System.out.println("Sending the file: " + FILE_TO_SEND + "(" + mybytearray.length + " bytes)");
		          output_stream.write(mybytearray,0,mybytearray.length);
		          
		          //flusing stream
		          output_stream.flush();
		          System.out.println("File transfer Completed.");
		       
		          if (buffer_input_stream != null) 
		        	  buffer_input_stream.close();
		          if (output_stream != null) 
		        	  output_stream.close();
		          if (sock!=null) 
		        	  sock.close();
		      
		    } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    finally {
		      if (server_socket != null) 
		    	  server_socket.close();
		    }
		    
		  
	  }
}
