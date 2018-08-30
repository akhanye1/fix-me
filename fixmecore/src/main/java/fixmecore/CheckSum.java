package fixmecore;

public class CheckSum {
      private static String separator = "|";

    public static String generatecheckSum(String message) {
        String[] mssg_parts = message.split("\\|");
        String check;

        String SENDER_ID = mssg_parts[0];
        String ORDER_TYPE = mssg_parts[1];
        String ORDER_QUANTITY = mssg_parts[2];
        String MARKET_ID = mssg_parts[3];
        String ORDER_PRICE = mssg_parts[4];
        String ORDER_STATUS = mssg_parts[5];
        String CHECKSUM = mssg_parts[6];

        check = fixmecore.Encrypt.encrypt(ORDER_QUANTITY + separator + ORDER_PRICE + separator + ORDER_STATUS);

        String checksum = SENDER_ID + separator + ORDER_TYPE + separator + MARKET_ID + separator + ORDER_QUANTITY + separator + ORDER_PRICE + separator + ORDER_STATUS + separator + CHECKSUM + check;
        return (checksum);
    }
}
