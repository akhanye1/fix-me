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

	private String Buy()
	{
		boolean transactionSuccessful = false;
		for(InstrumentObject io : this.instrument_List)
		{
			int totalPrice = io.getPrice() * Integer.parseInt(model.ORDER_QUANTITY);
			if(io.getName().equalsIgnoreCase(model.INSTRUMENT) /*&& totalPrice == this.order_price*/)
			{
				if (Integer.parseInt(model.ORDER_QUANTITY) > io.getQuantity()){
					transactionSuccessful = false;
				}
				else {
					io.setQuantity(io.getQuantity() - Integer.parseInt(model.ORDER_QUANTITY));
					transactionSuccessful = true;
				}
				if(io.getQuantity() <= 0)
					io.setQuantity(0);
					//this.instrument_List.remove(io);
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
		String request_Type = null;
		model = controller.readToObject(res_message);

		if(model.REQUEST_TYPE.equalsIgnoreCase("BUY"))
		{
			request_Type = this.Buy();
		}
		else if(model.REQUEST_TYPE.equalsIgnoreCase("SELL"))
		{
			request_Type = this.Sell();
		}

		DisplayMarketData.Display(this.instrument_List);
		System.out.println(" --------------------" + request_Type);
		this.model.ORDER_STATUS = request_Type;
		return this.model;
	}
}
