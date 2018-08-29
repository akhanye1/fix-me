package fixmemarket;

import fixmecore.Connector;
import fixmecore.Attachment;

public class MainMarket {

	private Connector connector;

	public MainMarket() {
		connector = new Connector(5001);
		connector.connect();
		connector.sendMessage("Hello");
	}

	public static void main(String[] args) {
		new MainMarket();
	}
}
