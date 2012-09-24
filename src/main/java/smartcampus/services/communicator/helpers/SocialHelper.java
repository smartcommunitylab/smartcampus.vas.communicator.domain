package smartcampus.services.communicator.helpers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import smartcampus.services.communicator.beans.EntityObject;
import smartcampus.services.communicator.beans.Notification;
import smartcampus.services.communicator.beans.NotificationAuthor;
import smartcampus.services.communicator.beans.SocialNews;
import smartcampus.services.communicator.beans.SocialParameters;
import smartcampus.services.communicator.beans.UnitnNews;

public class SocialHelper {

	public static int test(String funnelId) {
		return 0;
	}

	public static Notification[] buildNotification(SocialNews news[], String funnelId, SocialParameters pars,String newsIds[]) {
		List<Notification> result = new ArrayList<Notification>();
		List<String> ids;
		if (newsIds != null) {
			ids = Arrays.asList(newsIds);
		} else {
			ids = new ArrayList<String>();
		}
		
		for (SocialNews sn : news) {
			String id = buildId(sn);
			String title = sn.getTitle().toLowerCase();
			if (ids.contains(id)) {
				continue;
			}

				boolean found = false;
				if (pars.getTopics() != null && pars.getTopics().size() != 0) {
					for (Long topicId : pars.getTopics()) {
						  if (sn.getTopicId() == topicId)
							found = true;
							break;
						}
				} else {
					found = true;
				}
				if (found) {
				Notification not = buildNotification(sn, funnelId);
				result.add(not);
				}
		}

		Notification res[] = result.toArray(new Notification[result.size()]);
		return res;
	}
	
	public static String[] updateIds(String oldIds[], SocialNews news[]) {
		List<String> nid = new ArrayList<String>();
		for (SocialNews n: news) {
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
			
//		System.out.println("TD: " + toDelete);
//		System.out.println("TA: " + toAdd);
		
			oid.removeAll(toDelete);
			oid.addAll(toAdd);
			
			return (String[])oid.toArray(new String[oid.size()]);
	}
	
	private static String buildId(SocialNews news) {
		return "" + news.getTopicId() + "_" + news.getEntityId();
	}

	private static Notification buildNotification(SocialNews sn, String funnelId) {
		Notification not = new Notification();
		not.setTitle(sn.getTitle());
//		not.setDescription("New content for " + sn.getTopicName() + ": " + sn.getEntityType() + " " + sn.getTitle() + ".");
		String description =  "News for topic '" + sn.getTopicName() + "': ";
		if (sn.getRelatedId() != null) {
			description +=  sn.getRelatedEntityType() + "' " + sn.getRelatedTitle() + "' was linked with " + sn.getEntityType() + " '" + sn.getRelatedTitle() + "'.";
		} else {
			description += sn.getEntityType() + " '" + sn.getRelatedTitle() + "' was updated.";
		}
		not.setDescription(description);
		not.setFunnelId(funnelId);
		
		List<EntityObject> eos = new ArrayList<EntityObject>();
		EntityObject eo = new EntityObject();
		eo.setEntityId(sn.getEntityId());
		eo.setType(sn.getEntityType());
		eos.add(eo);
		
		not.setEntities(eos);		
		
		NotificationAuthor author = new NotificationAuthor();
		author.setSocialId(sn.getProviderId());
		not.setAuthor(author);		
		
		return not;
	}
	
}
