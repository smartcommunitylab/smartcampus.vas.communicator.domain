package smartcampus.services.communicator.beans;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class JourneyPlannerParameters {

	private boolean delays;
	private boolean strikes;
	private boolean parkings;
	
	private String source;
	
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

	public boolean isDelays() {
		return delays;
	}

	public void setDelays(boolean delays) {
		this.delays = delays;
	}

	public boolean isStrikes() {
		return strikes;
	}

	public void setStrikes(boolean strikes) {
		this.strikes = strikes;
	}

	public boolean isParkings() {
		return parkings;
	}

	public void setParkings(boolean parkings) {
		this.parkings = parkings;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
	
	
}
