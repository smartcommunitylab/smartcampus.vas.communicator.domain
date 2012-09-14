package smartcampus.services.communicator.beans;

import java.util.ArrayList;
import java.util.List;

public class Notification {

	public String title;
	public String description;
	public long timestamp;
	public String funnelId;
	public boolean starred;		
	public List<String> labels;
	// referenced entities;
	
	public Notification() {
		timestamp = System.currentTimeMillis();
		labels = new ArrayList<String>();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public String getFunnelId() {
		return funnelId;
	}

	public void setFunnelId(String funnelId) {
		this.funnelId = funnelId;
	}

	public boolean isStarred() {
		return starred;
	}

	public void setStarred(boolean starred) {
		this.starred = starred;
	}

	public List<String> getLabels() {
		return labels;
	}

	public void setLabels(List<String> labels) {
		this.labels = labels;
	}
	
	
	
}
