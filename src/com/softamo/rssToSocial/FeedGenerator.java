package com.softamo.rssToSocial;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.digester.rss.Channel;
import org.apache.commons.digester.rss.Item;
import org.apache.commons.digester.rss.RSSDigester;
import org.xml.sax.SAXException;

public class FeedGenerator {
	private String feed;
	private int severHoursDifference = 0;
	
	public FeedGenerator(String feed,int serverHoursDifference) {
		setFeed(feed);
		setSeverHoursDifference(serverHoursDifference);
	}

	public ArrayList<Post> getPostsSinceBackwardMinutes(int backward_minutes) {
		ArrayList<Post> filtered_posts = new ArrayList<Post>();
		ArrayList<Post> posts = getPosts();
		Calendar backward_date = Calendar.getInstance();	
		backward_date.add(Calendar.MINUTE, -backward_minutes);
		backward_date.add(Calendar.HOUR, getSeverHoursDifference());
		for(Post post : posts) {
			if(backward_date.getTime().getTime() < post.getPubDate().getTime()) {
				filtered_posts.add(post);
			}
		}
		return filtered_posts;
	}
	
	private ArrayList<Post> getPosts() {
		ArrayList<Post> posts = new ArrayList<Post>();
		RSSDigester digester=new RSSDigester();
		URL url;
		try {
			url = new URL(feed);
			HttpURLConnection httpSource = (HttpURLConnection)url.openConnection();
			Channel channel = (Channel)digester.parse(httpSource.getInputStream());

			if (channel==null) {
				return posts;
			}

			Item rssItems[]=channel.findItems();
			for (int i=0;i<rssItems.length;i++) {
				String title = rssItems[i].getTitle();
				String link = rssItems[i].getLink();
				String description = rssItems[i].getDescription();
				String pubDate = rssItems[i].getPubDate();				
				Post post = new Post(title,link,description,new Date(pubDate));				
				posts.add(post);

			}
		} catch(java.lang.NumberFormatException e) {
			System.out.println("NumberFormatException ");
			e.printStackTrace();				
		} catch (MalformedURLException e) {
			System.out.println("MalformedURLException");							
		} catch (IOException e) {
			System.out.println("IOException");				
		} catch (SAXException e) {
			System.out.println("SAXException");				
		}
		return posts;
	}
	
	public String getFeed() {return feed;}
	public void setFeed(String feed) { this.feed = feed;}

	public int getSeverHoursDifference() { return severHoursDifference;}

	public void setSeverHoursDifference(int severHoursDifference) { this.severHoursDifference = severHoursDifference;}
}
