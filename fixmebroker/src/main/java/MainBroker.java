package fixmebroker;

import fixmecore.Connector;
import fixmecore.Attachment;
import fixmecore.FIXModel;


public class MainBroker {

	private Connector connector;

	public MainBroker() {
		connector = new Connector(5000);
		connector.connect();
		connector.sendMessage("Hello");
	}

	public static void main(String[] args) {
		if (args.length != 4) {
			System.out.println("Usage: [java -jar app.jar REQUEST_TYPE MARKET_ID INSTRUMENT QUANTITY]");
		}else {
			new MainBroker();
			FIXModel model = new FIXModel("", args[3], args[4], args[2], "", "", args[1]);
		}
	}
}
