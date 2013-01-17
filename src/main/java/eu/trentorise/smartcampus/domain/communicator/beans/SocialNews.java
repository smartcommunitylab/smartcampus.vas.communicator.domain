/*******************************************************************************
 * Copyright 2012-2013 Trento RISE
 * 
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 * 
 *        http://www.apache.org/licenses/LICENSE-2.0
 * 
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 ******************************************************************************/
package eu.trentorise.smartcampus.domain.communicator.beans;

import java.io.Serializable;

import eu.trentorise.smartcampus.services.social.data.message.Social.News;

public class SocialNews implements Serializable {

	private static final long serialVersionUID = -5589620882308016936L;
	
	private String topicName;
	private Long topicId;
	private String title;
	private String entityType;
	private Long entityId;
	private Long providerId;
	
	private Long relatedId;
	private String relatedTitle;
	private String relatedEntityType;
	
	private boolean update;
	
	public SocialNews(News news) {
		this.topicName = news.getTopicName();
		this.topicId = news.getTopicId();
		this.title = news.getTitle();
		this.entityType = news.getEntityType();
		this.entityId = news.getEntityId();
		this.providerId = news.getProviderId();
		if (news.hasRelated()) {
			this.relatedId = news.getRelated().getId();
			this.relatedTitle = news.getRelated().getTitle();
			this.relatedEntityType = news.getRelated().getEntityType();
		}
		this.update = news.getUpdate();
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

	public Long getProviderId() {
		return providerId;
	}

	public void setProviderId(Long providerId) {
		this.providerId = providerId;
	}

	public Long getRelatedId() {
		return relatedId;
	}

	public void setRelatedId(Long relatedId) {
		this.relatedId = relatedId;
	}

	public String getRelatedTitle() {
		return relatedTitle;
	}

	public void setRelatedTitle(String relatedTitle) {
		this.relatedTitle = relatedTitle;
	}

	public String getRelatedEntityType() {
		return relatedEntityType;
	}

	public void setRelatedEntityType(String relatedEntityType) {
		this.relatedEntityType = relatedEntityType;
	}

	public boolean isUpdate() {
		return update;
	}

	public void setUpdate(boolean update) {
		this.update = update;
	}
	
}
