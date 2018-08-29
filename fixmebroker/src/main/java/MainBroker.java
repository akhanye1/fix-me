package fixmebroker;

import fixmecore.Connector;
import fixmecore.FIXModel;
import fixmecore.FIXController;
import fixmecore.CheckSum;
import fixmecore.MessageResponse;
//import java.nio.channels.CompletionHandler;

public class MainBroker {

	private FIXController controller;
	private Connector connector;
	private MessageResponse responseHandler;

	public MainBroker(FIXModel model) {
		controller = new FIXController();
		this.responseHandler = new Reply();
		connector = new Connector(5002, this.responseHandler);
		if (connector.connect()) {
			String FIXString = controller.GenerateFixMsgFromModel(model);
			System.out.println("FIX String :: " + FIXString);
			FIXString = CheckSum.checkSum(FIXString);
			connector.sendMessage(FIXString);//WHY????? lol
			try {
				Thread.currentThread().join();
			}
			catch (InterruptedException err) {
				System.out.println("Error joining threads");
			}
		}
		else {
			System.out.println("Unable to connect to router on port :: " + 5000);
		}
		//connector.sendMessage("Hello");
	}

	public static void main(String[] args) {
		if (args.length != 4) {
			System.out.println("Usage: [java -jar app.jar REQUEST_TYPE MARKET_ID INSTRUMENT QUANTITY]");
		}else {
			FIXModel model = new FIXModel("", args[2], args[3], args[1], "", "", args[0]);
			new MainBroker(model);
		}

	}
}
