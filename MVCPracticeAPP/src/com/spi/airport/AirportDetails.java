package com.spi.airport;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.w3c.dom.CharacterData;
import net.Airport.Airport;
import net.Airport.AirportSoap;

public class AirportDetails {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {	
     AirportDetails.AirportDetailsFetch("Canada");
	}
	
	public static void AirportDetailsFetch(String country) throws ParserConfigurationException, SAXException, IOException{
		Airport airport=new Airport();
		AirportSoap airportSoap=airport.getAirportSoap();
		String str=airportSoap.getAirportInformationByCountry(country);
		System.out.println("***************************");
		System.out.println(str);
		System.out.println("***************************");
		
		/*List<String> li=new ArrayList<String>();
		li.add("AirportCode");
		li.add("CityOrAirportName");
		li.add("Country");
		li.add("CountryAbbrviation");
		li.add("CountryCode");
		li.add("GMTOffset");
		li.add("RunwayLengthFeet");
		li.add("RunwayElevationFeet");
		li.add("LatitudeDegree");
		li.add("LatitudeMinute");
		li.add("LatitudeSecond");
		li.add("LatitudeNpeerS");
		li.add("LongitudeDegree");
		li.add("LongitudeMinute");
		li.add("LongitudeSeconds");
		li.add("LongitudeEperW");
		
		//Convert(str,"Table",li);
		NodeNameFetch(str);*/
		NodeNameFetch(str);
		
		
	}
	

	public static void Convert(String xmlRecords,String ParentTagname,List <String> ChildTagName) throws ParserConfigurationException, SAXException, IOException {
		
		     DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		    InputSource is = new InputSource();
		    is.setCharacterStream(new StringReader(xmlRecords));

		    Document doc = db.parse(is);
		    NodeList nodes = doc.getElementsByTagName(ParentTagname);
		    
		    for (int i = 0; i < nodes.getLength(); i++) {
		       
		    	for (int j = 0; j < ChildTagName.size(); j++) {
		    		 System.out.println(ChildTagName.get(j)+":"+LoopFetch(i, nodes, ChildTagName.get(j)));
				}
		      System.out.println("------------");
		    }
	}
	
	public static String LoopFetch(int i,NodeList nodes,String TagName){
		Element element = (Element) nodes.item(i);
		NodeList name = element.getElementsByTagName(TagName);
	    Element line = (Element) name.item(0);
		try {
			Node child = line.getFirstChild();
			if (child instanceof CharacterData) {
			      CharacterData cd = (CharacterData) child;
			      return cd.getData();
			    }
		} catch (Exception e) {
			System.out.println("Error:"+e);
			return "*****";
		}
		    
		    return "*****";    
	}
	
	public static void NodeNameFetch(String xmlRecords) throws SAXException, IOException, ParserConfigurationException{
		 Document doc = 
			     DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(xmlRecords)));
			    NodeList nl = doc.getElementsByTagName("*");
			    for (int i = 0; i < nl.getLength(); i++)
			    {
			      System.out.println("name is : "+nl.item(i).getNodeName());
			    }
	}
	
}
