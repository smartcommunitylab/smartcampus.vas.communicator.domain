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

public class JourneyPlannerParameters {

	public static final String DELAYS = "delays";
	public static final String STRIKES = "strikes";
	public static final String PARKINGS = "parkings";
	
//	private boolean delays;
//	private boolean strikes;
//	private boolean parkings;
	
	private List<String> types;
	
	private List<String> sources;
	
	public static JourneyPlannerParameters fromJSON(String json) {
		if (json == null || json.length() == 0) {
			return new JourneyPlannerParameters();
		}
		try {
		ObjectMapper mapper = new ObjectMapper();
		JourneyPlannerParameters pars = mapper.readValue(json, JourneyPlannerParameters.class);
		return pars;
		} catch (Exception e) {
			e.printStackTrace();
			return new JourneyPlannerParameters();
		}
	}
	
	public static String toJSON(JourneyPlannerParameters parameters) {
		try {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(parameters);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}	
	
	public JourneyPlannerParameters() {
		types = new ArrayList<String>();
		sources = new ArrayList<String>();
	}

	public List<String> getTypes() {
		return types;
	}

	public void setTypes(List<String> types) {
		this.types = types;
	}

	public List<String> getSources() {
		return sources;
	}

	public void setSources(List<String> sources) {
		this.sources = sources;
	}

	
	
	
}
