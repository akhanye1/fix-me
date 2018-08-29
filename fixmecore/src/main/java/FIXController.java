package fixmecore;

public class FIXController {

    public String GenerateFixMsgFromModel(fixmecore.FIXModel model) {

        String _message = "SENDER_ID=" + model.SENDER_ID
                + "|MARKET_ID=" + model.MARKET_ID
                + "|ORDER_TYPE=" + model.ORDER_TYPE
                + "|ORDER_QUANTITY=" + model.ORDER_QUANTITY
                + "|ORDER_PRICE=" + model.ORDER_PRICE
                + "|ORDER_STATUS=" + model.ORDER_STATUS
                + "|CHECKSUM=" + model.CHECKSUM;

        return _message;
    }

    public fixmecore.FIXModel readToObject(String line) {
        String SenderID = ((line.split("\\|")[0]).split("=")[1]);
        String MarketID = ((line.split("\\|")[1]).split("=")[1]);
        String OrderType = ((line.split("\\|")[2]).split("=")[1]);
        String OrderQuantity = ((line.split("\\|")[3]).split("=")[1]);
        String OrderPrice = ((line.split("\\|")[4]).split("=")[1]);
        String OrderStatus = ((line.split("\\|")[5]).split("=")[1]);
        String CheckSum = ((line.split("\\|")[6]).split("=")[1]);
        return (new fixmecore.FIXModel(SenderID, MarketID, OrderType, OrderQuantity, OrderPrice, OrderStatus, CheckSum));
    }
}