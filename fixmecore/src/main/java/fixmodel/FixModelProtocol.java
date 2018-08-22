package fixmodel;

public class FixModelProtocol
{
	private static String _message;

	//well according to the FIX protocol each tag=value pair should be seperated by a delimeter
	public static String FixMessage(String delimiter)
	{
		String[] sep_mssgs = delimeter.split("\\|");

		_message = "SENDER_ID = " + sep_mssg[0] +  "MARKET_ID = " + sep_mssg[1] + " | ORDER_TYPE = " + sep_mssg[2] + " | ORDER_QUANTITY = " | sep_mssg[3] + " | ORDER_PRICE = " + sep_mssg[4] + " | ORDER_STATUS = " + sep_mssg[5] + " | CHECKSUM = " + sep_mssg[6];

		return _messgae;
	}
};

