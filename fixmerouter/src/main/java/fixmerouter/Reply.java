package fixmerouter;

import fixmecore.*;

public class Reply implements MessageResponse {

	private Attachment attach;
	private FIXController controller;

	public Reply(Attachment attach) {
		this.attach = attach;
	}

	private void sendToMarket(String message, ReadWriteHandler readWriteHandler, Attachment staticAttach) {
		FIXModel	model;
		String		responseMessage;
		//int			marketId;
		Attachment	marketAttachment;

		controller = new FIXController();
		if ((model = controller.readToObject(message)) != null) {
			//marketId = Integer.parseInt(model.MARKET_ID);
			if ((marketAttachment = Clients.findMarket(model.MARKET_ID)) != null) { 
				System.out.println("market found");
				marketAttachment.mustRead = true;
				Connector.sendStaticMessage(message, marketAttachment, readWriteHandler);
			}
			else {
				System.out.println("market not found");
				Connector.sendStaticMessage(message.toLowerCase(), staticAttach, readWriteHandler);
			}
		}
		else {
			Connector.sendStaticMessage("SENDER_ID=1|ORDER_TYPE=1|ORDER_QUANTITY=1|MARKET_ID=1|ORDER_PRICE=1|ORDER_STATUS=2|REQUEST_TYPE=1|CHECKSUM=aa27d768bedf4790644899b5fa034b11", staticAttach, readWriteHandler);
		}
	}

	private void sendToBroker(String message, ReadWriteHandler readWriteHandler, Attachment staticAttach) {
		FIXModel	model;
		String		responseMessage;
		//int			marketId;
		Attachment	marketAttachment;

		controller = new FIXController();
		if ((model = controller.readToObject(message)) != null) {
			//marketId = Integer.parseInt(model.MARKET_ID);
			if ((marketAttachment = Clients.findBroker(model.SENDER_ID)) != null) { 
				System.out.println("Broker found");
				marketAttachment.mustRead = false;
				Connector.sendStaticMessage(message, marketAttachment, readWriteHandler);
			}
			else {
				System.out.println("Broker not found");
				marketAttachment.mustRead = false;
				Connector.sendStaticMessage(message.toLowerCase(), staticAttach, readWriteHandler);
			}
		}
		else {
			Connector.sendStaticMessage("SENDER_ID=1|ORDER_TYPE=1|ORDER_QUANTITY=1|MARKET_ID=1|ORDER_PRICE=1|ORDER_STATUS=2|REQUEST_TYPE=1|CHECKSUM=aa27d768bedf4790644899b5fa034b11", staticAttach, readWriteHandler);
		}
	}


	public void processMessage(String message, ReadWriteHandler readWriteHandler, Attachment staticAttach) {
		System.out.println("Message recieved  :: <" + message + ">");

		//validateCheckSum should return true or false depending if the message is verified, so that we can know if the request was executed or not.
		if (message.equals("register")) {
			if (staticAttach.isBroker) {
				String sendString = "registerId:" + staticAttach.id;
				staticAttach.mustRead = true;
				staticAttach.isRead = false;
				Connector.sendStaticMessage(sendString, staticAttach, readWriteHandler);
			}
			else {
				String sendString = "registerId:" + staticAttach.id;
				staticAttach.isRead = false;
				Connector.sendStaticMessage(sendString, staticAttach, readWriteHandler);
			}
			return ;
		}

		if (CheckSum.validatecheckSum(message)) {
			// send the message to the market if the message is verified
			System.out.println("Is Broker :: " + staticAttach.isBroker);
			if (staticAttach.isBroker) {
				sendToMarket(message, readWriteHandler, staticAttach);
			}
			else {
				sendToBroker(message, readWriteHandler, staticAttach);
				//Connector.sendStaticMessage(message.toLowerCase(), staticAttach, readWriteHandler);
			}
		}else {
			//return to the broker
			Connector.sendStaticMessage("SENDER_ID=1|ORDER_TYPE=1|ORDER_QUANTITY=1|MARKET_ID=1|ORDER_PRICE=1|ORDER_STATUS=1|REQUEST_TYPE=1|CHECKSUM=aa27d768bedf4790644899b5fa034b11", staticAttach, readWriteHandler);
		}
	}
}
