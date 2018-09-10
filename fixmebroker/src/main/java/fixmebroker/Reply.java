package fixmebroker;

import fixmecore.*;

public class Reply implements MessageResponse {

    FIXController controller = new FIXController();

	public void processMessage(String messageGiven, ReadWriteHandler readWriteHandler, Attachment attach) {
		System.out.println("Recieved this message :: " + messageGiven);
		if (messageGiven.startsWith("registerId:")) {
			String idGiven = messageGiven.substring("registerId:".length());
			String fixString;
			System.out.println("Sender id is : " + idGiven);
			FIXModel model = controller.readToObject(attach.tempString);
			model.setSENDER_ID(idGiven);
			fixString = controller.GenerateFixMsgFromModel(model);
			fixString = CheckSum.generatecheckSum(fixString);
			Connector.sendStaticMessage(fixString, attach, readWriteHandler);
			return ;
		}
		FIXModel fixModel =  controller.readToObject(messageGiven);
		//System.out.println("------" + fixModel.ORDER_STATUS);
		if (fixModel.ORDER_STATUS.equals("1")){
		    System.out.println("Order was executed");
            System.out.println("Message Recieved < " + messageGiven +" >");


        }else if(fixModel.ORDER_STATUS.equals("2")){
		    System.out.println("Order was rejected");
            System.out.println("Message Recieved < " + messageGiven +" >");
        }
		else {
			System.out.println("Message not understood by broker");
		}
		System.exit(0);
	}
}
