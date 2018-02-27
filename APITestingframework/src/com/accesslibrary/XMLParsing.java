package com.accesslibrary;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class XMLParsing {

	
	public static void NodeNameFetch(String xmlRecords) throws SAXException, IOException, ParserConfigurationException{
		 Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(xmlRecords)));
			    NodeList nl = doc.getElementsByTagName("*");
			    for (int i = 0; i < nl.getLength(); i++)
			    {
			      System.out.println("name is : "+nl.item(i).getNodeName());
			    }
	}
}
