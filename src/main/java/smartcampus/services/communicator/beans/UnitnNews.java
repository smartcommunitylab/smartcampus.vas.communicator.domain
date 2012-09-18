package smartcampus.services.communicator.beans;

import java.io.Serializable;

import smartcampus.service.unitnnews.data.message.Unitnnews.NewsEntry;

public class UnitnNews implements Serializable {

	private String title;
	private String content;
	private String source;
	
	public UnitnNews(NewsEntry entry) {
		this.title = entry.getTitle();
		this.content = entry.getContent();
		this.source = entry.getSource();
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	
	
	
}
