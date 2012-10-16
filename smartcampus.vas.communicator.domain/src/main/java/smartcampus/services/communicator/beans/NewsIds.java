package smartcampus.services.communicator.beans;

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
