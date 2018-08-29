package fixmecore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class FIXModel {
    public static String SENDER_ID;
    public static String INSTRUMENT;
    public static String ORDER_QUANTITY;
    public static String MARKET_ID;
    public static String ORDER_PRICE;
    public static String ORDER_STATUS;
    public static String REQUEST_TYPE;

    public FIXModel(String sender_id, String instrument, String order_quantity, String market_id,
                    String order_price, String order_status, String request_type){
        SENDER_ID = sender_id;
        INSTRUMENT = instrument;
        ORDER_QUANTITY = order_quantity;
        MARKET_ID = market_id;
        ORDER_PRICE = order_price;
        ORDER_STATUS = order_status;
        REQUEST_TYPE = request_type;
    }
}