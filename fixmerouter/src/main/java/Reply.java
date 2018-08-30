package fixmerouter;

import fixmecore.MessageResponse;
import fixmecore.Attachment;

public class Reply implements MessageResponse {
	
	private Attachment attach;

	public Reply() {
	}

	public void processMessage(String message) {
		System.out.println("Response message :: <" + message + ">");
	}
}
