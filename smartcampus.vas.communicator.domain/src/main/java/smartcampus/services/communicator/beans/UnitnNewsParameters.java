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
