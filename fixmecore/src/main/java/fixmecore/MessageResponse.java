package fixmecore;

public interface MessageResponse {
	public abstract void processMessage(String message, ReadWriteHandler readWriteHandler, Attachment attach);
}
