package fixmerouter;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;
import fixmecore.Attachment;

public class MainRouter {

	public static void RegisterServer(int port) {
		try {
			AsynchronousServerSocketChannel server = AsynchronousServerSocketChannel.open();
			InetSocketAddress socketAddress = new InetSocketAddress("localhost", port);
			server.bind(socketAddress);
			Attachment attach = new Attachment();
			attach.server = server;
			attach.serverAddr = socketAddress;
			server.accept(attach, new ConnectionHandler());
			System.out.println("Server Listening at port :: " + port);
		}
		catch (Exception err) {
			System.out.println("Error listening to port " + port + " :: " + err.getMessage());
		}
	}
	
    public static void main(String[] args) {
		RegisterServer(5000);
		RegisterServer(5001);
		try {
			Thread.currentThread().join();
		}
		catch (InterruptedException err) {
			System.out.println("Error joining threads :: " + err.getMessage());
		}
	}
}

class ReadWriteHandler implements CompletionHandler<Integer, Attachment> {
    @Override
    public void completed(Integer result, Attachment attach) {
        if (result == -1) {
            try {
                attach.client.close();
                System.out.format("Stopped   listening to the   client %s%n",
                        attach.clientAddr);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            return;
        }

        if (attach.isRead) {
            attach.buffer.flip();
            int limits = attach.buffer.limit();
            byte bytes[] = new byte[limits];
            attach.buffer.get(bytes, 0, limits);
            Charset cs = Charset.forName("UTF-8");
            String msg = new String(bytes, cs);
            System.out.format("Client at  %s  says: %s%n", attach.clientAddr,
                    msg);
            attach.isRead = false; // It is a write
            //Make data readable for the client, for resend
            attach.buffer.rewind();
            attach.buffer.clear();
            byte[] data = msg.getBytes(cs);
            attach.buffer.put(data);
            attach.buffer.flip();
            //send back the data to the respective client
            attach.client.write(attach.buffer, attach, this);
            attach.buffer.rewind();

        } else {
            // Write to the client
            //attach.client.write(attach.buffer, attach, this);
            attach.isRead = true;
            attach.buffer.clear();
            attach.client.read(attach.buffer, attach, this);
        }
    }

    @Override
    public void failed(Throwable e, Attachment attach) {
        e.printStackTrace();
    }
}
