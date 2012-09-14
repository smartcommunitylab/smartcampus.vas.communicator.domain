package smartcampus.services.communicator.beans;

import java.util.Map;

public class ActionDescriptor {

	private String actionId;
	private String actionName;
	private Map<String, Object> actionData;
	
	public ActionDescriptor(String actionId, String actionName, Map<String, Object> actionData) {
		this.actionId = actionId;
		this.actionName = actionName;
		this.actionData = actionData;
	}
	
	public String getActionId() {
		return actionId;
	}
	public void setActionId(String actionId) {
		this.actionId = actionId;
	}
	public String getActionName() {
		return actionName;
	}
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	public Map<String, Object> getActionData() {
		return actionData;
	}
	public void setActionData(Map<String, Object> actionData) {
		this.actionData = actionData;
	}
	
	
	
}

