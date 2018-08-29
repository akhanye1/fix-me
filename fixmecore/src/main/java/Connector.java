package fixmecore;

import java.nio.channels.AsynchronousSocketChannel;
import java.net.InetSocketAddress;
import java.util.concurrent.Future;

public class Connector {
	private int port;
	private Attachment attach;
	
	private void attachClientObject(AsynchronousSocketChannel channel) {
		this.attach = new Attachment();
	}

	public Connector(int port) {
		this.port = port;
	}

	public void sendMessage(String message) {
	}

	public boolean connect() {
		try {
			AsynchronousSocketChannel channel = AsynchronousSocketChannel.open();
			InetSocketAddress client = new InetSocketAddress("localhost", this.port);
			Future<Void> result = channel.connect(client);
			result.get();
			System.out.println("Connected");
			return (true);
		}
		catch (Exception err) {
			System.out.println("Error connecting to port ::" + this.port);
		}
		return (false);
	}
}
