package smartcampus.services.communicator.beans;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;

public class SocialParameters {

	private List<Long> topics;
	
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
	
}
