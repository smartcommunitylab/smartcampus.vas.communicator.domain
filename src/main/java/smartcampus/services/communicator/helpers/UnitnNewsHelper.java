package smartcampus.services.communicator.helpers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import smartcampus.services.communicator.beans.Notification;
import smartcampus.services.communicator.beans.UnitnNewsParameters;
import smartcampus.services.communicator.beans.UnitnNews;

public class UnitnNewsHelper {

	public static int test(String funnelId) {
		return 0;
	}

	public static Notification[] buildNotification(UnitnNews news[], String funnelId, UnitnNewsParameters pars,String newsIds[]) {
		List<Notification> result = new ArrayList<Notification>();
		List<String> ids;
		if (newsIds != null) {
			ids = Arrays.asList(newsIds);
		} else {
			ids = new ArrayList<String>();
		}
		
		for (UnitnNews un : news) {
			String id = buildId(un);
			String title = un.getTitle().toLowerCase();
			if (ids.contains(id)) {
				continue;
			}

			if (pars.getSources() == null || pars.getSources().contains(un.getSource())) {
				String content = un.getContent().toLowerCase();
				boolean found = false;
				if (pars.getKeywords() != null) {
					for (String keyword : pars.getKeywords()) {
						String k = keyword.toLowerCase();
						if (title.contains(k) || content.contains(k)) {
							found = true;
							break;
						}
					}
				} else {
					found = true;
				}
				if (found) {
				Notification not = buildNotification(un, funnelId);
				result.add(not);
				}
			}
		}

		return (Notification[]) result.toArray();
	}
	
	public static String[] updateIds(String oldIds[], Notification notifications[]) {
		return oldIds;
	}
	
	private static String buildId(UnitnNews news) {
		return "" + news.getTitle().toLowerCase().hashCode();
	}

	private static Notification buildNotification(UnitnNews un, String funnelId) {
		Notification not = new Notification();
		not.setTitle(un.getTitle());
		not.setDescription(un.getContent());
		not.setFunnelId(funnelId);
		return not;
	}
	
	public static boolean isUnitn(UnitnNews news[]) {
		String type = extractType(news);
		return type.equals("Ateneo") || type.equals("Scienze") || type.equals("Ingegneria") || type.equals("Unisport");
	}
	
	public static boolean isOpera(UnitnNews news[]) {
		return extractType(news).equals("Opera Universitaria");
	}
	
	public static boolean isCisca(UnitnNews news[]) {
		return extractType(news).equals("Cisca");
	}	
	
	private static String extractType(UnitnNews news[]) {
		if (news == null || news.length == 0) {
			return null;
		}
		return news[0].getSource();
	}

}
