package fixmebroker;

import fixmecore.Connector;
import fixmecore.FIXModel;
import fixmecore.FIXController;
import fixmecore.CheckSum;
import fixmecore.MessageResponse;

public class MainBroker {

	private FIXController controller;
	private Connector connector;
	private MessageResponse responseHandler;

	public MainBroker(FIXModel model) {
		this.controller = new FIXController();
		connector = new Connector(5000, new Reply());
		connector.connect();
		String FIXString = controller.GenerateFixMsgFromModel(model);
		FIXString = CheckSum.generatecheckSum(FIXString);
		connector.sendMessage(FIXString);
		try {
			Thread.currentThread().join();
		}
		catch (InterruptedException err) {
			System.out.println("Error :: " + err.getMessage());
		}
	}
	
	public static void main(String[] args) {
		if (args.length != 5) {
			System.out.println("Usage: [java -jar app.jar MARKET_ID REQUEST_TYPE INSTRUMENT QUANTITY ORDER_PRICE]");
		} else {
			FIXModel model = new FIXModel("400000", args[2], args[3], args[0], args[4], "0", args[1]);
			new MainBroker(model);
		}
	}
}
