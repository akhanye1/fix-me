package fixmemarket;

import fixmecore.DisplayMarketData;
import fixmecore.FIXController;
import fixmecore.FIXModel;
import types.*;
import instruments.*;
import java.util.*;

public class Transactions
{
	private  List<InstrumentObject> instrument_List;
	private String[] response = {"1", "2"};
	FIXModel model;
	FIXController controller = new FIXController();

	public Transactions(FIXModel model, List<InstrumentObject> instrumentList)
	{
		this.model = model;
		this.instrument_List = instrumentList;
	}

//	public static List<InstrumentObject> getInstrument_List() {
//		return instrument_List;
//	}

	private String Buy()
	{
		boolean transactionSuccessful = false;
		for(InstrumentObject io : this.instrument_List)
		{
			System.out.println(io.getQuantity() + "ZACH WHAT THE FUCK..........." + io.getQuantity());
			int totalPrice = io.getPrice() * Integer.parseInt(model.ORDER_QUANTITY);
			if(io.getName().equalsIgnoreCase(model.INSTRUMENT) /*&& totalPrice == this.order_price*/)
			{
				io.setQuantity(io.getQuantity() - Integer.parseInt(model.ORDER_QUANTITY));
				transactionSuccessful = true;
				if(io.getQuantity() <= 0)
					this.instrument_List.remove(io);
			}

		}
		if(!transactionSuccessful)
			return response[1];
		return response[0];
	}

	private String Sell()
	{
		boolean transactionSuccessful = false;
		for (InstrumentObject io : this.instrument_List)
		{
			int total_price = io.getPrice() * Integer.parseInt(model.ORDER_QUANTITY);
			if (io.getName().equalsIgnoreCase(model.INSTRUMENT) && total_price >= Integer.parseInt(model.ORDER_PRICE))
			{
				io.setQuantity(io.getQuantity() + Integer.parseInt(model.ORDER_QUANTITY));
				transactionSuccessful = true;
			}
		}
		if (!transactionSuccessful)
			return response[1];
		return response[0];
	}

	public FIXModel ProcTransactions(String res_message)
	{

//		res_message = CheckSum.generatecheckSum(res_message);
//		String[] mssg_split = res_message.split("\\|");
		String request_Type = null;
		model = controller.readToObject(res_message);
		System.out.println("WHAT'S FOR LUNCH?	" + model.REQUEST_TYPE);
//
		if(model.REQUEST_TYPE.equalsIgnoreCase("BUY"))
		{
			System.out.println("ED is BUYING us LUNCH?	");
			request_Type = this.Buy();
		}
		else if(model.REQUEST_TYPE.equalsIgnoreCase("SELL"))
		{
			System.out.println("Egoli is SELLING US LUNCH.......	");
			request_Type = this.Sell();
		}

		DisplayMarketData.Display(this.instrument_List);
		//request_Type = mssg_split[0] + " | " + request_Type + " | " + mssg_split[2];
		this.model.ORDER_STATUS = request_Type;
		return this.model;
	}
}

