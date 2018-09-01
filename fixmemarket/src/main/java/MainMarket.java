package fixmemarket;

import fixmecore.Connector;
import fixmecore.Attachment;
import fixmecore.DisplayMarketData;
import fixmecore.types.*;
import fixmecore.instruments.*;
import java.util.*;

public class MainMarket
{

	private static List<InstrumentObject> instrument_List;
	private Connector connector;

	public MainMarket()
    {
		connector = new Connector(5001);
		connector.connect();
		connector.sendMessage("Hello");
		instrument_List = InstrumentList.createInstrumentList();
		DisplayMarketData.Display(instrument_List);
		try {
			Thread.currentThread().join();
		}
		catch (InterruptedException err) {
			System.out.println("Unable to join threads");
		}

	}

	public static void main(String[] args)
    {
		new MainMarket();
	}
}
