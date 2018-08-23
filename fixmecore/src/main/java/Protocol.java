import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public abstract class Protocol {
    public String SENDER_ID;
    public String MARKET_ID;
    public String ORDER_TYPE;
    public String ORDER_QUANTITY;
    public String ORDER_PRICE;
    public String ORDER_STATUS;

    public Protocol newProtocol(String SENDER_ID, String MARKET_ID,
                                String ORDER_TYPE, String ORDER_QUANTITY,
                                String ORDER_PRICE, String ORDER_STATUS){
        this.SENDER_ID = SENDER_ID;
        this.MARKET_ID = MARKET_ID;
        this.ORDER_TYPE = ORDER_TYPE;
        this.ORDER_QUANTITY = ORDER_QUANTITY;
        this.ORDER_PRICE = ORDER_PRICE;
        this.ORDER_STATUS = ORDER_STATUS;
    }
}
