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

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;

public class SocialParameters {

	private List<Long> topics;
	private String description;
	
	public static SocialParameters fromJSON(String json) {
		if (json == null || json.length() == 0) {
			return new SocialParameters();
		}
		try {
		ObjectMapper mapper = new ObjectMapper();
		SocialParameters pars = mapper.readValue(json, SocialParameters.class);
		return pars;
		} catch (Exception e) {
			e.printStackTrace();
			return new SocialParameters();
		}
	}
	
	public static String toJSON(SocialParameters parameters) {
		try {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(parameters);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public SocialParameters() {
		topics = new ArrayList<Long>();
	}

	public List<Long> getTopics() {
		return topics;
	}

	public void setTopics(List<Long> topics) {
		this.topics = topics;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}	
}
