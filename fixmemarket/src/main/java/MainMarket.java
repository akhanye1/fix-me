package fixmemarket;

import fixmecore.Connector;
import fixmecore.DisplayMarketData;
import types.*;
import instruments.InstrumentList;
import java.util.*;
public class MainMarket
{

	private List<InstrumentObject> instrument_List;
	private Connector connector;

	public MainMarket()
    {
		connector = new Connector(5001, new fixmemarket.Reply());
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
