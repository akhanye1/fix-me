package fixmecore;

import java.nio.channels.CompletionHandler;
import fixmecore.Attachment;
import java.io.IOException;
import java.nio.charset.Charset;

public class ReadWriteHandler implements CompletionHandler<Integer, Attachment> {

	@Override
	public void completed(Integer result, Attachment attach) {
		if (result == -1) {
			try {
				attach.client.close();
				System.out.format("Stopped   listening to the client %s%n",
						attach.clientAddr);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			return;
		}
		System.out.println("Is read write :: " + attach.isRead + " | port :: " + attach.mainPort);
		if (attach.isRead) {
			attach.buffer.flip();
			int limits = attach.buffer.limit();
			byte bytes[] = new byte[limits];
			attach.buffer.get(bytes, 0, limits);
			Charset cs = Charset.forName("UTF-8");
			String msg = new String(bytes, cs);
			if (attach.mainPort == 5000) {
				System.out.println("5000 made connection");
			}
			else if (attach.mainPort == 5001) {
				System.out.println("5001 made connection");
				//attach.mustRead = false;
			}
			if (attach.response != null) {
				System.out.println("Response sent to calling class");
				attach.response.processMessage(msg, this, attach);
			}
			else {
				System.out.println("Calling class not set");
			}	
		} else {
			if (attach.mustRead) {
				System.out.println("Must read");
				attach.isRead = true;
				attach.buffer.clear();
				attach.client.read(attach.buffer, attach, this);
			}
			else {
				System.out.println("Do not listen");
			}
		}
	}

	@Override
	public void failed(Throwable e, Attachment attach) {
		e.printStackTrace();
	}

}
