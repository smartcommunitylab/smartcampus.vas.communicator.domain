/*******************************************************************************
 * Copyright 2012-2013 Trento RISE
 * 
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 * 
 *        http://www.apache.org/licenses/LICENSE-2.0
 * 
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 ******************************************************************************/
package smartcampus.services.communicator.helpers;

import java.util.ArrayList;
import java.util.List;

import smartcampus.services.communicator.beans.NewsIds;
import smartcampus.services.communicator.beans.Notification;
import smartcampus.services.communicator.beans.NotificationAuthor;
import smartcampus.services.communicator.beans.UnitnNews;
import smartcampus.services.communicator.beans.UnitnNewsParameters;

public class UnitnNewsHelper {

	public static int test(String funnelId) {
		return 0;
	}

	public static Notification[] buildNotification(UnitnNews news[], String funnelId, NewsIds newsIds) {
		List<Notification> result = new ArrayList<Notification>();

		String type = extractType(news);

		if (type != null) {

			List<String> ids;
			if (newsIds != null && newsIds.contains(type)) {
				ids = newsIds.getValues(type);
			} else {
				ids = new ArrayList<String>();
			}

			for (UnitnNews un : news) {
				String id = buildId(un);
//				String title = un.getTitle().toLowerCase();
				if (ids.contains(id)) {
					continue;
				}

//				boolean found = false;
//				if (pars.getSources() != null && pars.getSources().size() != 0) {
//					for (String source : pars.getSources()) {
//						if (un.getSource().equals(source)) {
//							found = true;
//							break;
//						}
//					}
//				} else {
//					found = true;
//				}
//				
//				if (!found) {
//					continue;
//				}
//				
//				String content = un.getContent().toLowerCase();
//				found = false;
//				if (pars.getKeywords() != null && pars.getKeywords().size() != 0) {
//					for (String keyword : pars.getKeywords()) {
//						String k = keyword.toLowerCase();
//						String text = (" " + title + " " + content + " ").toLowerCase();
//						if (text.matches(".*[\\p{Punct}\\p{Blank}]" + k + "[\\p{Punct}\\p{Blank}].*")) {
//							found = true;
//							break;
//						}
//					}
//				} else {
//					found = true;
//				}
//				if (found) {
					Notification not = buildNotification(un, funnelId);
					result.add(not);
//				}
			}
		}
		
//		System.out.println("UNI NEWS: " + news.length);
//		System.out.println("NOT: " + result.size());

		return (result.size() == 0)?null:result.toArray(new Notification[result.size()]);
	}

	public static NewsIds updateIds(NewsIds newsIds, UnitnNews news[]) {
		List<String> nid = new ArrayList<String>();
		for (UnitnNews n : news) {
			nid.add(buildId(n));
		}

		String type = extractType(news);
		if (type == null) {
			return newsIds;
		}

		if (!newsIds.contains(type)) {
			newsIds.setValues(type, nid);
			return newsIds;
		}

		List<String> oid = newsIds.getValues(type);

		List<String> toAdd = new ArrayList<String>();
		List<String> toDelete = new ArrayList<String>();

		for (String n : nid) {
			if (!oid.contains(n)) {
				toAdd.add(n);
			}
		}

		for (String o : oid) {
			if (!nid.contains(o)) {
				toDelete.add(o);
			}
		}

//		System.out.println("TD: " + toDelete.size());
//		System.out.println("TA: " + toAdd.size());
//		System.out.println("Oold: " + oid.size());

		oid.removeAll(toDelete);
		oid.addAll(toAdd);

//		System.out.println("Onew: " + oid.size());

		newsIds.setValues(type, oid);
		
		return newsIds;
	}

	private static String buildId(UnitnNews news) {
		return news.getSource() + "_" + news.getTitle().toLowerCase().hashCode();
	}

	private static Notification buildNotification(UnitnNews un, String funnelId) {
		Notification not = new Notification();
		not.setTitle(un.getTitle());
		not.setDescription(un.getContent());

		NotificationAuthor author = new NotificationAuthor();
		author.setName("Unitn news - " + un.getSource());
		not.setAuthor(author);

		return not;
	}

	public static boolean isSubscribed(UnitnNews news[], UnitnNewsParameters pars) {
		String type = extractType(news);
		if (type != null) {
			if (pars.getSources() == null || pars.getSources().size() == 0 || pars.getSources().contains(type)) {
				return true;
			}
		}
		return false;
	}

	public static boolean subscribedUnitn(UnitnNews news[], UnitnNewsParameters pars) {
		String type = extractType(news);
		return type.equals("Ateneo") || type.equals("Scienze") || type.equals("Ingegneria") || type.equals("Unisport");
	}

	public static boolean subscribedOpera(UnitnNews news[], UnitnNewsParameters pars) {
//		String type = extractType(news);
		return extractType(news).equals("Opera Universitaria");
	}

	public static boolean subscribedCisca(UnitnNews news[], UnitnNewsParameters pars) {
//		String type = extractType(news);
		return extractType(news).equals("Cisca");
	}

	private static String extractType(UnitnNews news[]) {
		if (news == null || news.length == 0) {
			return null;
		}
		return news[0].getSource();
	}

}