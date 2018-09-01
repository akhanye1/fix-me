package fixmemarket;

import fixmecore.*;

public class Reply implements MessageResponse{
    FIXController controller = new FIXController();

    public void processMessage(String messageGiven, ReadWriteHandler readWriteHandler, Attachment attach) {
		if (CheckSum.validatecheckSum(messageGiven)) {
			System.out.println("Checksum okay");
			FIXModel fixModel = controller.readToObject(messageGiven);
			System.out.println("Qty :: " + fixModel.ORDER_QUANTITY);
		}
		else {
			System.out.println("Checksum failed");
		}
        //System.out.println("Response :: <" + messageGiven + ">");
    }
}
