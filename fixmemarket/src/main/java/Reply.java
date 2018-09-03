package fixmemarket;

import fixmecore.*;

public class Reply implements MessageResponse{
    FIXController controller = new FIXController();

    public void processMessage(String messageGiven, ReadWriteHandler readWriteHandler, Attachment attach) {
		if (CheckSum.validatecheckSum(messageGiven)) {
			System.out.println("Checksum okay");
			FIXModel fixModel = controller.readToObject(messageGiven);
			System.out.println("Qty :: " + fixModel.ORDER_QUANTITY);
			fixModel.ORDER_STATUS = "2";
			String fixMessage = controller.GenerateFixMsgFromModel(fixModel);
			fixMessage = CheckSum.generatecheckSum(fixMessage);
			System.out.println("Attempting to respond to client");
			Connector.sendStaticMessage(fixMessage, attach, readWriteHandler);
		}
		else {
			System.out.println("Checksum failed");
		}
    }
}
