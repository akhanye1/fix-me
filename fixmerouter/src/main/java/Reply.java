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
		}else {
			//return to the broker
			Connector.sendStaticMessage("SENDER_ID=1|ORDER_TYPE=1|ORDER_QUANTITY=1|MARKET_ID=1|ORDER_PRICE=1|ORDER_STATUS=1|REQUEST_TYPE=1|CHECKSUM=aa27d768bedf4790644899b5fa034b11", staticAttach, readWriteHandler);
		}
	}
}
