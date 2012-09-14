package smartcampus.services.communicator.helpers;

import java.util.ArrayList;
import java.util.List;

import smartcampus.services.communicator.beans.Notification;
import smartcampus.services.communicator.beans.UnitnNewsParameters;
import smartcampus.services.communicator.beans.UnitnNews;

public class UnitnNewsHelper {

	public static int test(String funnelId) {
		return 0;
	}

	public static Notification[] buildNotification(UnitnNews news[], String funnelId, UnitnNewsParameters pars) {
		List<Notification> result = new ArrayList<Notification>();

		for (UnitnNews un : news) {

			if (pars.getSource() == null || pars.getSource().equals(un.getSource())) {
				String title = un.getTitle().toLowerCase();
				String content = un.getContent().toLowerCase();
				if (pars.getKeywords() != null) {
					for (String keyword : pars.getKeywords()) {
						if (title.contains(keyword.toLowerCase()) || content.contains(keyword.toLowerCase())) {
							Notification not = buildNotification(un, funnelId);
							result.add(not);
							break;
						}
					}
				} else {
					Notification not = buildNotification(un, funnelId);
					result.add(not);
				}
			}
		}

		return (Notification[]) result.toArray();
	}

	private static Notification buildNotification(UnitnNews un, String funnelId) {
		Notification not = new Notification();
		not.setTitle(un.getTitle());
		not.setDescription(un.getContent());
		not.setFunnelId(funnelId);
		return not;
	}

}
