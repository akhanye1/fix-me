package fixmecore.instruments;

import fixmecore.types.*;
import java.util.*;

public class InstrumentList
{
	public static List<InstrumentObject> instrument_List = new ArrayList<InstrumentObject>();
	private static InstrumentObject instrument;

	public static List<InstrumentObject> createInstrumentList()
	{
		String[] instrument_names = {"BTC", "LTC", "GNOSIS","RIPPLE", "ETHEREUM"};
		int[] order_prices = {200, 150, 170, 185, 100};
		int[] order_quantity = {80, 15, 80, 20, 60};

		for(int i = 0; i <  5; i++)
		{
			instrument = new InstrumentObject(instrument_names[i], order_prices[i], order_quantity[i]);
			instrument_List.add(instrument);
		}
		return instrument_List;
	}
};
