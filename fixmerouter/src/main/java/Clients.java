package fixmerouter;

import fixmecore.Attachment;
import java.util.List;
import java.util.ArrayList;

public class Clients {
	private static List<Attachment>	attachedClients = new ArrayList<Attachment>();

	public static void addClient(Attachment attach) {
		System.out.println("New client added :: " + attach.id);
		attachedClients.add(attach);
	}

	public static Attachment findClient(Attachment attach) {
		for (Attachment tempAttach : attachedClients) {
			if (tempAttach.id.equals(attach.id)) {
				return (tempAttach);
			}
		}
		return (null);
	}
}
