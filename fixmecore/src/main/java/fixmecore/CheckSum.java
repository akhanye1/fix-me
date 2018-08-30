package fixmecore;

public class CheckSum {
      private static String separator = "|";

    public static String checkSum(String message) {
        String[] mssg_parts = message.split("\\|");

        String SENDER_ID = mssg_parts[0];
        String MARKET_ID = mssg_parts[1];
        String ORDER_TYPE = mssg_parts[2];
        String ORDER_QUANTITY = mssg_parts[3];
        String ORDER_PRICE = mssg_parts[4];
        String ORDER_STATUS = mssg_parts[5];

        String checksum = SENDER_ID + separator + MARKET_ID + separator + ORDER_TYPE + separator + fixmecore.Encrypt.encrypt(ORDER_QUANTITY + separator + ORDER_PRICE + separator + ORDER_STATUS);
        return (checksum);
    }
}
