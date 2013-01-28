package com.softamo.rssToSocial;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;

public class FacebookUpdater extends SocialNetworkUpdater {
	  // Obtained in https://graph.facebook.com/me/accounts?access_token=  
	  private String accessToken; 
	  private String facebookPageId;
	  private final FacebookClient facebookClient;
	  
	  public FacebookUpdater() {
		
		  Properties prop = new Properties();
		  try {
			  prop.load(new FileInputStream(new File(Runner.CONFIG_PROPERTIES)));
			  accessToken = prop.getProperty("access_token").toString();
			  facebookPageId = prop.getProperty("facebook_page_id").toString();
			  
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		facebookClient = new DefaultFacebookClient(accessToken);
	  }
	  
	  public void publish(String message) {
		  if(facebookClient != null) {
			  facebookClient.publish(facebookPageId+"/feed", FacebookType.class, Parameter.with("message", message));
		  }
	  }
}
