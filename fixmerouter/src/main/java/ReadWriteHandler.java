package fixmerouter;

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

		if (attach.isRead) {
			attach.buffer.flip();
			int limits = attach.buffer.limit();
			byte bytes[] = new byte[limits];
			attach.buffer.get(bytes, 0, limits);
			Charset cs = Charset.forName("UTF-8");
			String msg = new String(bytes, cs);
			/*System.out.format("Client at  %s  says: %s%n", attach.clientAddr,
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
			attach.buffer.rewind();*/

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
