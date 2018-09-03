package fixmecore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class FIXModel {
    public String SENDER_ID;
    public String INSTRUMENT;
    public String ORDER_QUANTITY;
    public String MARKET_ID;
    public String ORDER_PRICE;
    public String ORDER_STATUS;
    public String REQUEST_TYPE;

    public FIXModel(String sender_id, String instrument, String order_quantity, String market_id,
                    String order_price, String order_status, String request_type){
        SENDER_ID = sender_id;
        INSTRUMENT = instrument;
        ORDER_QUANTITY = order_quantity;
        MARKET_ID = market_id;
		//MARKET_ID = "500000";
        ORDER_PRICE = order_price;
		ORDER_PRICE = "1";
        ORDER_STATUS = order_status;
		ORDER_STATUS = "1";
        REQUEST_TYPE = request_type;
    }
}
