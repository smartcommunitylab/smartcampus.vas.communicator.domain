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

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class NewsIds {

	private Map<String, List<String>> ids;
	
	public static NewsIds getInstance() {
		return new NewsIds();
	}
	
	public NewsIds() {
		ids = new TreeMap<String, List<String>>();
	}
	
	public boolean contains(String key) {
		return ids.containsKey(key);
	}
	
	public List<String> getValues(String key) {
		return ids.get(key);
	}
	
	public void setValues(String key, List<String> values) {
		ids.put(key, values);
	}

	public Map<String, List<String>> getIds() {
		return ids;
	}

	public void setIds(Map<String, List<String>> ids) {
		this.ids = ids;
	}	
	
}
