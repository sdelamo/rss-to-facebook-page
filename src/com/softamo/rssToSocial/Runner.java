package com.softamo.rssToSocial;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class Runner {
	static final String CONFIG_PROPERTIES = "config.properties";
	public static void main(String[] args) {
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(new File(CONFIG_PROPERTIES)));
			String feed = prop.getProperty("feed").toString();
			int backward_minutes = new Integer(prop.getProperty("backward_minutes").toString());
			int server_hour_difference = new Integer(prop.getProperty("server_hour_difference").toString());
			FeedGenerator feedGenerator = new FeedGenerator(feed,server_hour_difference);
			List<Post> posts = feedGenerator.getPostsSinceBackwardMinutes(backward_minutes);
			FacebookUpdater facebook_generator = new FacebookUpdater();
			facebook_generator.updateSocialNetwork(posts);			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}