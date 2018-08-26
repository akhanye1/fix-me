package com;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.net.SocketAddress;

public class Attachment {
	AsynchronousServerSocketChannel server;
	AsynchronousSocketChannel		client;
	ByteBuffer						buffer;
	SocketAddress					clientAddr;
	boolean							isRead;	
}
