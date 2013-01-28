package com.softamo.rssToSocial;

import java.util.Collections;
import java.util.List;

public abstract class SocialNetworkUpdater {
	Shortener shortener = new Shortener();

	public void updateSocialNetwork(List<Post> noticias) {
		Collections.reverse(noticias);		
		for(Post noticia : noticias) {
			String url = shortener.shortUrl(noticia.getLink());
			if(url!=null) {
				String title = noticia.getTitle();
				if((title.length() + url.length() + 1) > 140) {
					title = title.substring(0, (140 - 4 - url.length()));
					title += "...";
				}
				String message = title + " " + url;
				publish(message);					
			}
		}
	}
	
	public abstract void publish(String message);
}
