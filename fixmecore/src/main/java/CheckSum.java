public class CheckSum
{
    private static String separator = "|";

    public static String CheckSum(String message)
    {
        String[] mssg_parts = message.split("\\|");

        String marketId = mssg_parts[0];
        String messType = mssg_parts[1];
        String instru = mssg_parts[2];
        String quant = mssg_parts[3];
        String price = mssg_parts[4];

        String checksum = marketId + separator + messType + separator + Encrypt.encrypt(instru + separator + quant + separator + price);
        return (checksum);
    }