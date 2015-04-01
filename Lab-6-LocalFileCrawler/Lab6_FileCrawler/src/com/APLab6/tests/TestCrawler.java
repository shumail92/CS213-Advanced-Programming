/*
 * Local File Crawler
 * Advanced Programming - Lab 6
 * 
 * Unit Tests
 * 
 * @Author: Shumail Mohy-ud-Din
 * BSCS-2B | 01947
 */

package com.APLab6.tests;

import java.io.File;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.APLab6.src.FileCrawlerWithSearch;
import com.APLab6.src.FileNode;

public class TestCrawler {

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
	public void test() throws InterruptedException {
		
		String folderToScan = "C:\\Users\\Shumail Mohy-ud-Din\\workspace\\Lab6_FileCrawler";
		System.out.print("Target Directory: " + folderToScan);
		
		File f = new File(folderToScan);
		FileCrawlerWithSearch.startCrawler(f);
		List<List<FileNode>> my_list = FileCrawlerWithSearch.divideListEqually();
		
		/* Search File */
		System.out.println("\n>> Searching Files named: \"FileNode\"");
		Thread t1 = new Thread(new FileCrawlerWithSearch("filenode", my_list.get(0),1));
		Thread t2 = new Thread(new FileCrawlerWithSearch("filenode", my_list.get(1),1));		
		t1.start();
		t2.start();
		t1.join();
		t2.join();	
		
		/* Search Content */
		System.out.println("\n>> Searching inside txt files for: \"FileNode\"");
		Thread c1 = new Thread(new FileCrawlerWithSearch("FileNode", my_list.get(0),0));
		Thread c2 = new Thread(new FileCrawlerWithSearch("FileNode", my_list.get(1),0));
		c1.start();
		c2.start();
		c1.join();
		c2.join();
	}
	
}
