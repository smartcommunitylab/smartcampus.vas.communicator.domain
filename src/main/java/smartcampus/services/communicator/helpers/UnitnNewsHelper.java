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

			if (pars.getSources() == null || pars.getSources().size() == 0 || pars.getSources().contains(un.getSource())) {
				String content = un.getContent().toLowerCase();
				boolean found = false;
				if (pars.getKeywords() != null && pars.getKeywords().size() != 0) {
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

		Notification res[] = result.toArray(new Notification[result.size()]);
		return res;
	}
	
	public static String[] updateIds(String oldIds[], UnitnNews news[]) {
		List<String> nid = new ArrayList<String>();
		for (UnitnNews n: news) {
			nid.add(buildId(n));
		}
		
		if (oldIds == null) {
			return nid.toArray(new String[nid.size()]);
		}
		List<String> oid = new ArrayList<String>(Arrays.asList(oldIds));

		
		List<String> toAdd = new ArrayList<String>();
		List<String> toDelete = new ArrayList<String>();
		
		for (String n: nid) {
			if (!oid.contains(n)) {
				toAdd.add(n);
			}
		}
		
		for (String o: oid) {
			if (!nid.contains(o)) {
				toDelete.add(o);
			}
		}
			
		System.out.println("TD: " + toDelete);
		System.out.println("TA: " + toAdd);
		
			oid.removeAll(toDelete);
			oid.addAll(toAdd);
			
			return (String[])oid.toArray(new String[oid.size()]);
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
