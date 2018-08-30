package fixmerouter;

import fixmecore.MessageResponse;
import fixmecore.Attachment;
import fixmecore.Connector;
import fixmecore.ReadWriteHandler;

public class Reply implements MessageResponse {
	
	private Attachment attach;

	public Reply(Attachment attach) {
		this.attach = attach;
	}

	public void processMessage(String message, ReadWriteHandler readWriteHandler, Attachment staticAttach) {
		System.out.println("Message recieved  :: <" + message + ">");
		Connector.sendStaticMessage(message.toLowerCase(), staticAttach, readWriteHandler);
	}
}
