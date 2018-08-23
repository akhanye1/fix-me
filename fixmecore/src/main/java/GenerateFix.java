public class GenerateFix {

    public String GenerateFixMsgFromModel(Protocol model){

        String _message = "SENDER_ID = " + model.SENDER_ID
                +  "MARKET_ID = " + model.MARKET_ID
                + " | ORDER_TYPE = " + model.ORDER_TYPE
                + " | ORDER_QUANTITY = " + model.ORDER_QUANTITY
                + " | ORDER_PRICE = " + model.ORDER_PRICE
                + " | ORDER_STATUS = " + model.ORDER_STATUS
                + " | CHECKSUM = " + model.CHECKSUM;

        return _message;
    }

}
