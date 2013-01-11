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
package smartcampus.services.communicator.beans;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;

public class UnitnNewsParameters {

	private List<String> sources;
	private List<String> keywords;
	
	public static UnitnNewsParameters fromJSON(String json) {
		if (json == null || json.length() == 0) {
			return new UnitnNewsParameters();
		}
		try {
		ObjectMapper mapper = new ObjectMapper();
		UnitnNewsParameters pars = mapper.readValue(json, UnitnNewsParameters.class);
		return pars;
		} catch (Exception e) {
			e.printStackTrace();
			return new UnitnNewsParameters();
		}
	}
	
	public static String toJSON(UnitnNewsParameters parameters) {
		try {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(parameters);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public UnitnNewsParameters() {
		sources = new ArrayList<String>();
		keywords = new ArrayList<String>();
	}	

	public List<String> getSources() {
		return sources;
	}

	public void setSources(List<String> source) {
		this.sources = source;
	}

	public List<String> getKeywords() {
		return keywords;
	}

	public void setKeywords(List<String> keywords) {
		this.keywords = keywords;
	}
	
	
}
