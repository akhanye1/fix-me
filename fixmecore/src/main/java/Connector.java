package fixmecore;

import java.nio.channels.AsynchronousSocketChannel;
import java.net.InetSocketAddress;
import java.util.concurrent.Future;
import java.nio.charset.Charset;
import java.nio.ByteBuffer;

public class Connector {
	private int port;
	private Attachment attach;
	private ReadWriteHandler readwriteHandler;
	
	private void attachClientObject(AsynchronousSocketChannel channel) {
		this.attach = new Attachment();
		this.attach.client = channel;
		this.attach.buffer = ByteBuffer.allocate(2048);
		this.attach.isRead = false;
		this.attach.mainPort = this.port;
	}

	public Connector(int port) {
		this.port = port;
	}

	public boolean connect() {
		try {
			AsynchronousSocketChannel channel = AsynchronousSocketChannel.open();
			InetSocketAddress client = new InetSocketAddress("localhost", this.port);
			Future<Void> result = channel.connect(client);
			result.get();
			attachClientObject(channel);
			this.readwriteHandler = new ReadWriteHandler();
			System.out.println("Connected");
			return (true);
		}
		catch (Exception err) {
			System.out.println("Error connecting to port ::" + this.port);
		}
		return (false);
	}

	public void	sendMessage(String message) {
		byte[] data = message.getBytes();
		this.attach.buffer.clear();
		this.attach.buffer.rewind();
		this.attach.buffer.put(data);
		this.attach.isRead = false;
		this.attach.mainPort = this.port;
		this.attach.client.write(this.attach.buffer, this.attach, this.readwriteHandler);
	}
}
