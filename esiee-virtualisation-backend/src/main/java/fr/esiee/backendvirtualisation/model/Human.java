package fr.esiee.backendvirtualisation.model;

public class Human {
	private int mID;
	private String mLastName;
	private String mFirstName;
	private String mEmail;
	
	
	// Constructors
	public Human(int mID, String mLastName, String mFirstName, String mEmail) {
		super();
		this.mID = mID;
		this.mLastName = mLastName;
		this.mFirstName = mFirstName;
		this.mEmail = mEmail;
	}
	
	// Getter & Setter
	public int getID() {
		return mID;
	}
	public void setID(int mID) {
		this.mID = mID;
	}
	public String getLastName() {
		return mLastName;
	}
	public void setLastName(String mLastName) {
		this.mLastName = mLastName;
	}
	public String getFirstName() {
		return mFirstName;
	}
	public void setFirstName(String mFirstName) {
		this.mFirstName = mFirstName;
	}
	public String getEmail() {
		return mEmail;
	}
	public void setEmail(String mEmail) {
		this.mEmail = mEmail;
	}
	
	
}
