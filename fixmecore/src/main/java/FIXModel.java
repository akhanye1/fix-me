package fixmecore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class FIXModel {
    public static String SENDER_ID;
    public static String MARKET_ID;
    public static String ORDER_TYPE;
    public static String ORDER_QUANTITY;
    public static String ORDER_PRICE;
    public static String ORDER_STATUS;
    public static String CHECKSUM;

    public FIXModel(String sender_id, String market_id,
                    String order_type, String order_quantity,
                    String order_price, String order_status, String checksum){
        SENDER_ID = sender_id;
        MARKET_ID = market_id;
        ORDER_TYPE = order_type;
        ORDER_QUANTITY = order_quantity;
        ORDER_PRICE = order_price;
        ORDER_STATUS = order_status;
        CHECKSUM = checksum;

    }
}