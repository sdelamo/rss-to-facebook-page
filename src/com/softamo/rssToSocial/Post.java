package com.softamo.rssToSocial;

import java.util.Date;

public class Post {
	public String title;
	public String link;		
	public String description;
	public Date pubDate;

	public Post(String title, String link, String description, Date pubDate) {
		this.title = title;
		this.link = link;		
		this.description = description;
		this.pubDate = pubDate;
	}

	public String getLink() { return this.link;	}
	public void setLink(String link) {this.link = link;	}

	public void setTitle(String title) { this.title = title;}
	public String getTitle() { return this.title;}

	public void setDescription(String description) { this.description = description;}			
	public String getDescription() { return this.description;}

	public void setPubDate(Date pubDate) {this.pubDate = pubDate;}			
	public Date getPubDate() { return this.pubDate;}
}