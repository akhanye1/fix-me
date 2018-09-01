package fixmemarket;

import fixmecore.*;

public class Reply implements MessageResponse{
    FIXController controller = new FIXController();

    public void processMessage(String messageGiven, ReadWriteHandler readWriteHandler, Attachment attach) {
        System.out.println("Response :: <" + messageGiven + ">");
    }
}
