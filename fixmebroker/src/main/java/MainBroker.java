package fixmebroker;

import fixmecore.*;
import fixmecore.FIXController;

public class MainBroker {

	FIXController controller;
	private Connector connector;

	public MainBroker(FIXModel model) {
		connector = new Connector(5000);
		connector.connect();
		String FIXString = controller.GenerateFixMsgFromModel(model);
		FIXString = CheckSum.checkSum(FIXString);
		connector.sendMessage(FIXString);//WHY????? lol
	}

	public void processMessage(String messageString) {
	}

	public static void main(String[] args) {
		if (args.length != 4) {
			System.out.println("Usage: [java -jar app.jar REQUEST_TYPE MARKET_ID INSTRUMENT QUANTITY]");
		}else {
			FIXModel model = new FIXModel("", args[3], args[4], args[2], "", "", args[1]);
			new MainBroker(model);
		}
	}
}
