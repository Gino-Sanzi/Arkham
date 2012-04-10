package edu.uconn.arkham.parser;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class Parser {
	
	private DocumentBuilderFactory docBuilderFactory;
	private DocumentBuilder docBuilder;
	private Document doc;
	
	public Parser() {
		
		docBuilderFactory = DocumentBuilderFactory.newInstance();
		try {
			docBuilder = docBuilderFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			
			System.out.println("Could not get new DocumentBuilder in " 
					+ this.getClass().getName());
			
			e.printStackTrace();
		}
		
		this.parse();
	}
	
	private void parse() {
		
		doc = this.parseFile("C:\\Users\\ens07001\\Desktop\\input_raw" +
				"\\15_V2_262_log20120124193341-cellular.xml");
		
		Element docElement = doc.getDocumentElement();
		
	}
	
	private Document parseFile(String loc) {
		
		try {
			return docBuilder.parse(new File(loc));
		} catch (SAXException e) {
			
			System.out.println("SAXException thrown in " 
					+ this.getClass().getName());
			
			e.printStackTrace();
			
		} catch (IOException e) {
			
			System.out.println("IOException thrown in " 
					+ this.getClass().getName());
			
			e.printStackTrace();
		}
		
		return null;
	}
	
	
}
