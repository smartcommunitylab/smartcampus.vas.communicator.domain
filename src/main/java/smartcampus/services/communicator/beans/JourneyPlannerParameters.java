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
		try {
		ObjectMapper mapper = new ObjectMapper();
		JourneyPlannerParameters pars = mapper.readValue(json, JourneyPlannerParameters.class);
		return pars;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
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
