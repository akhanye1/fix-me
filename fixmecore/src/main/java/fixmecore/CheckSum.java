package fixmecore;

public class CheckSum {
      private static String separator = "|";

    public static String checkSum(String message) {
		String eg = "SENDER_ID=|ORDER_TYPE=2|ORDER_QUANTITY=3";
        String[] mssg_parts = message.split("\\|");
		String temp;

        String SENDER_ID = mssg_parts[0];
        String MARKET_ID = mssg_parts[1];
        String ORDER_TYPE = mssg_parts[2];
        String ORDER_QUANTITY = mssg_parts[3];
        String ORDER_PRICE = mssg_parts[4];
        String ORDER_STATUS = mssg_parts[5];

		temp = fixmecore.Encrypt.encrypt(ORDER_QUANTITY + separator + ORDER_PRICE + separator + ORDER_STATUS);

        String checksum = SENDER_ID + separator + MARKET_ID + separator + ORDER_TYPE + separator + temp;
        return (checksum);
    }

	public static boolean verifyChecksum(String message) {
		String eg = "SENDER_ID=|ORDER_TYPE=2|ORDER_QUANTITY=3|e7c3e73868f66f8d7794f74b2a22c1af";
		String value = "e7c3e73868f66f8d7794f74b2a22c1af"; 
		temp = fixmecore.Encrypt.encrypt(ORDER_QUANTITY + separator + ORDER_PRICE + separator + ORDER_STATUS);
		String eg1 = "SENDER_ID=|ORDER_TYPE=2|ORDER_QUANTITY=3";
		return value.equals(temp);
	}
}
