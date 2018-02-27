package com.spi.basics;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;



public class ReadJsonObject {
	public static void main(String[] args) throws Exception  {
		ReadJsonObject re=new ReadJsonObject();
		re.aptTesting();
	}

public void	aptTesting() throws Exception{

	try {
		//System.setProperty("java.net.useSystemProxies", "true");
		String placename="Texas";
		String address="http://maps.googleapis.com/maps/api/geocode/json?address="+placename+"&sensor=false&#82";
		URL url = new URL(address);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept", "application/json");
        System.out.println("*******:"+conn.getResponseCode());
		if (conn.getResponseCode() != 200) {
			System.out.println("Exception occured");
		throw new RuntimeException("HTTP error code : "+ conn.getResponseCode());
		}

		Scanner scan = new Scanner(url.openStream());
		String entireResponse = new String();
		while (scan.hasNext())
		entireResponse += scan.nextLine();

		System.out.println("Response : "+entireResponse);

		scan.close();

		JSONObject obj = new JSONObject(entireResponse );
		String responseCode = obj.getString("status");
		System.out.println("status : " + responseCode);

		JSONArray arr = obj.getJSONArray("results");
		for (int i = 0; i < arr.length(); i++) {
		String placeid = arr.getJSONObject(i).getString("place_id");
		System.out.println("Place id : " + placeid);
		String formatAddress = arr.getJSONObject(i).getString("formatted_address");
		System.out.println("Address : " + formatAddress);
		
		
		String GemotricalAddress = arr.getJSONObject(i).getString("geometry");
		JSONObject obj11 = new JSONObject(GemotricalAddress );

		//validating Address as per the requirement
		/*if(formatAddress.equalsIgnoreCase("Chicago, IL, USA"))
		{
		System.out.println("Address is as Expected");
		}
		else
		{
		System.out.println("Address is not as Expected");
		}*/
		}

		conn.disconnect();
		} catch (MalformedURLException e) {
		e.printStackTrace();

		} catch (IOException e) {

		e.printStackTrace();

		}
}
}
