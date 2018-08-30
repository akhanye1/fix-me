package fixmebroker;

import fixmecore.*;

public class Reply implements MessageResponse {

	public void processMessage(String messageGiven, ReadWriteHandler readWriteHandler) {
		System.out.println("Message Recieved < " + messageGiven +" >");
	}
}
