package fixmecore;

import fixmecore.types.*;
import fixmecore.instruments.*;
import java.util.*;

public class Transactions
{
	private String mssg_Type;
	private String instrument_name;
	private int order_quantity;
	private int order_price;
	private List<InstrumentObject> instrument_List;
	private String[] response = {"EXECUTED", "REJECTED"};

	public Transactions(String mssgType, String instrumentName, int orderQuantity, int orderPrice, List<InstrumentObject> instrumentList)
	{
		this.mssg_Type = mssgType;
		this.instrument_name = instrumentName;
		this.order_quantity = orderQuantity;
		this.order_price = orderPrice;
		this.instrument_List = instrumentList;

	}

	private String Buy()
	{
		boolean transactionSuccessful = false;
		for(InstrumentObject io : this.instrument_List)
		{
			int totalPrice = io.getPrice() * this.order_quantity;
			if(io.getName().equalsIgnoreCase(this.instrument_name) && totalPrice == this.order_price)
			{
				io.setQuantity(io.getQuantity() - this.order_quantity);
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
			int total_price = io.getPrice() * this.order_quantity;
			if (io.getName().equalsIgnoreCase(this.instrument_name) && total_price >= this.order_price)
			{
				io.setQuantity(io.getQuantity() + this.order_quantity);
				transactionSuccessful = true;
			}
		}
		if (!transactionSuccessful)
			return response[1];
		return response[0];
	}

	public String ProcTransactions(String res_message)
	{
		res_message = CheckSum.generatecheckSum(res_message);
		String[] mssg_split = res_message.split("\\|");
		String request_Type = null;

		if(this.mssg_Type.equalsIgnoreCase("BUY"))
		{
			request_Type = this.Buy();
		}
		else if(this.mssg_Type.equalsIgnoreCase("SELL"))
		{
			request_Type = this.Sell();
		}

		DisplayMarketData.Display(this.instrument_List);
		request_Type = mssg_split[0] + " | " + request_Type + " | " + mssg_split[2];
		return request_Type;
	}
};

