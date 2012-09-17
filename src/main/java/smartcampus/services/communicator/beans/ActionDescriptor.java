package smartcampus.services.communicator.beans;


public class ActionDescriptor {

	private String label;
	private int type;
	private String value;

	
	public ActionDescriptor() {
		super();
	}


	public ActionDescriptor(String label, int type, String value) {
		super();
		this.label = label;
		this.type = type;
		this.value = value;
	}


	public String getLabel() {
		return label;
	}


	public void setLabel(String label) {
		this.label = label;
	}


	public int getType() {
		return type;
	}


	public void setType(int type) {
		this.type = type;
	}


	public String getValue() {
		return value;
	}


	public void setValue(String value) {
		this.value = value;
	}
	
}

