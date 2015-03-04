/*
 * Advanced Programming - Lab 3
 * Java file server 
 * 
 * Client Class
 * 
 * by @Shumail Mohy-ud-Din
 * 4-march-2015
 */

package com.lab3.source;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class FileTransferClient {

	  public static void main (String [] args ) throws IOException {
		
	  }
	  
	  public static void client_receive_file(int SOCKET_PORT, String SERVER, String FILE_TO_RECEIVED ) throws IOException{
		  int FILE_SIZE = 6022386;	//for receving the byte stream we set file size big
			int bytesRead;	
		    int current = 0;
		    //initially setting FileOutputStream and BufferedOutputStream to null
		    FileOutputStream fos = null;
		    BufferedOutputStream bos = null;
		    
		    //socket
		    Socket sock = null;
		    
		    //creating and initializing teh socket @ given IP & Port
		    try {
		      sock = new Socket(SERVER, SOCKET_PORT);
		      System.out.println("Trying to Connect..");

		      // receive file - 
		      byte [] recieved_file_byte_array  = new byte [FILE_SIZE];
		      //get input sream
		      InputStream is = sock.getInputStream();
		      
		      //output stream initialized for receiving file
		      fos = new FileOutputStream(FILE_TO_RECEIVED);
		      bos = new BufferedOutputStream(fos);
		      bytesRead = is.read(recieved_file_byte_array,0,recieved_file_byte_array.length);
		      current = bytesRead;

		      do {
		         bytesRead = is.read(recieved_file_byte_array, current, (recieved_file_byte_array.length-current));
		         if(bytesRead >= 0) current += bytesRead;
		      } while(bytesRead > -1);
		      
		      //writing received data to buffer
		      bos.write(recieved_file_byte_array, 0 , current);
		      bos.flush();	//flusing the stream
		      System.out.println("File " + FILE_TO_RECEIVED
		          + " Retreived (" + current + " bytes read)");
		    } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    finally {
		      if (fos != null) 
		    	  fos.close();
		      if (bos != null) 
		    	  bos.close();
		      if (sock != null) 
		    	  sock.close();
		    }
	  }
	
}
