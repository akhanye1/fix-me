
package fixmecore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Protocol {
    public static String SENDER_ID;
    public static String MARKET_ID;
    public static String ORDER_TYPE;
    public static String ORDER_QUANTITY;
    public static String ORDER_PRICE;
    public static String ORDER_STATUS;
    public static String CHECKSUM;

    public Protocol(String sENDER_ID, String mARKET_ID,
                                String oRDER_TYPE, String oRDER_QUANTITY,
                                String oRDER_PRICE, String oRDER_STATUS, String cHECKSUM){
        SENDER_ID = sENDER_ID;
        MARKET_ID = mARKET_ID;
        ORDER_TYPE = oRDER_TYPE;
        ORDER_QUANTITY = oRDER_QUANTITY;
        ORDER_PRICE = oRDER_PRICE;
        ORDER_STATUS = oRDER_STATUS;
        CHECKSUM = cHECKSUM;

    }

    public static Protocol newProtocol(String sENDER_ID, String mARKET_ID,
                                String oRDER_TYPE, String oRDER_QUANTITY,
                                String oRDER_PRICE, String oRDER_STATUS,
                                String cHECKSUM){

        return(new Protocol(sENDER_ID, mARKET_ID, oRDER_TYPE, oRDER_QUANTITY, oRDER_PRICE, oRDER_STATUS, cHECKSUM));
    }
}
