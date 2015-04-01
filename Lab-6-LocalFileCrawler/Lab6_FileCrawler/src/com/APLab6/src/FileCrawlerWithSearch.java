/*
 * Local File Crawler
 * Advanced Programming - Lab 6
 * 
 * Main Functionality class
 * 
 * @Author: Shumail Mohy-ud-Din
 * BSCS-2B | 01947
 */


package com.APLab6.src;

import java.io.*;     
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*; 

import com.google.common.collect.Lists;

public class FileCrawlerWithSearch implements Runnable	{

	String searchQuery;
	public static int filesCounter;
	public static List <FileNode> filesMap;
	public List <FileNode> listOfFilesForThread;
	public int searchMethodFlag;
		
	/* Start the local crawler */
	public static void startCrawler(File currentPath) {
		filesCounter = 0;
		filesMap = new ArrayList<FileNode>();
		doCrawling(currentPath, "");
	}
		
	private static void doCrawling(File currentFile, String recursionLevel) {
		
		/* Scan sub-directories for files if the current file is Directory */
		if (currentFile.isDirectory()) {		
			recursionLevel += "x";
			File[] filesInSubDirectory = currentFile.listFiles();
			System.out.println("Indexing Level: " + recursionLevel + " -- Files: " + getFilesList(filesInSubDirectory));
			
			for (int i = 0; i < filesInSubDirectory.length; i++) {
				/* Check each of the sub file recursively and run crawler */
				doCrawling(filesInSubDirectory[i], recursionLevel);
			} // end for
		
		} else {
		/* in this case, file is not directory */
			
			String filePath = currentFile.getAbsolutePath();			
			String fileExtension = getFileExtension(filePath);
			String fileData = "";
			
			if(fileExtension.equalsIgnoreCase("txt"))	{
				try {
					fileData = new String(Files.readAllBytes(Paths.get(filePath)), StandardCharsets.UTF_8);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			/* Create the File Node for this file with all it's details and insert it into the list */
			FileNode tempFileNode = new FileNode(currentFile.getAbsolutePath(), fileExtension, fileData, currentFile.getName(), 1);
			filesMap.add(tempFileNode);
			
			//increment counter
			filesCounter++;
			
		}
	}

	/* helper function for getting list of files in a directory  */
	private static String getFilesList(File[] subFiles) {
		
		String temp = "[";
		
		for (File file : subFiles) {
		    if (file.isFile()) {
		        temp += "(" + file.getName() + "),";
		    }
		}
		temp += "]";
		return temp;
	}

	/* helper function for extracting extension of file */
	private static String getFileExtension(String FilePath) {
		
		// check if it has a DOT (.) / extension at end
		int x = FilePath.lastIndexOf('.');
		String FileExtension = "";
		if (x > 0) {
		    //extract from index very next to dot till end
			FileExtension = FilePath.substring(x+1);
		}
		return FileExtension;
	}

	/* Search for contents of a file as well to check if given string is present in file or not */
	public static void searchForDataInFile(String searchQuery, List <FileNode> currentFilesList)	{
		
		for (ListIterator<FileNode> listIterator = currentFilesList.listIterator(); listIterator.hasNext(); ) {
	
			FileNode currentFileBeingScanned = listIterator.next();
			
			if(currentFileBeingScanned.fileData.toLowerCase().contains(searchQuery.toLowerCase())) {
				System.out.println(currentFileBeingScanned.printFileInfo());
			}
		}
	}
	
	/* Search for file only with it's name given */
	public static void searchForFileNameOnly(String searchQueryText, List <FileNode> currentListOfFiles) {
		
		for (ListIterator<FileNode> listIterator = currentListOfFiles.listIterator(); listIterator.hasNext(); ) {
			
			FileNode currentFileBeingScanned = listIterator.next();
			
			if(currentFileBeingScanned.fileName.toLowerCase().contains(searchQueryText.toLowerCase()))	{
				System.out.println(currentFileBeingScanned.printFileInfo()); 
			}
		}
	}
	
	/* Constructor for thread, sets the files list, scanning type, and query to search */
	public FileCrawlerWithSearch(String searchQuery, List <FileNode> filesList, int type) {
		this.searchQuery = searchQuery;
		this.listOfFilesForThread = filesList;	
		this.searchMethodFlag = type;
    }

	/* Run the thread */
	@Override
	public void run() {
		if(this.searchMethodFlag == 1)
			searchForFileNameOnly(this.searchQuery ,this.listOfFilesForThread);	
		else
			searchForDataInFile(this.searchQuery ,this.listOfFilesForThread);	
	}
	
	/* Split the list from half to run a separate thread on each */
	public static List<List<FileNode>> divideListEqually ()		{		
		return Lists.partition(filesMap, (filesCounter/2)+1);			
	}
	
	
}	