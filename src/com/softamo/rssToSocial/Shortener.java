package com.softamo.rssToSocial;

import java.io.IOException;

import com.google.api.client.googleapis.GoogleHeaders;
import com.google.api.client.googleapis.GoogleTransport;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.GenericJson;
import com.google.api.client.json.JsonHttpContent;
import com.google.api.client.json.JsonHttpParser;
import com.google.api.client.util.GenericData;
import com.google.api.client.util.Key;

public class Shortener {    
	public static final String GOOGL_URL = "https://www.googleapis.com/urlshortener/v1/url";
	HttpRequest request;
	public Shortener() {
	    // setup up the HTTP transport
        HttpTransport transport = GoogleTransport.create();
        // add default headers
        GoogleHeaders defaultHeaders = new GoogleHeaders();
        transport.defaultHeaders = defaultHeaders;
        transport.defaultHeaders.put("Content-Type", "application/json");
        transport.addParser(new JsonHttpParser());
        request = transport.buildPostRequest();
        request.setUrl(GOOGL_URL);
     }
	
	public String shortUrl(String long_url) {
		GenericData data = new GenericData();
        data.put("longUrl", long_url);
        JsonHttpContent content = new JsonHttpContent();
        content.data = data;
        request.content = content;
        HttpResponse response;
		try {
			response = request.execute();
			Result result = response.parseAs(Result.class);
			return result.shortUrl;
		} catch (IOException e) {
			return null;
		}        
	}
	
	public static class Result extends GenericJson {        
        @Key("id")
        public String shortUrl;
    }    
}