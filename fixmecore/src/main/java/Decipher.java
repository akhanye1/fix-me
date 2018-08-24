
public class Decipher {

    public static void readToObject(String line){
        Protocol protocol = Protocol.newProtocol(line.split("\\|")[0], line.split("\\|")[1],
                line.split("\\|")[2], line.split("\\|")[3], line.split("\\|")[4], line.split("\\|")[5],
                line.split("\\|")[6]);
    }
}
