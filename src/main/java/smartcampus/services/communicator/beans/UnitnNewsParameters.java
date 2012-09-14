package smartcampus.services.communicator.beans;

import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;

public class UnitnNewsParameters {

	private String source;
	private List<String> keywords;
	
	public static UnitnNewsParameters fromJSON(String json) {
		try {
		ObjectMapper mapper = new ObjectMapper();
		UnitnNewsParameters pars = mapper.readValue(json, UnitnNewsParameters.class);
		return pars;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public List<String> getKeywords() {
		return keywords;
	}

	public void setKeywords(List<String> keywords) {
		this.keywords = keywords;
	}
	
	
}
