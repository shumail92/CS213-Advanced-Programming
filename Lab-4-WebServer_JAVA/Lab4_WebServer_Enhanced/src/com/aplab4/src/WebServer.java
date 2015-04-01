package com.aplab4.src;
/*
 * Advanced Programming
 * Lab 4 - HTTP web Server in Java
 * 
 * Author: Shumail Mohy-ud-Din
 * Dated: 11 March, 2015
 * 
 * Reference: http://www.prasannatech.net
*/

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLDecoder;
import java.util.StringTokenizer;

public class WebServer extends Thread {
	
	static final String HTML_PRE_APPEND_HEAD = "<html><title>Lab 4 - Web Server</title><body>";
			
    static final String HTML_POST_APPEND_FOOTER = "</body></html>";
	
    //Socket, BufferReader, DataOutputStream
	Socket httpClientConnection = null;	
	BufferedReader inputBufferStream = null;
	DataOutputStream outputStream = null;
	
	//constructor initializes the client
	public WebServer(Socket client) {
		httpClientConnection = client;
	}			
			
	/* 
	 * Reads the Request
	 * Parses the Request, Extracts the Request Type
	 * Responds to the request accordingly (GET, POST)
	 */
	public void run() {
		
	  String currString = null;
	  String requestedFileName = null;
	  String forPost_contentLength = null;
	  PrintWriter fOutStream = null;
		
	  try {
		
		System.out.println( "Client " + httpClientConnection.getInetAddress() + ":" + httpClientConnection.getPort() + " connects");
        
		//initializes the input and output buffer stream
        inputBufferStream = new BufferedReader(new InputStreamReader (httpClientConnection.getInputStream()));                  
        outputStream = new DataOutputStream(httpClientConnection.getOutputStream());
        
        //Reads the Request
		currString = inputBufferStream.readLine();
        String headerLine = currString;            	
        
        //Tokenization of read request for extracting proper request type
        System.out.println("headerline:" + headerLine);
        StringTokenizer tokenizer = new StringTokenizer(headerLine);
		String httpMethod = tokenizer.nextToken();
		String httpQueryString = tokenizer.nextToken();
		
		System.out.println(currString);
				
		//If method is GET
        if (httpMethod.equals("GET") || httpMethod.equalsIgnoreCase("HEAD")) {    
        	System.out.println("-> GET request");    	
			//if trailing with /, means no file
        	if (httpQueryString.equals("/")) {
   				//Response String  
        		String responseString = WebServer.HTML_PRE_APPEND_HEAD + 
   				  "<h1>Welcome to Default Server Homepage. </h1>"
   				  + "<br><h3>This simple web server supports both Get & POST Requests effectively</h3>"
   				  + "<br><p>Current Landing page is example of GET and following form can be used for POST request</p>" + "<form action=\"http://127.0.0.1:9090\" enctype=\"multipart/form-data\"" +
   				  "method=\"post\">" +
   				  "Select the File to Upload: <input name=\"file\" type=\"file\"><br>" +
  				  "<input value=\"Upload\" type=\"submit\"></form>" +
  				  "<i>Only small text file supported for now </i>." +
  				  
  				  WebServer.HTML_POST_APPEND_FOOTER;
				  fHTTPResponseToClient(200, responseString , false);				  			  
				} else {
					//otherwise if a file is specified, find that
					String fileName = httpQueryString.replaceFirst("/", "");
					fileName = URLDecoder.decode(fileName);
					
					//extracted filename
					System.out.println("filename: " + fileName);
					
					// a valid file = send that in response
					if (new File(fileName).isFile()){								
					  fHTTPResponseToClient(200, fileName, true);
					
					}else if(new File(fileName).isDirectory()) {
						  fileName=fileName.replace('\\', '/');
						  fHTTPResponseToClient(301	, "301 Moved", false);
					}else {
						// otherwise, report 404 error	
				      fHTTPResponseToClient(404, "<b> Error: 404<br> The Requested File can't be found" + 
					  "Usage: http://127.0.0.1:9090/" + fileName + "</b>", false);	
					}
				}
		}
		else { //if the request is POST
		    System.out.println("-> POST request"); 
			do {
				//read the request
				currString = inputBufferStream.readLine();
				System.out.println(currString);
				
				//check if it has multipart/form-data to ensure if the request is POST
			    if (currString.indexOf("Content-Type: multipart/form-data") != -1) {
				  String boundary = currString.split("boundary=")[1];
				  // The POST boundary			  			 
				  
				  while (true) {
				  	currString = inputBufferStream.readLine();
				  	if (currString.indexOf("Content-Length:") != -1) {
				  		forPost_contentLength = currString.split(" ")[1];
				  		System.out.println("Content Length = " + forPost_contentLength);
				  		break;
				  	}				  	
				  }
				  
				  //Check for file size < 3 MB
				  if (Long.valueOf(forPost_contentLength) > 3000000L) {
				  	fHTTPResponseToClient(200, "File size should be < 3MB", false);
				  }
				  
				  while (true) {
				  	currString = inputBufferStream.readLine();
				  	if (currString.indexOf("--" + boundary) != -1) {
				  		requestedFileName = inputBufferStream.readLine().split("filename=")[1].replaceAll("\"", ""); 				  			 		
				  		String [] filelist = requestedFileName.split("\\" + System.getProperty("file.separator"));
				  		requestedFileName = filelist[filelist.length - 1];	
				  		//file name extracted
				  		System.out.println("File to be uploaded = " + requestedFileName);
				  		break;
				  	}				  	
				  }
				  
				  String fileType = inputBufferStream.readLine().split(" ")[1];
				  System.out.println("File type = " + fileType);
				  
				  inputBufferStream.readLine();
				  fOutStream = new PrintWriter(requestedFileName);
				  String prevLine = inputBufferStream.readLine();
				  currString = inputBufferStream.readLine();			  
				  
				  //iterate for uploading the file
				  while (true) {
				  	if (currString.equals("--" + boundary + "--")) {
				  		fOutStream.print(prevLine);
				  		break;
				  	}
				  	else {
				  		fOutStream.println(prevLine);
				  	}	
				  	prevLine = currString;			  		
				  	currString = inputBufferStream.readLine();
			      }
			      
			      fHTTPResponseToClient(200, "File " + requestedFileName + "File Uploaded..", false);
			      fOutStream.close();				   
				} //if							  				
			}while (inputBufferStream.ready()); 
			
	  	}//else
	  } catch (Exception e) {
			e.printStackTrace();
	  }	
	}
	
	/* Simple function for sending the file to buffer */
	public void uploadFile (FileInputStream fin, DataOutputStream out) throws Exception {
		byte[] buffer = new byte[1024] ;
		int bytesRead;
	
		while ((bytesRead = fin.read(buffer)) != -1 ) {
		out.write(buffer, 0, bytesRead);
	    }
	    fin.close();
	}
	
	public void fHTTPResponseToClient (int statusCode, String responseString, boolean isFile) throws Exception {
		
		String statusLine = null;
		String serverdetails = "Lab 4 - WebServer";
		String contentLengthLine = null;
		String fileName = null;		
		String contentTypeLine = "Content-Type: text/html" + "\r\n";
		FileInputStream fin = null;
		
		System.out.println("HTTP/1.1 " + statusCode);
		
		if (statusCode == 500) {
			statusLine = "HTTP/1.1 500 Internal Server Error" + "\r\n";
		}
		
		if (statusCode == 200) {
			statusLine = "HTTP/1.1 200 OK" + "\r\n";
		}
		else {
			statusLine = "HTTP/1.1 404 Not Found" + "\r\n";	
		}
		if (isFile) {
			fileName = responseString;			
			fin = new FileInputStream(fileName);
			contentLengthLine = "Content-Length: " + Integer.toString(fin.available()) + "\r\n";
			if (!fileName.endsWith(".htm") && !fileName.endsWith(".html"))
				contentTypeLine = "Content-Type: \r\n";	
		}						
		else {
			responseString = WebServer.HTML_PRE_APPEND_HEAD + responseString + WebServer.HTML_POST_APPEND_FOOTER;
			contentLengthLine = "Content-Length: " + responseString.length() + "\r\n";	
		}			
		 
		outputStream.writeBytes(statusLine);
		outputStream.writeBytes(serverdetails);
		outputStream.writeBytes(contentTypeLine);
		outputStream.writeBytes(contentLengthLine);
		outputStream.writeBytes("Connection: close\r\n");
		outputStream.writeBytes("\r\n");		
		
		if (isFile) uploadFile(fin, outputStream);
		else outputStream.writeBytes(responseString);
		
		outputStream.close();
	}
	
	//start the server, and open any file		
	public static void main (String args[]) throws Exception {
		
		ServerSocket Server = new ServerSocket (9090, 10, InetAddress.getByName("127.0.0.1"));         
		System.out.println ("Server started and Waiting for client on port 9090");
								
		while(true) {	                	   	      	
				Socket connected = Server.accept();
	            (new WebServer(connected)).start();
        }      
	}
}
