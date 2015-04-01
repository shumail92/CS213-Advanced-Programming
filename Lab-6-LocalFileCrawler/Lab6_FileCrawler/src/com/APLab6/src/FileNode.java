/*
 * Local File Crawler
 * Advanced Programming - Lab 6
 * 
 * File Node Class that holds information of each file
 * 
 * @Author: Shumail Mohy-ud-Din
 * BSCS-2B | 01947
 */


package com.APLab6.src;

public class FileNode	{
	
	public String filePath;
	public String fileExtension;
	public int fileTypeFlag;
	public String fileData;
	public String fileName;

	FileNode(String filePath, String fileExtension, String fileData, String fileName,int type)	{
		
		this.filePath = filePath;
		this.fileExtension = fileExtension;
		this.fileData = fileData;
		this.fileName = fileName;
		this.fileTypeFlag = type;
		
	}

	public String printFileInfo() {
		String tmp = "";
		tmp += "--------------------------------------------------------------------------------------\n";
		tmp+= "File: " + this.fileName;
		tmp += "\nPath: " + this.filePath;
		tmp += "\nFile Type: " + this.fileExtension;
		
		if(this.fileData != "")
			tmp+= "\nFile Data: \n" + this.fileData;
		tmp+="\n";
		tmp += "--------------------------------------------------------------------------------------\n";
		return tmp;
	}
}