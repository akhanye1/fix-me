package fixmebroker;

import fixmecore.*;

public class Reply implements MessageResponse {

    FIXController controller = new FIXController();

	public void processMessage(String messageGiven, ReadWriteHandler readWriteHandler, Attachment attach) {
		FIXModel fixModel =  controller.readToObject(messageGiven);
		if (fixModel.ORDER_STATUS.toLowerCase().equals("executed")){
		    System.out.println("Order was executed");
            System.out.println("Message Recieved < " + messageGiven +" >");

        }else if(fixModel.ORDER_STATUS.toLowerCase().equals("rejected")){
		    System.out.println("Order was rejected");
            System.out.println("Message Recieved < " + messageGiven +" >");
        }

	}
}
