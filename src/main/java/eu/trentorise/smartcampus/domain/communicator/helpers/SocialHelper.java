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
package eu.trentorise.smartcampus.domain.communicator.helpers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import eu.trentorise.smartcampus.domain.communicator.beans.EntityObject;
import eu.trentorise.smartcampus.domain.communicator.beans.Notification;
import eu.trentorise.smartcampus.domain.communicator.beans.NotificationAuthor;
import eu.trentorise.smartcampus.domain.communicator.beans.SocialNews;


public class SocialHelper {
	public static int test(String funnelId) {
		return 0;
	}

	public static Notification[] buildNotification(SocialNews news[], String funnelId, String newsIds[]) {
		List<Notification> result = new ArrayList<Notification>();
		List<String> ids;
		if (newsIds != null) {
			ids = Arrays.asList(newsIds);
		} else {
			ids = new ArrayList<String>();
		}
		
		for (SocialNews sn : news) {
			String id = buildId(sn);
			
			if (ids.contains(id)) {
				continue;
			}

			Notification not = buildNotification(sn, funnelId);
			result.add(not);
		}
		
		return (result.size() == 0)?null:result.toArray(new Notification[result.size()]);
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
			
//		System.out.println("TD: " + toDelete.size());
//		System.out.println("TA: " + toAdd.size());
//		System.out.println("Oold: " + oid.size());
		
			oid.removeAll(toDelete);
			oid.addAll(toAdd);
			
//			System.out.println("Onew: " + oid.size());
			
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

		List<EntityObject> eos = new ArrayList<EntityObject>();

		if (sn.getRelatedId() != null) {
			EntityObject eo = new EntityObject();
			eo.setEntityId(sn.getRelatedId());
			eo.setType(sn.getRelatedEntityType());
			eo.setTitle(sn.getRelatedTitle());
			eos.add(eo);
			
			description +=  sn.getRelatedEntityType() + "' " + sn.getRelatedTitle() + "' was linked with " + sn.getEntityType() + " '" + sn.getTitle() + "'.";
		} else if (sn.isUpdate()) {
			description += sn.getEntityType() + " '" + sn.getTitle() + "' was updated.";
		} else {
			description += sn.getEntityType() + " '" + sn.getTitle() + "' was created.";
		} 
		not.setDescription(description);
		
		EntityObject eo = new EntityObject();
		eo.setEntityId(sn.getEntityId());
		eo.setType(sn.getEntityType());
		eo.setTitle(sn.getTitle());
		eos.add(eo);
		
		not.setEntities(eos);		
		
		NotificationAuthor author = new NotificationAuthor();
		author.setSocialId(sn.getProviderId());
		not.setAuthor(author);		
		
		return not;
	}
	
}
