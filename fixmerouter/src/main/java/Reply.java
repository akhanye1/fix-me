package fixmerouter;

import fixmecore.*;

public class Reply implements MessageResponse {
	
	private Attachment attach;

	public Reply(Attachment attach) {
		this.attach = attach;
	}

	public void processMessage(String message, ReadWriteHandler readWriteHandler, Attachment staticAttach) {
		System.out.println("Message recieved  :: <" + message + ">");

		//validateCheckSum should return true or false depending if the message is verified, so that we can know if the request was executed or not.

		if (CheckSum.validatecheckSum(message)) {
			// send the message to the market if the message is verified
			Connector.sendStaticMessage(message.toLowerCase(), staticAttach, readWriteHandler);
		}else{
			//return to the broker
			Connector.sendStaticMessage("failed to send message", staticAttach, readWriteHandler);
		}
	}
}
