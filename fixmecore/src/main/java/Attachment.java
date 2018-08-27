package fixmecore;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.net.SocketAddress;
import java.net.InetSocketAddress;

public class Attachment {
	public AsynchronousServerSocketChannel	server;
	public AsynchronousSocketChannel		client;
	public ByteBuffer						buffer;
	public SocketAddress					clientAddr;
	public InetSocketAddress				serverAddr;
	public boolean							isBroker;
	public boolean							isRead;	
	public boolean							mustRead;
}
