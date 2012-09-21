package smartcampus.services.communicator.beans;

import java.io.Serializable;

import eu.trentorise.smartcampus.services.social.data.message.Social.News;

public class SocialNews implements Serializable {

	private String topicName;
	private Long topicId;
	private String title;
	private String entityType;
	private Long entityId;
	
	public SocialNews(News news) {
		this.topicName = news.getTopicName();
		this.topicId = news.getTopicId();
		this.title = news.getTitle();
		this.entityType = news.getEntityType();
		this.entityId = news.getEntityId();
	}

	public String getTopicName() {
		return topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

	public Long getTopicId() {
		return topicId;
	}

	public void setTopicId(Long topicId) {
		this.topicId = topicId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getEntityType() {
		return entityType;
	}

	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}

	public Long getEntityId() {
		return entityId;
	}

	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}
	
}
