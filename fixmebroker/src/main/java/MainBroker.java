package fixmebroker;

import fixmecore.*;

public class MainBroker {

	public static fixmecore.FIXController controller;
	private Connector connector;

	public MainBroker() {
		connector = new Connector(5000);
		connector.connect();
		connector.sendMessage("Hello");
	}

	public void processMessage(String messageString) {
	}

	public static void main(String[] args) {
		if (args.length != 4) {
			System.out.println("Usage: [java -jar app.jar REQUEST_TYPE MARKET_ID INSTRUMENT QUANTITY]");
		}else {
			new MainBroker();
			FIXModel model = new FIXModel("", args[3], args[4], args[2], "", "", args[1]);
			String FIXString = controller.GenerateFixMsgFromModel(model);
			FIXString = CheckSum.checkSum(FIXString);
			connector.sendMessage(FIXString);//WHY????? lol
		}

	}
}
