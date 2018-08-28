package fixmecore;

public class Decipher {
    public static void readToObject(String line){
        String SenderID = ((line.split("\\|")[0]).split("=")[1]);
        String MarketID = ((line.split("\\|")[1]).split("=")[1]);
        String OrderType = ((line.split("\\|")[2]).split("=")[1]);
        String OrderQuantity = ((line.split("\\|")[3]).split("=")[1]);
        String OrderPrice = ((line.split("\\|")[4]).split("=")[1]);
        String OrderStatus = ((line.split("\\|")[5]).split("=")[1]);
        String CheckSum = ((line.split("\\|")[6]).split("=")[1]);
        Protocol protocol = Protocol.newProtocol(SenderID, MarketID, OrderType, OrderQuantity, OrderPrice, OrderStatus, CheckSum);
    }
}