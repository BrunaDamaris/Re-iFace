package iface;

public class Requests {
	private String Solicitor;
	private String Receiver;
	
	//Info about the person that sent the friend's request
	public void setSolicitor(String username) {
		this.Solicitor = username;
	}
	public String getSolicitor() {
		return Solicitor;
	}
	
	//info about the receiver
	public void setReceiver(String username) {
		this.Receiver = username;
	}
	public String getReceiver() {
		return Receiver;
	}
}
