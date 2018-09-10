package fixmemarket;

import fixmecore.*;
import instruments.InstrumentList;
import types.InstrumentObject;

import java.util.List;

public class Reply implements MessageResponse{
    FIXController controller = new FIXController();
	private List<InstrumentObject> instrument_List;

	public void processMessage(String messageGiven, ReadWriteHandler readWriteHandler, Attachment attach) {
		if (messageGiven.startsWith("registerId:")) {
			System.out.println("Market id : " + messageGiven.substring("registerId:".length()));
			Connector.listenToWrite(attach, readWriteHandler);
			return ;
		}
		if (CheckSum.validatecheckSum(messageGiven))
		{
			System.out.println("Checksum okay");
			FIXModel fixModel = controller.readToObject(messageGiven);
			fixModel.ORDER_STATUS = "2";
			String fixMessage = controller.GenerateFixMsgFromModel(fixModel);
//			System.out.println("--------------------------------------------" + fixMessage + "-------------------------------------------------");
//			String SenderID = ((fixMessage.split("\\|")[0]).split("=")[1]);
//			String Instrument = ((fixMessage.split("\\|")[1]).split("=")[1]);
//			String OrderQuantity = ((fixMessage.split("\\|")[2]).split("=")[1]);
//			String MarketID = ((fixMessage.split("\\|")[3]).split("=")[1]);
//			String OrderPrice = ((fixMessage.split("\\|")[4]).split("=")[1]);
//			String OrderStatus = ((fixMessage.split("\\|")[5]).split("=")[1]);
//			String RequestType = ((fixMessage.split("\\|")[6]).split("=")[1]);
//			System.out.println(SenderID);
//			System.out.println(Instrument);
//			System.out.println(OrderQuantity);
//			System.out.println(MarketID);
//			System.out.println(OrderPrice);
//			System.out.println(OrderStatus);
//			System.out.println(RequestType);
			instrument_List = InstrumentList.createInstrumentList();
			fixmemarket.Transactions transactions = new fixmemarket.Transactions(fixModel, instrument_List);
//============================================================================
			fixModel = transactions.ProcTransactions(fixMessage);
//============================================================================
			fixMessage = controller.GenerateFixMsgFromModel(fixModel);
			fixMessage = CheckSum.generatecheckSum(fixMessage);
			System.out.println("Attempting to respond to client");
			Connector.sendStaticMessage(fixMessage, attach, readWriteHandler);
		}
		else {
			System.out.println("Checksum failed");
		}
    }
}
