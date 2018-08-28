package fixmerouter;

import java.nio.channels.AsynchronousSocketChannel;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;
import java.io.IOException;
import fixmecore.Attachment;

class ConnectionHandler implements
        CompletionHandler<AsynchronousSocketChannel, Attachment> {
	private static int	marketId = 0;
	private static int	brokerId = 0;
	
    @Override
    public void completed(AsynchronousSocketChannel client, Attachment attach) {
        try {
            SocketAddress clientAddr = client.getRemoteAddress();
            System.out.format("Accepted a connection from  %s%n", clientAddr);
			System.out.println("Port :: " + attach.serverAddr.getPort());
            attach.server.accept(attach, this);
			if (attach.serverAddr.getPort() == 5002) {
				attach.id = String.valueOf(brokerId++);
				attach.isBroker = true;
			}
			else if (attach.serverAddr.getPort() == 5001) {
				attach.id = String.valueOf(marketId++);
				attach.isBroker = false;
			}
			else {
				System.out.println("Connection error");
				System.exit(0);
			}
            ReadWriteHandler rwHandler = new ReadWriteHandler();
			//attach.client = client;
			//attach.buffer = ByteBuffer.allocate(2048);
			//attach.clientAddr = clientAddr;
            Attachment newAttach = new Attachment();
            newAttach.server = attach.server;
			newAttach.id = attach.id;
            newAttach.client = client;
            newAttach.buffer = ByteBuffer.allocate(2048);
            newAttach.isRead = false;
            newAttach.clientAddr = clientAddr;
			Clients.addClient(newAttach);
            client.read(newAttach.buffer, attach, rwHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void failed(Throwable e, Attachment attach) {
        System.out.println("Failed to accept a  connection.");
        e.printStackTrace();
    }
}

