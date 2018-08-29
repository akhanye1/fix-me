package fixmebroker;

import fixmecore.Connector;
import fixmecore.Attachment;

public class MainBroker {

	private Connector connector;

	public MainBroker() {
		connector = new Connector(5000);
		connector.connect();
		connector.sendMessage("Hello");
	}

	public void processMessage(String messageString) {
	}

	public static void main(String[] args) {
		new MainBroker();
	}
}
