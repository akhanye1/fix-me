package fixmecore;

import fixmecore.types.*;
import java.util.*;

public class DisplayMarketData
{
	public static void Display(List<InstrumentObject> instrument_List)
	{
		System.out.println("\n" + instrument_List + "\n");
		for(InstrumentObject io : instrument_List)
		{
			System.out.println("Id. " + io.getId() + " " + io.getName().toUpperCase() + " Quantity = " + io.getQuantity() + " Price = " + io.getPrice());
		}

	}
};