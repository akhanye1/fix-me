package fixmemarket;

import fixmecore.Connector;
import fixmecore.Attachment;
import fixmecore.DisplayMarketData;
import fixmecore.types.*;
import java.util.*;

public class MainMarket {

	private static List<InstrumentObject> instrument_List;
	private Connector connector;

	public MainMarket() {
		connector = new Connector(5001);
		connector.connect();
		connector.sendMessage("Hello");
		DisplayMarketData.Display(instrument_List);
	}

	public static void main(String[] args) {
		new MainMarket();
	}
}
