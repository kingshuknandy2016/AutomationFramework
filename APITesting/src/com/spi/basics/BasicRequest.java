package com.spi.basics;

import java.io.IOException;
import java.net.HttpURLConnection;

import java.net.URL;

public class BasicRequest {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		System.setProperty("java.net.useSystemProxies", "true");
		URL url = new URL("http://jdbc.postgresql.org/download/postgresql-9.2-1002.jdbc4.jar");
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        int responseCode = httpConn.getResponseCode();
        System.out.println("Res:"+responseCode);
	}

}
