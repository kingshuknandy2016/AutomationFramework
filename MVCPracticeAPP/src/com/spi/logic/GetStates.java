package com.spi.logic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.webservicex.GlobalWeather;
import net.webservicex.GlobalWeatherSoap;

public class GetStates {

	
	public static List<String> getCity(String CountryName){
		
		List<String> li=new ArrayList<String>();
		GlobalWeather globalWeather=new GlobalWeather();
		GlobalWeatherSoap globalWeatherSoap= globalWeather.getGlobalWeatherSoap();
		String text=globalWeatherSoap.getCitiesByCountry(CountryName);
		System.out.println(text);
		String pattern1 = "<City>";
		String pattern2 = "</City>";
			
		Pattern p = Pattern.compile(Pattern.quote(pattern1) + "(.*?)" + Pattern.quote(pattern2));
		Matcher m = p.matcher(text);
		while (m.find()) {
			  li.add(m.group(1));
			}
		 
		return li;	
	}
	
	
	public static void main(String[] args) {
		
		List li=getCity("India");
		Iterator itr=li.iterator();
		while(itr.hasNext())
		{
			System.out.println(itr.next()+"\n");
		}
		//System.out.println(strr);
	}
}
