package dtu.scheduler;

public class Worker {
	public String ID;

	public Worker(String ID) {
		// Input validation checking
		if (ID.length() == 0) throw new IllegalArgumentException("The ID must be at least one character");
		if (ID.length() > 4) throw new IllegalArgumentException("The ID can max be 4 characters");

		// Make ID upper case
		ID = ID.toUpperCase();

		this.ID = ID;
	}
	
	public String getID() {
		return ID;
	}
}
